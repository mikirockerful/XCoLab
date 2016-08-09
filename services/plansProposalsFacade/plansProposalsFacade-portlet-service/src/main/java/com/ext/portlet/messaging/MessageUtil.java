package com.ext.portlet.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.joda.time.DateTime;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageConstants;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.messaging.MessageLimitManager;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.utils.TemplateReplacementUtil;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class MessageUtil {

    private static final Log _log = LogFactoryUtil.getLog(MessageUtil.class);

    private MessageUtil() {
    }

    public static int countMessages(long userId, MessageType type) {

        switch (type) {
            case INBOX:
                return MessagingClient.getMessageCountForUser(userId, false);
            case ARCHIVED:
                return MessagingClient.getMessageCountForUser(userId, true);
            case SENT:
                return MessagingClient.getSentMessageCountForUser(userId);
            default:
                return 0;
        }
    }

    public static List<Message> getMessages(long userId, int pagerStart, int pagerNext, MessageType type) {
        switch (type) {
            case INBOX:
                return MessagingClient.getMessagesForUser(pagerStart, pagerNext, userId, false);
            case ARCHIVED:
                return MessagingClient.getMessagesForUser(pagerStart, pagerNext, userId, true);
            case SENT:
                return MessagingClient.getSentMessagesForUser(pagerStart, pagerNext, userId);
            default:
                return Collections.emptyList();
        }
    }

    public static void checkLimitAndSendMessage(String subject, String content,
            Member fromUser, Collection<Long> recipientIds) throws MessageLimitExceededException {
        Long fromId = fromUser.getUserId();
        synchronized (MessageLimitManager.getMutex(fromId)) {
            // Send a validation problem mail to patrick if the daily limit is reached for a user
            if (!MessageLimitManager.canSendMessages(recipientIds.size(), fromId)) {
                _log.warn("User exceeded validation limit " + fromId);

                // Only send the email once in 24h!
                if (MessageLimitManager.shouldSendValidationErrorMessage(fromUser)) {
                    recipientIds.clear();
                    recipientIds.add(1011659L); //patrick
                    sendMessage("VALIDATION PROBLEM  " + subject, "VALIDATION PROBLEM  " + content,
                            fromId, fromId, recipientIds);
                }
                throw new MessageLimitExceededException(fromId);
            }

            sendMessage(subject, content, fromId, fromId, recipientIds);
        }
    }

    public static void sendMessage(String subject, String content, Long fromId,
            Long replyToId, Collection<Long> recipientIds) {
        Message message = new Message();
        message.setSubject(StringEscapeUtils.unescapeXml(subject));
        message.setContent(content.replaceAll("\n", ""));
        message.setFromId(fromId);
        message.setCreateDate(new Timestamp(DateTime.now().getMillis()));
        message.setRepliesTo(replyToId);
        message = MessagingClient.createMessage(message);
        for (long memberId : recipientIds) {
            try {
                Member recipient = MembersClient.getMember(memberId);
                MessagingClient.createRecipient(message.getMessageId(), memberId);
                if (MessagingClient.getMessagingPreferencesForMember(memberId).getEmailOnReceipt()) {
                    copyRecipient(recipient, message);
                }
            } catch (MemberNotFoundException e) {
                _log.error("Member " + memberId + ", recipient of message "
                        + message.getMessageId() + ", does not exist");
            }
        }
    }

    private static void copyRecipient(Member recipient, Message m) {
        try {
            Member from = MembersClient.getMember(m.getFromId());
            String subject = m.getSubject();
            if (subject.length() < 3) {
                subject = MessageConstants.EMAIL_MESSAGE_SUBJECT.replace(
                        MessageConstants.EMAIL_MESSAGE_VAR_USER, from.getScreenName());
                subject = TemplateReplacementUtil.replacePlatformConstants(subject);
            }
            String message = TemplateReplacementUtil.replacePlatformConstants(
                    MessageConstants.EMAIL_MESSAGE_TEMPLATE.replace(
                            MessageConstants.EMAIL_MESSAGE_VAR_USER, from.getScreenName())
                            .replace(MessageConstants.EMAIL_MESSAGE_VAR_URL, createMessageURL(m))
                            .replace(MessageConstants.EMAIL_MESSAGE_VAR_SUBJECT, m.getSubject())
                            .replace(MessageConstants.EMAIL_MESSAGE_VAR_MESSAGE,
                                    m.getContent()));

            String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.getStringValue();
            EmailClient.sendEmail(fromEmail, recipient.getEmailAddress(), subject, message, true,
                            fromEmail);
        } catch (MemberNotFoundException e) {
            throw new IllegalArgumentException("Sender " + m.getFromId()
                    + " of message " + m.getMessageId() + " does not exist");
        }
    }

    private static String createMessageURL(Message m) {
        String home = ConfigurationAttributeKey.COLAB_URL.getStringValue();
        return home + MessageConstants.EMAIL_MESSAGE_URL_TEMPLATE + m.getMessageId();
    }
}

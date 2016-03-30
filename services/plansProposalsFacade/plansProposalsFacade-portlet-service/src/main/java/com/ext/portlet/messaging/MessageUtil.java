/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;
import org.apache.commons.lang.StringEscapeUtils;
import org.xcolab.utils.MessageLimitManager;
import org.xcolab.utils.TemplateReplacementUtil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.PortletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author jintrone
 * @date 01/19/2010
 * @version 1.0
 */
public final class MessageUtil {

    private static final Log _log = LogFactoryUtil.getLog(MessageUtil.class);

    private MessageUtil() { }

    public static int countMessages(long userId,  String type) throws SystemException, PortalException {

        if (MessageConstants.INBOX.equals(type)) {
            return MessageRecipientStatusLocalServiceUtil.countInboxMessagesForUser(userId);
        }
        if (MessageConstants.ARCHIVED.equals(type)) {
            return MessageRecipientStatusLocalServiceUtil.countArchivedMessagesForUser(userId);
        }
        if (MessageConstants.SENT.equals(type)) {
            return MessageLocalServiceUtil.countSentMessage(userId);
        }
        return 0;
    }

    public static List<Message> getMessages(long userId, int pagerStart, int pagerNext, String type) throws SystemException, PortalException {
        if (MessageConstants.INBOX.equals(type)) {
            List<MessageRecipientStatus> result = MessageRecipientStatusLocalServiceUtil.findInboxMessagesForUser(userId,pagerStart,pagerNext);
            return convertToMessages(result);
        }
        if (MessageConstants.ARCHIVED.equals(type)) {
            List<MessageRecipientStatus> result = MessageRecipientStatusLocalServiceUtil.findArchivedMessagesForUser(userId,pagerStart,pagerNext);
            return convertToMessages(result);
        }
        if (MessageConstants.SENT.equals(type)) {
            return MessageLocalServiceUtil.findSentMessages(userId,pagerStart,pagerNext);
        }
        return Collections.emptyList();
    }

    public static List<Message> convertToMessages(List<MessageRecipientStatus> statuses) throws SystemException, PortalException {
        List<Message> result = new ArrayList<>();
        for (MessageRecipientStatus status:statuses) {
            result.add(MessageLocalServiceUtil.getMessage(status.getMessageId()));
        }
        return result;
    }

    public static boolean checkLimitAndSendMessage(String subject, String content,
            User fromUser, Collection<Long> recipientIds)
            throws AddressException, PortalException, MailEngineException,
            SystemException, UnsupportedEncodingException {
        Long fromId = fromUser.getUserId();
        synchronized (MessageLimitManager.getMutex(fromId)) {
            // Send a validation problem mail to patrick if the daily limit is reached for a user
            if (!MessageLimitManager.canSendMessages(recipientIds.size(), fromUser)) {
                _log.warn("User exceeded validation limit " + fromId);

                // Only send the email once in 24h!
                if (MessageLimitManager.shouldSendValidationErrorMessage(fromUser)) {
                    recipientIds.clear();
                    recipientIds.add(1011659L); //patrick
                    sendMessage("VALIDATION PROBLEM  "+subject, "VALIDATION PROBLEM  "+content, fromId, fromId, recipientIds, null);
                }
                return false;
            }

            sendMessage(subject, content, fromId, fromId, recipientIds, null);
            return true;
        }
    }

    public static void sendMessage(String subject, String content, Long fromId, Long replyToId, Collection<Long> recipientIds, PortletRequest request) throws SystemException, PortalException, MailEngineException, AddressException, UnsupportedEncodingException {
        long nextId = CounterLocalServiceUtil.increment(Message.class.getName());
        Message m = MessageLocalServiceUtil.createMessage(nextId);
        m.setSubject(StringEscapeUtils.unescapeXml(subject));
        m.setContent(content);
        m.setFromId(fromId);
        m.setCreateDate(new Date());
        m.setRepliesTo(replyToId);
        MessageLocalServiceUtil.updateMessage(m);
        for (long user : recipientIds) {
            createRecipient(nextId, user);
            if (getMessagingPreferences(user).getEmailOnReceipt()) {
                copyRecipient(user, m, request);
            }
        }
        if (getMessagingPreferences(fromId).getEmailOnSend()) {
            copySender(m, request);
        }
    }

    public static MessageRecipientStatus createRecipient(long messageId, long userid) throws SystemException {
        long nextid = CounterLocalServiceUtil.increment(MessageRecipientStatus.class.getName());
        MessageRecipientStatus result = MessageRecipientStatusLocalServiceUtil.createMessageRecipientStatus(nextid);
        result.setMessageId(messageId);
        result.setUserId(userid);
        result.setArchived(false);
        result.setOpened(false);
        MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(result);
        return result;
    }

    public static void copySender(Message m, PortletRequest request) throws SystemException {
        //tbd
    }

    public static void copyRecipient(Long userId, Message m, PortletRequest request) throws SystemException, PortalException, AddressException, MailEngineException, UnsupportedEncodingException {
        User from = UserLocalServiceUtil.getUser(m.getFromId());
        User to = UserLocalServiceUtil.getUser(userId);
        String subject = m.getSubject();
        if (subject.length() < 3) {
            subject = MessageConstants.EMAIL_MESSAGE_SUBJECT.replace(MessageConstants.EMAIL_MESSAGE_VAR_USER,from.getScreenName());
            subject = TemplateReplacementUtil.replacePlatformConstants(subject);
        }
        String message = TemplateReplacementUtil.replacePlatformConstants(
                MessageConstants.EMAIL_MESSAGE_TEMPLATE.replace(MessageConstants.EMAIL_MESSAGE_VAR_USER,from.getScreenName())
                .replace(MessageConstants.EMAIL_MESSAGE_VAR_URL,createMessageURL(m, request)).replace(MessageConstants.EMAIL_MESSAGE_VAR_SUBJECT,m.getSubject())
                .replace(MessageConstants.EMAIL_MESSAGE_VAR_MESSAGE,m.getContent().replaceAll("\n" ,"<br />")));

        InternetAddress fromEmail = TemplateReplacementUtil.getAdminFromEmailAddress();
        InternetAddress toEmail = new InternetAddress(to.getEmailAddress());
        MailEngine.send(fromEmail, toEmail, subject, message, true);

    }

    public static String createMessageURL(Message m, PortletRequest request) {
        String home = "http://climatecolab.org";
        if (request != null) {
            int port = PortalUtil.getPortalPort();
            home = "http://" + PortalUtil.getHost(request) + (port != 80 ? ":" + port : "");
        }

        return home + MessageConstants.EMAIL_MESSAGE_URL_TEMPLATE + m.getMessageId();
    }

    public static MessagingUserPreferences getMessagingPreferences(long userId) throws SystemException {
        MessagingUserPreferences prefs = MessagingUserPreferencesLocalServiceUtil.findByUser(userId);
        if (prefs == null) {
            long nextId = CounterLocalServiceUtil.increment(MessagingUserPreferencesLocalServiceUtil.class.getName());
            prefs = MessagingUserPreferencesLocalServiceUtil.createMessagingUserPreferences(nextId);
            prefs.setEmailOnReceipt(true);
            prefs.setEmailOnSend(false);
            prefs.setUserId(userId);
            prefs.setEmailOnActivity(true);
            prefs.setEmailActivityDailyDigest(true);
            MessagingUserPreferencesLocalServiceUtil.addMessagingUserPreferences(prefs);
        }
        
        return prefs;
    }
}

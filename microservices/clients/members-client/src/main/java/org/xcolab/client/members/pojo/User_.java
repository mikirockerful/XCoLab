/**
 * This class is generated by jOOQ
 */
package org.xcolab.client.members.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.xcolab.client.members.MembersClient;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * This class is generated by jOOQ.
 *
 * @Generated( value = { "http://www.jooq.org", "jOOQ version:3.7.2" }, comments = "This class is
 * generated by jOOQ" )
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class User_ implements Serializable {

    private static final long serialVersionUID = 650725730;

    private String uuid_;
    private Long userid;
    private Long companyid;
    private Timestamp createdate;
    private Timestamp modifieddate;
    private Byte defaultuser;
    private Long contactid;
    private String password_;
    private Byte passwordencrypted;
    private Byte passwordreset;
    private Timestamp passwordmodifieddate;
    private String reminderqueryquestion;
    private String reminderqueryanswer;
    private Integer gracelogincount;
    private String screenname;
    private String emailaddress;
    private String openid;
    private Long portraitid;
    private String languageid;
    private String timezoneid;
    private String greeting;
    private String comments;
    private String firstname;
    private String middlename;
    private String lastname;
    private String jobtitle;
    private Timestamp logindate;
    private String loginip;
    private Timestamp lastlogindate;
    private String lastloginip;
    private Timestamp lastfailedlogindate;
    private Integer failedloginattempts;
    private Byte lockout;
    private Timestamp lockoutdate;
    private Byte agreedtotermsofuse;
    private Double socialcontributionequity;
    private Double socialparticipationequity;
    private Double socialpersonalequity;
    private Long facebookid;
    private String digest;
    private Byte emailaddressverified;
    private Integer status;
    private Long ldapserverid;

    public User_() {
    }

    public User_(User_ value) {
        this.uuid_ = value.uuid_;
        this.userid = value.userid;
        this.companyid = value.companyid;
        this.createdate = value.createdate;
        this.modifieddate = value.modifieddate;
        this.defaultuser = value.defaultuser;
        this.contactid = value.contactid;
        this.password_ = value.password_;
        this.passwordencrypted = value.passwordencrypted;
        this.passwordreset = value.passwordreset;
        this.passwordmodifieddate = value.passwordmodifieddate;
        this.reminderqueryquestion = value.reminderqueryquestion;
        this.reminderqueryanswer = value.reminderqueryanswer;
        this.gracelogincount = value.gracelogincount;
        this.screenname = value.screenname;
        this.emailaddress = value.emailaddress;
        this.openid = value.openid;
        this.portraitid = value.portraitid;
        this.languageid = value.languageid;
        this.timezoneid = value.timezoneid;
        this.greeting = value.greeting;
        this.comments = value.comments;
        this.firstname = value.firstname;
        this.middlename = value.middlename;
        this.lastname = value.lastname;
        this.jobtitle = value.jobtitle;
        this.logindate = value.logindate;
        this.loginip = value.loginip;
        this.lastlogindate = value.lastlogindate;
        this.lastloginip = value.lastloginip;
        this.lastfailedlogindate = value.lastfailedlogindate;
        this.failedloginattempts = value.failedloginattempts;
        this.lockout = value.lockout;
        this.lockoutdate = value.lockoutdate;
        this.agreedtotermsofuse = value.agreedtotermsofuse;
        this.socialcontributionequity = value.socialcontributionequity;
        this.socialparticipationequity = value.socialparticipationequity;
        this.socialpersonalequity = value.socialpersonalequity;
        this.facebookid = value.facebookid;
        this.digest = value.digest;
        this.emailaddressverified = value.emailaddressverified;
        this.status = value.status;
        this.ldapserverid = value.ldapserverid;
    }

    public User_(
            String uuid_,
            Long userid,
            Long companyid,
            Timestamp createdate,
            Timestamp modifieddate,
            Byte defaultuser,
            Long contactid,
            String password_,
            Byte passwordencrypted,
            Byte passwordreset,
            Timestamp passwordmodifieddate,
            String reminderqueryquestion,
            String reminderqueryanswer,
            Integer gracelogincount,
            String screenname,
            String emailaddress,
            String openid,
            Long portraitid,
            String languageid,
            String timezoneid,
            String greeting,
            String comments,
            String firstname,
            String middlename,
            String lastname,
            String jobtitle,
            Timestamp logindate,
            String loginip,
            Timestamp lastlogindate,
            String lastloginip,
            Timestamp lastfailedlogindate,
            Integer failedloginattempts,
            Byte lockout,
            Timestamp lockoutdate,
            Byte agreedtotermsofuse,
            Double socialcontributionequity,
            Double socialparticipationequity,
            Double socialpersonalequity,
            Long facebookid,
            String digest,
            Byte emailaddressverified,
            Integer status,
            Long ldapserverid
    ) {
        this.uuid_ = uuid_;
        this.userid = userid;
        this.companyid = companyid;
        this.createdate = createdate;
        this.modifieddate = modifieddate;
        this.defaultuser = defaultuser;
        this.contactid = contactid;
        this.password_ = password_;
        this.passwordencrypted = passwordencrypted;
        this.passwordreset = passwordreset;
        this.passwordmodifieddate = passwordmodifieddate;
        this.reminderqueryquestion = reminderqueryquestion;
        this.reminderqueryanswer = reminderqueryanswer;
        this.gracelogincount = gracelogincount;
        this.screenname = screenname;
        this.emailaddress = emailaddress;
        this.openid = openid;
        this.portraitid = portraitid;
        this.languageid = languageid;
        this.timezoneid = timezoneid;
        this.greeting = greeting;
        this.comments = comments;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.jobtitle = jobtitle;
        this.logindate = logindate;
        this.loginip = loginip;
        this.lastlogindate = lastlogindate;
        this.lastloginip = lastloginip;
        this.lastfailedlogindate = lastfailedlogindate;
        this.failedloginattempts = failedloginattempts;
        this.lockout = lockout;
        this.lockoutdate = lockoutdate;
        this.agreedtotermsofuse = agreedtotermsofuse;
        this.socialcontributionequity = socialcontributionequity;
        this.socialparticipationequity = socialparticipationequity;
        this.socialpersonalequity = socialpersonalequity;
        this.facebookid = facebookid;
        this.digest = digest;
        this.emailaddressverified = emailaddressverified;
        this.status = status;
        this.ldapserverid = ldapserverid;
    }

    public String getUuid_() {
        return this.uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public Long getCompanyId() {
        return this.companyid;
    }

    public void setCompanyId(Long companyid) {
        this.companyid = companyid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getModifiedDate() {
        return this.modifieddate;
    }

    public void setModifiedDate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Byte getDefaultUser() {
        return this.defaultuser;
    }

    public void setDefaultUser(Byte defaultuser) {
        this.defaultuser = defaultuser;
    }

    public Long getContactId() {
        return this.contactid;
    }

    public void setContactId(Long contactid) {
        this.contactid = contactid;
    }

    public String getPassword_() {
        return this.password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public Byte getPasswordEncrypted() {
        return this.passwordencrypted;
    }

    public void setPasswordEncrypted(Byte passwordencrypted) {
        this.passwordencrypted = passwordencrypted;
    }

    public Byte getPasswordReset() {
        return this.passwordreset;
    }

    public void setPasswordReset(Byte passwordreset) {
        this.passwordreset = passwordreset;
    }

    public Timestamp getPasswordModifiedDate() {
        return this.passwordmodifieddate;
    }

    public void setPasswordModifiedDate(Timestamp passwordmodifieddate) {
        this.passwordmodifieddate = passwordmodifieddate;
    }

    public String getReminderQueryQuestion() {
        return this.reminderqueryquestion;
    }

    public void setReminderQueryQuestion(String reminderqueryquestion) {
        this.reminderqueryquestion = reminderqueryquestion;
    }

    public String getReminderQueryAnswer() {
        return this.reminderqueryanswer;
    }

    public void setReminderQueryAnswer(String reminderqueryanswer) {
        this.reminderqueryanswer = reminderqueryanswer;
    }

    public Integer getGraceLoginCount() {
        return this.gracelogincount;
    }

    public void setGraceLoginCount(Integer gracelogincount) {
        this.gracelogincount = gracelogincount;
    }

    public String getScreenName() {
        return this.screenname;
    }

    public void setScreenName(String screenname) {
        this.screenname = screenname;
    }

    public String getEmailAddress() {
        return this.emailaddress;
    }

    public void setEmailAddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getOpenId() {
        return this.openid;
    }

    public void setOpenId(String openid) {
        this.openid = openid;
    }

    public Long getPortraitId() {
        return this.portraitid;
    }

    public void setPortraitId(Long portraitid) {
        this.portraitid = portraitid;
    }

    public String getLanguageId() {
        return this.languageid;
    }

    public void setLanguageId(String languageid) {
        this.languageid = languageid;
    }

    public String getTimeZoneId() {
        return this.timezoneid;
    }

    public void setTimeZoneId(String timezoneid) {
        this.timezoneid = timezoneid;
    }

    public String getGreeting() {
        return this.greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return this.middlename;
    }

    public void setMiddleName(String middlename) {
        this.middlename = middlename;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getJobTitle() {
        return this.jobtitle;
    }

    public void setJobTitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public Timestamp getLoginDate() {
        return this.logindate;
    }

    public void setLoginDate(Timestamp logindate) {
        this.logindate = logindate;
    }

    public String getLoginIP() {
        return this.loginip;
    }

    public void setLoginIP(String loginip) {
        this.loginip = loginip;
    }

    public Timestamp getLastLoginDate() {
        return this.lastlogindate;
    }

    public void setLastLoginDate(Timestamp lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getLastLoginIP() {
        return this.lastloginip;
    }

    public void setLastLoginIP(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public Timestamp getLastFailedLoginDate() {
        return this.lastfailedlogindate;
    }

    public void setLastFailedLoginDate(Timestamp lastfailedlogindate) {
        this.lastfailedlogindate = lastfailedlogindate;
    }

    public Integer getFailedLoginAttempts() {
        return this.failedloginattempts;
    }

    public void setFailedLoginAttempts(Integer failedloginattempts) {
        this.failedloginattempts = failedloginattempts;
    }

    public Byte getLockout() {
        return this.lockout;
    }

    public void setLockout(Byte lockout) {
        this.lockout = lockout;
    }

    public Timestamp getLockoutDate() {
        return this.lockoutdate;
    }

    public void setLockoutDate(Timestamp lockoutdate) {
        this.lockoutdate = lockoutdate;
    }

    public Byte getAgreedToTermsOfUse() {
        return this.agreedtotermsofuse;
    }

    public void setAgreedToTermsOfUse(Byte agreedtotermsofuse) {
        this.agreedtotermsofuse = agreedtotermsofuse;
    }

    public Double getSocialContributionEquity() {
        return this.socialcontributionequity;
    }

    public void setSocialContributionEquity(Double socialcontributionequity) {
        this.socialcontributionequity = socialcontributionequity;
    }

    public Double getSocialParticipationEquity() {
        return this.socialparticipationequity;
    }

    public void setSocialParticipationEquity(Double socialparticipationequity) {
        this.socialparticipationequity = socialparticipationequity;
    }

    public Double getSocialPersonalEquity() {
        return this.socialpersonalequity;
    }

    public void setSocialPersonalEquity(Double socialpersonalequity) {
        this.socialpersonalequity = socialpersonalequity;
    }

    public Long getFacebookId() {
        return this.facebookid;
    }

    public void setFacebookId(Long facebookid) {
        this.facebookid = facebookid;
    }

    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Byte getEmailAddressVerified() {
        return this.emailaddressverified;
    }

    public void setEmailAddressVerified(Byte emailaddressverified) {
        this.emailaddressverified = emailaddressverified;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLdapServerId() {
        return this.ldapserverid;
    }

    public void setLdapServerId(Long ldapserverid) {
        this.ldapserverid = ldapserverid;
    }

    public boolean isActive() {
        return this.getStatus() == 0;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
    }

    public List<Role_> getRoles() {
        return MembersClient.getMemberRoles(this.getUserId());
    }

    public boolean getFemale() {
        return !this.getMale();
    }

    public boolean getMale() {
        return this.getContact().getMale();
    }

    public Contact_ getContact() {
        Contact_ var2;
            var2 = MembersClient.getContact(this.getContactId());
        return var2;
    }

    public String getPassword() {
        return this.password_==null?"":this.password_;
    }

    public void setPassword(String password) {
        this.password_=this.password_;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User_ (");

        sb.append(uuid_);
        sb.append(", ").append(userid);
        sb.append(", ").append(companyid);
        sb.append(", ").append(createdate);
        sb.append(", ").append(modifieddate);
        sb.append(", ").append(defaultuser);
        sb.append(", ").append(contactid);
        sb.append(", ").append(password_);
        sb.append(", ").append(passwordencrypted);
        sb.append(", ").append(passwordreset);
        sb.append(", ").append(passwordmodifieddate);
        sb.append(", ").append(reminderqueryquestion);
        sb.append(", ").append(reminderqueryanswer);
        sb.append(", ").append(gracelogincount);
        sb.append(", ").append(screenname);
        sb.append(", ").append(emailaddress);
        sb.append(", ").append(openid);
        sb.append(", ").append(portraitid);
        sb.append(", ").append(languageid);
        sb.append(", ").append(timezoneid);
        sb.append(", ").append(greeting);
        sb.append(", ").append(comments);
        sb.append(", ").append(firstname);
        sb.append(", ").append(middlename);
        sb.append(", ").append(lastname);
        sb.append(", ").append(jobtitle);
        sb.append(", ").append(logindate);
        sb.append(", ").append(loginip);
        sb.append(", ").append(lastlogindate);
        sb.append(", ").append(lastloginip);
        sb.append(", ").append(lastfailedlogindate);
        sb.append(", ").append(failedloginattempts);
        sb.append(", ").append(lockout);
        sb.append(", ").append(lockoutdate);
        sb.append(", ").append(agreedtotermsofuse);
        sb.append(", ").append(socialcontributionequity);
        sb.append(", ").append(socialparticipationequity);
        sb.append(", ").append(socialpersonalequity);
        sb.append(", ").append(facebookid);
        sb.append(", ").append(digest);
        sb.append(", ").append(emailaddressverified);
        sb.append(", ").append(status);
        sb.append(", ").append(ldapserverid);

        sb.append(")");
        return sb.toString();
    }
}
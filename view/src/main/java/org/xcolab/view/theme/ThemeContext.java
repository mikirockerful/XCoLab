package org.xcolab.view.theme;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.util.http.servlet.RequestUtil;
import org.xcolab.view.auth.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

public class ThemeContext {

    private I18nVariables i18NVariables;
    private AuthenticationVariables authenticationVariables;
    private CredentialVariables credentialVariables;
    private ThemeVariables themeVariables;
    private MetaVariables metaVariables;
    private SharedColabVariables sharedColabVariables;
    private MessageVariables messageVariables;
    private SocialMediaVariables socialMediaVariables;

    private ServerEnvironment serverEnvironment;
    // public boolean isProductionEnvironment;
    // Now use: serverEnvironment.isProduction()

    private String colabName;
    private String colabLongName;
    private String colabShortName;
    private String colabUrl;
    private String colabUrlProduction;
    private String blogAdminUrl;
    private String adminEmail;
    private ContestType defaultContestType;

    private String requestUri;

    private void initSubObjects(AuthenticationService authenticationService,
                                HttpServletRequest request) {
        this.i18NVariables = new I18nVariables();
        this.authenticationVariables = new AuthenticationVariables(authenticationService, request);
        this.credentialVariables = new CredentialVariables();
        this.themeVariables = new ThemeVariables(request, this.getI18NVariables());
        this.metaVariables = new MetaVariables(request, this.getI18NVariables());
        this.sharedColabVariables = new SharedColabVariables();
        this.messageVariables = new MessageVariables(request);
        this.socialMediaVariables = new SocialMediaVariables(request);
    }

    public ThemeContext(AuthenticationService authenticationService,
                        HttpServletRequest request) {
        this.initSubObjects(authenticationService, request);

        this.serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();

        this.colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        this.colabLongName = ConfigurationAttributeKey.COLAB_LONG_NAME.get();
        this.colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.get();

        this.colabUrl = PlatformAttributeKey.COLAB_URL.get();
        this.colabUrlProduction = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get();
        this.blogAdminUrl = ConfigurationAttributeKey.BLOG_ADMIN_URL.get();

        this.adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        final Long defaultContestTypeId =
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        this.defaultContestType = ContestTypeClient
                .getContestType(defaultContestTypeId, this.getI18NVariables().getLanguage());

        this.requestUri = RequestUtil.getOriginalUri(request);
    }

    public I18nVariables getI18NVariables() {
        return i18NVariables;
    }

    public AuthenticationVariables getAuthenticationVariables() {
        return authenticationVariables;
    }

    public CredentialVariables getCredentialVariables() {
        return credentialVariables;
    }

    public ThemeVariables getThemeVariables() {
        return themeVariables;
    }

    public MetaVariables getMetaVariables() {
        return metaVariables;
    }

    public SharedColabVariables getSharedColabVariables() {
        return sharedColabVariables;
    }

    public MessageVariables getMessageVariables() {
        return messageVariables;
    }

    public SocialMediaVariables getSocialMediaVariables() {
        return socialMediaVariables;
    }

    public ServerEnvironment getServerEnvironment() {
        return serverEnvironment;
    }

    public String getColabName() {
        return colabName;
    }

    public String getColabLongName() {
        return colabLongName;
    }

    public String getColabShortName() {
        return colabShortName;
    }

    public String getColabUrl() {
        return colabUrl;
    }

    public String getColabUrlProduction() {
        return colabUrlProduction;
    }

    public String getBlogAdminUrl() {
        return blogAdminUrl;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public ContestType getDefaultContestType() {
        return defaultContestType;
    }

    public String getRequestUri() {
        return requestUri;
    }
}

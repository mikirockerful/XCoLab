package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.impl.ProposalAttributeImpl;
import com.ext.portlet.model.impl.ProposalAttributeModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.AnalyticsUserEventPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
import com.ext.portlet.service.persistence.ContestPhaseRibbonTypePersistence;
import com.ext.portlet.service.persistence.ContestPhaseTypePersistence;
import com.ext.portlet.service.persistence.ContestTeamMemberPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryGroupPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryPersistence;
import com.ext.portlet.service.persistence.DiscussionMessageFlagPersistence;
import com.ext.portlet.service.persistence.DiscussionMessagePersistence;
import com.ext.portlet.service.persistence.EmailListPersistence;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.service.persistence.FocusAreaPersistence;
import com.ext.portlet.service.persistence.LandingPagePersistence;
import com.ext.portlet.service.persistence.MessagePersistence;
import com.ext.portlet.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.service.persistence.MessagingIgnoredRecipientsPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionTypePersistence;
import com.ext.portlet.service.persistence.MessagingMessagePersistence;
import com.ext.portlet.service.persistence.MessagingMessageRecipientPersistence;
import com.ext.portlet.service.persistence.MessagingRedirectLinkPersistence;
import com.ext.portlet.service.persistence.MessagingUserPreferencesPersistence;
import com.ext.portlet.service.persistence.ModelCategoryPersistence;
import com.ext.portlet.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.service.persistence.ModelGlobalPreferencePersistence;
import com.ext.portlet.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.service.persistence.ModelOutputItemPersistence;
import com.ext.portlet.service.persistence.ModelPositionPersistence;
import com.ext.portlet.service.persistence.OntologySpacePersistence;
import com.ext.portlet.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.service.persistence.OntologyTermPersistence;
import com.ext.portlet.service.persistence.Plan2ProposalPersistence;
import com.ext.portlet.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.service.persistence.PlanAttributePersistence;
import com.ext.portlet.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.service.persistence.PlanFanPersistence;
import com.ext.portlet.service.persistence.PlanItemGroupPersistence;
import com.ext.portlet.service.persistence.PlanItemPersistence;
import com.ext.portlet.service.persistence.PlanMetaPersistence;
import com.ext.portlet.service.persistence.PlanModelRunPersistence;
import com.ext.portlet.service.persistence.PlanPositionItemPersistence;
import com.ext.portlet.service.persistence.PlanPositionPersistence;
import com.ext.portlet.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.service.persistence.PlanTypePersistence;
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalSupporterPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;
import com.ext.portlet.service.persistence.ProposalVotePersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the proposal attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributePersistence
 * @see ProposalAttributeUtil
 * @generated
 */
public class ProposalAttributePersistenceImpl extends BasePersistenceImpl<ProposalAttribute>
    implements ProposalAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalAttributeUtil} to access the proposal attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDVERSION =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalIdVersion",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDVERSION =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalIdVersion",
            new String[] { Long.class.getName(), Integer.class.getName() },
            ProposalAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalAttributeModelImpl.VERSION_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDVERSION = new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdVersion",
            new String[] { Long.class.getName(), Integer.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                Integer.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                Integer.class.getName()
            },
            ProposalAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalAttributeModelImpl.VERSION_COLUMN_BITMASK |
            ProposalAttributeModelImpl.VERSIONWHENCREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                Integer.class.getName()
            });
    public static final FinderPath FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByProposalIdVersionNameAdditionalId",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                String.class.getName(), Long.class.getName()
            },
            ProposalAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalAttributeModelImpl.VERSION_COLUMN_BITMASK |
            ProposalAttributeModelImpl.NAME_COLUMN_BITMASK |
            ProposalAttributeModelImpl.ADDITIONALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDVERSIONNAMEADDITIONALID =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdVersionNameAdditionalId",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                String.class.getName(), Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                Integer.class.getName(), String.class.getName(),
                Long.class.getName()
            },
            ProposalAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalAttributeModelImpl.VERSION_COLUMN_BITMASK |
            ProposalAttributeModelImpl.VERSIONWHENCREATED_COLUMN_BITMASK |
            ProposalAttributeModelImpl.NAME_COLUMN_BITMASK |
            ProposalAttributeModelImpl.ADDITIONALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID =
        new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId",
            new String[] {
                Long.class.getName(), Integer.class.getName(),
                Integer.class.getName(), String.class.getName(),
                Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALATTRIBUTE = "SELECT proposalAttribute FROM ProposalAttribute proposalAttribute";
    private static final String _SQL_SELECT_PROPOSALATTRIBUTE_WHERE = "SELECT proposalAttribute FROM ProposalAttribute proposalAttribute WHERE ";
    private static final String _SQL_COUNT_PROPOSALATTRIBUTE = "SELECT COUNT(proposalAttribute) FROM ProposalAttribute proposalAttribute";
    private static final String _SQL_COUNT_PROPOSALATTRIBUTE_WHERE = "SELECT COUNT(proposalAttribute) FROM ProposalAttribute proposalAttribute WHERE ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSION_PROPOSALID_2 = "proposalAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSION_VERSION_2 = "proposalAttribute.version = ?";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_PROPOSALID_2 =
        "proposalAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSION_2 =
        "proposalAttribute.version >= ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSIONWHENCREATED_2 =
        "proposalAttribute.versionWhenCreated <= ?";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_PROPOSALID_2 =
        "proposalAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_VERSION_2 =
        "proposalAttribute.version = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_1 =
        "proposalAttribute.name IS NULL AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_2 =
        "proposalAttribute.name = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_3 =
        "(proposalAttribute.name IS NULL OR proposalAttribute.name = ?) AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_ADDITIONALID_2 =
        "proposalAttribute.additionalId = ?";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_PROPOSALID_2 =
        "proposalAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_VERSION_2 =
        "proposalAttribute.version >= ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_VERSIONWHENCREATED_2 =
        "proposalAttribute.versionWhenCreated <= ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_1 =
        "proposalAttribute.name IS NULL AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_2 =
        "proposalAttribute.name = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_3 =
        "(proposalAttribute.name IS NULL OR proposalAttribute.name = ?) AND ";
    private static final String _FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_ADDITIONALID_2 =
        "proposalAttribute.additionalId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalAttributePersistenceImpl.class);
    private static ProposalAttribute _nullProposalAttribute = new ProposalAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalAttribute> toCacheModel() {
                return _nullProposalAttributeCacheModel;
            }
        };

    private static CacheModel<ProposalAttribute> _nullProposalAttributeCacheModel =
        new CacheModel<ProposalAttribute>() {
            public ProposalAttribute toEntityModel() {
                return _nullProposalAttribute;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = AnalyticsUserEventPersistence.class)
    protected AnalyticsUserEventPersistence analyticsUserEventPersistence;
    @BeanReference(type = BalloonStatsEntryPersistence.class)
    protected BalloonStatsEntryPersistence balloonStatsEntryPersistence;
    @BeanReference(type = ContestPersistence.class)
    protected ContestPersistence contestPersistence;
    @BeanReference(type = ContestDebatePersistence.class)
    protected ContestDebatePersistence contestDebatePersistence;
    @BeanReference(type = ContestPhasePersistence.class)
    protected ContestPhasePersistence contestPhasePersistence;
    @BeanReference(type = ContestPhaseColumnPersistence.class)
    protected ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(type = ContestPhaseRibbonTypePersistence.class)
    protected ContestPhaseRibbonTypePersistence contestPhaseRibbonTypePersistence;
    @BeanReference(type = ContestPhaseTypePersistence.class)
    protected ContestPhaseTypePersistence contestPhaseTypePersistence;
    @BeanReference(type = ContestTeamMemberPersistence.class)
    protected ContestTeamMemberPersistence contestTeamMemberPersistence;
    @BeanReference(type = DiscussionCategoryPersistence.class)
    protected DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(type = DiscussionCategoryGroupPersistence.class)
    protected DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(type = DiscussionMessagePersistence.class)
    protected DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(type = DiscussionMessageFlagPersistence.class)
    protected DiscussionMessageFlagPersistence discussionMessageFlagPersistence;
    @BeanReference(type = EmailListPersistence.class)
    protected EmailListPersistence emailListPersistence;
    @BeanReference(type = FocusAreaPersistence.class)
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(type = FocusAreaOntologyTermPersistence.class)
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;
    @BeanReference(type = LandingPagePersistence.class)
    protected LandingPagePersistence landingPagePersistence;
    @BeanReference(type = MessagePersistence.class)
    protected MessagePersistence messagePersistence;
    @BeanReference(type = MessageRecipientStatusPersistence.class)
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(type = MessagingIgnoredRecipientsPersistence.class)
    protected MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence;
    @BeanReference(type = MessagingMessagePersistence.class)
    protected MessagingMessagePersistence messagingMessagePersistence;
    @BeanReference(type = MessagingMessageConversionPersistence.class)
    protected MessagingMessageConversionPersistence messagingMessageConversionPersistence;
    @BeanReference(type = MessagingMessageConversionTypePersistence.class)
    protected MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence;
    @BeanReference(type = MessagingMessageRecipientPersistence.class)
    protected MessagingMessageRecipientPersistence messagingMessageRecipientPersistence;
    @BeanReference(type = MessagingRedirectLinkPersistence.class)
    protected MessagingRedirectLinkPersistence messagingRedirectLinkPersistence;
    @BeanReference(type = MessagingUserPreferencesPersistence.class)
    protected MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
    @BeanReference(type = ModelCategoryPersistence.class)
    protected ModelCategoryPersistence modelCategoryPersistence;
    @BeanReference(type = ModelDiscussionPersistence.class)
    protected ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(type = ModelGlobalPreferencePersistence.class)
    protected ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(type = ModelInputGroupPersistence.class)
    protected ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(type = ModelInputItemPersistence.class)
    protected ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(type = ModelOutputChartOrderPersistence.class)
    protected ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(type = ModelOutputItemPersistence.class)
    protected ModelOutputItemPersistence modelOutputItemPersistence;
    @BeanReference(type = ModelPositionPersistence.class)
    protected ModelPositionPersistence modelPositionPersistence;
    @BeanReference(type = OntologySpacePersistence.class)
    protected OntologySpacePersistence ontologySpacePersistence;
    @BeanReference(type = OntologyTermPersistence.class)
    protected OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(type = OntologyTermEntityPersistence.class)
    protected OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(type = Plan2ProposalPersistence.class)
    protected Plan2ProposalPersistence plan2ProposalPersistence;
    @BeanReference(type = PlanAttributePersistence.class)
    protected PlanAttributePersistence planAttributePersistence;
    @BeanReference(type = PlanAttributeFilterPersistence.class)
    protected PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(type = PlanColumnSettingsPersistence.class)
    protected PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(type = PlanDescriptionPersistence.class)
    protected PlanDescriptionPersistence planDescriptionPersistence;
    @BeanReference(type = PlanFanPersistence.class)
    protected PlanFanPersistence planFanPersistence;
    @BeanReference(type = PlanItemPersistence.class)
    protected PlanItemPersistence planItemPersistence;
    @BeanReference(type = PlanItemGroupPersistence.class)
    protected PlanItemGroupPersistence planItemGroupPersistence;
    @BeanReference(type = PlanMetaPersistence.class)
    protected PlanMetaPersistence planMetaPersistence;
    @BeanReference(type = PlanModelRunPersistence.class)
    protected PlanModelRunPersistence planModelRunPersistence;
    @BeanReference(type = PlanPositionPersistence.class)
    protected PlanPositionPersistence planPositionPersistence;
    @BeanReference(type = PlanPositionItemPersistence.class)
    protected PlanPositionItemPersistence planPositionItemPersistence;
    @BeanReference(type = PlanPositionsPersistence.class)
    protected PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(type = PlanPropertyFilterPersistence.class)
    protected PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(type = PlanRelatedPersistence.class)
    protected PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(type = PlanSectionPersistence.class)
    protected PlanSectionPersistence planSectionPersistence;
    @BeanReference(type = PlanSectionDefinitionPersistence.class)
    protected PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(type = PlanSectionPlanMapPersistence.class)
    protected PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(type = PlansFilterPersistence.class)
    protected PlansFilterPersistence plansFilterPersistence;
    @BeanReference(type = PlansFilterPositionPersistence.class)
    protected PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(type = PlansUserSettingsPersistence.class)
    protected PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(type = PlanTeamHistoryPersistence.class)
    protected PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(type = PlanTemplatePersistence.class)
    protected PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(type = PlanTemplateSectionPersistence.class)
    protected PlanTemplateSectionPersistence planTemplateSectionPersistence;
    @BeanReference(type = PlanTypePersistence.class)
    protected PlanTypePersistence planTypePersistence;
    @BeanReference(type = PlanTypeAttributePersistence.class)
    protected PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(type = PlanTypeColumnPersistence.class)
    protected PlanTypeColumnPersistence planTypeColumnPersistence;
    @BeanReference(type = PlanVotePersistence.class)
    protected PlanVotePersistence planVotePersistence;
    @BeanReference(type = ProposalPersistence.class)
    protected ProposalPersistence proposalPersistence;
    @BeanReference(type = Proposal2PhasePersistence.class)
    protected Proposal2PhasePersistence proposal2PhasePersistence;
    @BeanReference(type = ProposalAttributePersistence.class)
    protected ProposalAttributePersistence proposalAttributePersistence;
    @BeanReference(type = ProposalAttributeTypePersistence.class)
    protected ProposalAttributeTypePersistence proposalAttributeTypePersistence;
    @BeanReference(type = ProposalContestPhaseAttributePersistence.class)
    protected ProposalContestPhaseAttributePersistence proposalContestPhaseAttributePersistence;
    @BeanReference(type = ProposalContestPhaseAttributeTypePersistence.class)
    protected ProposalContestPhaseAttributeTypePersistence proposalContestPhaseAttributeTypePersistence;
    @BeanReference(type = ProposalSupporterPersistence.class)
    protected ProposalSupporterPersistence proposalSupporterPersistence;
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = ProposalVotePersistence.class)
    protected ProposalVotePersistence proposalVotePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the proposal attribute in the entity cache if it is enabled.
     *
     * @param proposalAttribute the proposal attribute
     */
    public void cacheResult(ProposalAttribute proposalAttribute) {
        EntityCacheUtil.putResult(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeImpl.class, proposalAttribute.getPrimaryKey(),
            proposalAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
            new Object[] {
                Long.valueOf(proposalAttribute.getProposalId()),
                Integer.valueOf(proposalAttribute.getVersion()),
                
            proposalAttribute.getName(),
                Long.valueOf(proposalAttribute.getAdditionalId())
            }, proposalAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
            new Object[] {
                Long.valueOf(proposalAttribute.getProposalId()),
                Integer.valueOf(proposalAttribute.getVersion()),
                Integer.valueOf(proposalAttribute.getVersionWhenCreated()),
                
            proposalAttribute.getName(),
                Long.valueOf(proposalAttribute.getAdditionalId())
            }, proposalAttribute);

        proposalAttribute.resetOriginalValues();
    }

    /**
     * Caches the proposal attributes in the entity cache if it is enabled.
     *
     * @param proposalAttributes the proposal attributes
     */
    public void cacheResult(List<ProposalAttribute> proposalAttributes) {
        for (ProposalAttribute proposalAttribute : proposalAttributes) {
            if (EntityCacheUtil.getResult(
                        ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalAttributeImpl.class,
                        proposalAttribute.getPrimaryKey()) == null) {
                cacheResult(proposalAttribute);
            } else {
                proposalAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalAttribute proposalAttribute) {
        EntityCacheUtil.removeResult(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeImpl.class, proposalAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(proposalAttribute);
    }

    @Override
    public void clearCache(List<ProposalAttribute> proposalAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalAttribute proposalAttribute : proposalAttributes) {
            EntityCacheUtil.removeResult(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeImpl.class, proposalAttribute.getPrimaryKey());

            clearUniqueFindersCache(proposalAttribute);
        }
    }

    protected void clearUniqueFindersCache(ProposalAttribute proposalAttribute) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
            new Object[] {
                Long.valueOf(proposalAttribute.getProposalId()),
                Integer.valueOf(proposalAttribute.getVersion()),
                
            proposalAttribute.getName(),
                Long.valueOf(proposalAttribute.getAdditionalId())
            });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
            new Object[] {
                Long.valueOf(proposalAttribute.getProposalId()),
                Integer.valueOf(proposalAttribute.getVersion()),
                Integer.valueOf(proposalAttribute.getVersionWhenCreated()),
                
            proposalAttribute.getName(),
                Long.valueOf(proposalAttribute.getAdditionalId())
            });
    }

    /**
     * Creates a new proposal attribute with the primary key. Does not add the proposal attribute to the database.
     *
     * @param id the primary key for the new proposal attribute
     * @return the new proposal attribute
     */
    public ProposalAttribute create(long id) {
        ProposalAttribute proposalAttribute = new ProposalAttributeImpl();

        proposalAttribute.setNew(true);
        proposalAttribute.setPrimaryKey(id);

        return proposalAttribute;
    }

    /**
     * Removes the proposal attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal attribute
     * @return the proposal attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute remove(long id)
        throws NoSuchProposalAttributeException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the proposal attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal attribute
     * @return the proposal attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttribute remove(Serializable primaryKey)
        throws NoSuchProposalAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalAttribute proposalAttribute = (ProposalAttribute) session.get(ProposalAttributeImpl.class,
                    primaryKey);

            if (proposalAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalAttribute);
        } catch (NoSuchProposalAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalAttribute removeImpl(ProposalAttribute proposalAttribute)
        throws SystemException {
        proposalAttribute = toUnwrappedModel(proposalAttribute);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, proposalAttribute);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(proposalAttribute);

        return proposalAttribute;
    }

    @Override
    public ProposalAttribute updateImpl(
        com.ext.portlet.model.ProposalAttribute proposalAttribute, boolean merge)
        throws SystemException {
        proposalAttribute = toUnwrappedModel(proposalAttribute);

        boolean isNew = proposalAttribute.isNew();

        ProposalAttributeModelImpl proposalAttributeModelImpl = (ProposalAttributeModelImpl) proposalAttribute;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, proposalAttribute, merge);

            proposalAttribute.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProposalAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDVERSION.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalAttributeModelImpl.getOriginalProposalId()),
                        Integer.valueOf(proposalAttributeModelImpl.getOriginalVersion())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSION,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDVERSION,
                    args);

                args = new Object[] {
                        Long.valueOf(proposalAttributeModelImpl.getProposalId()),
                        Integer.valueOf(proposalAttributeModelImpl.getVersion())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSION,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDVERSION,
                    args);
            }

            if ((proposalAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalAttributeModelImpl.getOriginalProposalId()),
                        Integer.valueOf(proposalAttributeModelImpl.getOriginalVersion()),
                        Integer.valueOf(proposalAttributeModelImpl.getOriginalVersionWhenCreated())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL,
                    args);

                args = new Object[] {
                        Long.valueOf(proposalAttributeModelImpl.getProposalId()),
                        Integer.valueOf(proposalAttributeModelImpl.getVersion()),
                        Integer.valueOf(proposalAttributeModelImpl.getVersionWhenCreated())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeImpl.class, proposalAttribute.getPrimaryKey(),
            proposalAttribute);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                new Object[] {
                    Long.valueOf(proposalAttribute.getProposalId()),
                    Integer.valueOf(proposalAttribute.getVersion()),
                    
                proposalAttribute.getName(),
                    Long.valueOf(proposalAttribute.getAdditionalId())
                }, proposalAttribute);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                new Object[] {
                    Long.valueOf(proposalAttribute.getProposalId()),
                    Integer.valueOf(proposalAttribute.getVersion()),
                    Integer.valueOf(proposalAttribute.getVersionWhenCreated()),
                    
                proposalAttribute.getName(),
                    Long.valueOf(proposalAttribute.getAdditionalId())
                }, proposalAttribute);
        } else {
            if ((proposalAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalAttributeModelImpl.getOriginalProposalId()),
                        Integer.valueOf(proposalAttributeModelImpl.getOriginalVersion()),
                        
                        proposalAttributeModelImpl.getOriginalName(),
                        Long.valueOf(proposalAttributeModelImpl.getOriginalAdditionalId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                    new Object[] {
                        Long.valueOf(proposalAttribute.getProposalId()),
                        Integer.valueOf(proposalAttribute.getVersion()),
                        
                    proposalAttribute.getName(),
                        Long.valueOf(proposalAttribute.getAdditionalId())
                    }, proposalAttribute);
            }

            if ((proposalAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalAttributeModelImpl.getOriginalProposalId()),
                        Integer.valueOf(proposalAttributeModelImpl.getOriginalVersion()),
                        Integer.valueOf(proposalAttributeModelImpl.getOriginalVersionWhenCreated()),
                        
                        proposalAttributeModelImpl.getOriginalName(),
                        Long.valueOf(proposalAttributeModelImpl.getOriginalAdditionalId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                    new Object[] {
                        Long.valueOf(proposalAttribute.getProposalId()),
                        Integer.valueOf(proposalAttribute.getVersion()),
                        Integer.valueOf(
                            proposalAttribute.getVersionWhenCreated()),
                        
                    proposalAttribute.getName(),
                        Long.valueOf(proposalAttribute.getAdditionalId())
                    }, proposalAttribute);
            }
        }

        return proposalAttribute;
    }

    protected ProposalAttribute toUnwrappedModel(
        ProposalAttribute proposalAttribute) {
        if (proposalAttribute instanceof ProposalAttributeImpl) {
            return proposalAttribute;
        }

        ProposalAttributeImpl proposalAttributeImpl = new ProposalAttributeImpl();

        proposalAttributeImpl.setNew(proposalAttribute.isNew());
        proposalAttributeImpl.setPrimaryKey(proposalAttribute.getPrimaryKey());

        proposalAttributeImpl.setId(proposalAttribute.getId());
        proposalAttributeImpl.setProposalId(proposalAttribute.getProposalId());
        proposalAttributeImpl.setVersion(proposalAttribute.getVersion());
        proposalAttributeImpl.setVersionWhenCreated(proposalAttribute.getVersionWhenCreated());
        proposalAttributeImpl.setName(proposalAttribute.getName());
        proposalAttributeImpl.setAdditionalId(proposalAttribute.getAdditionalId());
        proposalAttributeImpl.setNumericValue(proposalAttribute.getNumericValue());
        proposalAttributeImpl.setStringValue(proposalAttribute.getStringValue());
        proposalAttributeImpl.setRealValue(proposalAttribute.getRealValue());

        return proposalAttributeImpl;
    }

    /**
     * Returns the proposal attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal attribute
     * @return the proposal attribute
     * @throws com.liferay.portal.NoSuchModelException if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttribute findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the proposal attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalAttributeException} if it could not be found.
     *
     * @param id the primary key of the proposal attribute
     * @return the proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByPrimaryKey(long id)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = fetchByPrimaryKey(id);

        if (proposalAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchProposalAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return proposalAttribute;
    }

    /**
     * Returns the proposal attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal attribute
     * @return the proposal attribute, or <code>null</code> if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttribute fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the proposal attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal attribute
     * @return the proposal attribute, or <code>null</code> if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute fetchByPrimaryKey(long id)
        throws SystemException {
        ProposalAttribute proposalAttribute = (ProposalAttribute) EntityCacheUtil.getResult(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeImpl.class, id);

        if (proposalAttribute == _nullProposalAttribute) {
            return null;
        }

        if (proposalAttribute == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                proposalAttribute = (ProposalAttribute) session.get(ProposalAttributeImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (proposalAttribute != null) {
                    cacheResult(proposalAttribute);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ProposalAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalAttributeImpl.class, id, _nullProposalAttribute);
                }

                closeSession(session);
            }
        }

        return proposalAttribute;
    }

    /**
     * Returns all the proposal attributes where proposalId = &#63; and version = &#63;.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @return the matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findByProposalIdVersion(long proposalId,
        int version) throws SystemException {
        return findByProposalIdVersion(proposalId, version, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal attributes where proposalId = &#63; and version = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param start the lower bound of the range of proposal attributes
     * @param end the upper bound of the range of proposal attributes (not inclusive)
     * @return the range of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findByProposalIdVersion(long proposalId,
        int version, int start, int end) throws SystemException {
        return findByProposalIdVersion(proposalId, version, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal attributes where proposalId = &#63; and version = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param start the lower bound of the range of proposal attributes
     * @param end the upper bound of the range of proposal attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findByProposalIdVersion(long proposalId,
        int version, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDVERSION;
            finderArgs = new Object[] { proposalId, version };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDVERSION;
            finderArgs = new Object[] {
                    proposalId, version,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalAttribute> list = (List<ProposalAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSION_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSION_VERSION_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                list = (List<ProposalAttribute>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first proposal attribute in the ordered set where proposalId = &#63; and version = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByProposalIdVersion_First(long proposalId,
        int version, OrderByComparator orderByComparator)
        throws NoSuchProposalAttributeException, SystemException {
        List<ProposalAttribute> list = findByProposalIdVersion(proposalId,
                version, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", version=");
            msg.append(version);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last proposal attribute in the ordered set where proposalId = &#63; and version = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByProposalIdVersion_Last(long proposalId,
        int version, OrderByComparator orderByComparator)
        throws NoSuchProposalAttributeException, SystemException {
        int count = countByProposalIdVersion(proposalId, version);

        List<ProposalAttribute> list = findByProposalIdVersion(proposalId,
                version, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", version=");
            msg.append(version);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the proposal attributes before and after the current proposal attribute in the ordered set where proposalId = &#63; and version = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current proposal attribute
     * @param proposalId the proposal ID
     * @param version the version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute[] findByProposalIdVersion_PrevAndNext(long id,
        long proposalId, int version, OrderByComparator orderByComparator)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalAttribute[] array = new ProposalAttributeImpl[3];

            array[0] = getByProposalIdVersion_PrevAndNext(session,
                    proposalAttribute, proposalId, version, orderByComparator,
                    true);

            array[1] = proposalAttribute;

            array[2] = getByProposalIdVersion_PrevAndNext(session,
                    proposalAttribute, proposalId, version, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalAttribute getByProposalIdVersion_PrevAndNext(
        Session session, ProposalAttribute proposalAttribute, long proposalId,
        int version, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALATTRIBUTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALIDVERSION_PROPOSALID_2);

        query.append(_FINDER_COLUMN_PROPOSALIDVERSION_VERSION_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        qPos.add(version);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the proposal attributes where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @return the matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(
        long proposalId, int version, int versionWhenCreated)
        throws SystemException {
        return findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId,
            version, versionWhenCreated, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the proposal attributes where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param start the lower bound of the range of proposal attributes
     * @param end the upper bound of the range of proposal attributes (not inclusive)
     * @return the range of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(
        long proposalId, int version, int versionWhenCreated, int start, int end)
        throws SystemException {
        return findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId,
            version, versionWhenCreated, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal attributes where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param start the lower bound of the range of proposal attributes
     * @param end the upper bound of the range of proposal attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(
        long proposalId, int version, int versionWhenCreated, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL;
            finderArgs = new Object[] { proposalId, version, versionWhenCreated };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL;
            finderArgs = new Object[] {
                    proposalId, version, versionWhenCreated,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalAttribute> list = (List<ProposalAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(5 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSION_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSIONWHENCREATED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                qPos.add(versionWhenCreated);

                list = (List<ProposalAttribute>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first proposal attribute in the ordered set where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_First(
        long proposalId, int version, int versionWhenCreated,
        OrderByComparator orderByComparator)
        throws NoSuchProposalAttributeException, SystemException {
        List<ProposalAttribute> list = findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId,
                version, versionWhenCreated, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(8);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", version=");
            msg.append(version);

            msg.append(", versionWhenCreated=");
            msg.append(versionWhenCreated);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last proposal attribute in the ordered set where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_Last(
        long proposalId, int version, int versionWhenCreated,
        OrderByComparator orderByComparator)
        throws NoSuchProposalAttributeException, SystemException {
        int count = countByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId,
                version, versionWhenCreated);

        List<ProposalAttribute> list = findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId,
                version, versionWhenCreated, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(8);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", version=");
            msg.append(version);

            msg.append(", versionWhenCreated=");
            msg.append(versionWhenCreated);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the proposal attributes before and after the current proposal attribute in the ordered set where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current proposal attribute
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a proposal attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute[] findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_PrevAndNext(
        long id, long proposalId, int version, int versionWhenCreated,
        OrderByComparator orderByComparator)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalAttribute[] array = new ProposalAttributeImpl[3];

            array[0] = getByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_PrevAndNext(session,
                    proposalAttribute, proposalId, version, versionWhenCreated,
                    orderByComparator, true);

            array[1] = proposalAttribute;

            array[2] = getByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_PrevAndNext(session,
                    proposalAttribute, proposalId, version, versionWhenCreated,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalAttribute getByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_PrevAndNext(
        Session session, ProposalAttribute proposalAttribute, long proposalId,
        int version, int versionWhenCreated,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALATTRIBUTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_PROPOSALID_2);

        query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSION_2);

        query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSIONWHENCREATED_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        qPos.add(version);

        qPos.add(versionWhenCreated);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the proposal attribute where proposalId = &#63; and version = &#63; and name = &#63; and additionalId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalAttributeException} if it could not be found.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByProposalIdVersionNameAdditionalId(
        long proposalId, int version, String name, long additionalId)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = fetchByProposalIdVersionNameAdditionalId(proposalId,
                version, name, additionalId);

        if (proposalAttribute == null) {
            StringBundler msg = new StringBundler(10);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", version=");
            msg.append(version);

            msg.append(", name=");
            msg.append(name);

            msg.append(", additionalId=");
            msg.append(additionalId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalAttributeException(msg.toString());
        }

        return proposalAttribute;
    }

    /**
     * Returns the proposal attribute where proposalId = &#63; and version = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal attribute, or <code>null</code> if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute fetchByProposalIdVersionNameAdditionalId(
        long proposalId, int version, String name, long additionalId)
        throws SystemException {
        return fetchByProposalIdVersionNameAdditionalId(proposalId, version,
            name, additionalId, true);
    }

    /**
     * Returns the proposal attribute where proposalId = &#63; and version = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param name the name
     * @param additionalId the additional ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal attribute, or <code>null</code> if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute fetchByProposalIdVersionNameAdditionalId(
        long proposalId, int version, String name, long additionalId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, version, name, additionalId
            };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_SELECT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_VERSION_2);

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_2);
                }
            }

            query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                if (name != null) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                List<ProposalAttribute> list = q.list();

                result = list;

                ProposalAttribute proposalAttribute = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                        finderArgs, list);
                } else {
                    proposalAttribute = list.get(0);

                    cacheResult(proposalAttribute);

                    if ((proposalAttribute.getProposalId() != proposalId) ||
                            (proposalAttribute.getVersion() != version) ||
                            (proposalAttribute.getName() == null) ||
                            !proposalAttribute.getName().equals(name) ||
                            (proposalAttribute.getAdditionalId() != additionalId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                            finderArgs, proposalAttribute);
                    }
                }

                return proposalAttribute;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (ProposalAttribute) result;
            }
        }
    }

    /**
     * Returns the proposal attribute where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63; and name = &#63; and additionalId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalAttributeException} if it could not be found.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal attribute
     * @throws com.ext.portlet.NoSuchProposalAttributeException if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
        long proposalId, int version, int versionWhenCreated, String name,
        long additionalId)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = fetchByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(proposalId,
                version, versionWhenCreated, name, additionalId);

        if (proposalAttribute == null) {
            StringBundler msg = new StringBundler(12);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", version=");
            msg.append(version);

            msg.append(", versionWhenCreated=");
            msg.append(versionWhenCreated);

            msg.append(", name=");
            msg.append(name);

            msg.append(", additionalId=");
            msg.append(additionalId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalAttributeException(msg.toString());
        }

        return proposalAttribute;
    }

    /**
     * Returns the proposal attribute where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal attribute, or <code>null</code> if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute fetchByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
        long proposalId, int version, int versionWhenCreated, String name,
        long additionalId) throws SystemException {
        return fetchByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(proposalId,
            version, versionWhenCreated, name, additionalId, true);
    }

    /**
     * Returns the proposal attribute where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param name the name
     * @param additionalId the additional ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal attribute, or <code>null</code> if a matching proposal attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttribute fetchByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
        long proposalId, int version, int versionWhenCreated, String name,
        long additionalId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, version, versionWhenCreated, name, additionalId
            };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(6);

            query.append(_SQL_SELECT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_VERSION_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_VERSIONWHENCREATED_2);

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_2);
                }
            }

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                qPos.add(versionWhenCreated);

                if (name != null) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                List<ProposalAttribute> list = q.list();

                result = list;

                ProposalAttribute proposalAttribute = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                        finderArgs, list);
                } else {
                    proposalAttribute = list.get(0);

                    cacheResult(proposalAttribute);

                    if ((proposalAttribute.getProposalId() != proposalId) ||
                            (proposalAttribute.getVersion() != version) ||
                            (proposalAttribute.getVersionWhenCreated() != versionWhenCreated) ||
                            (proposalAttribute.getName() == null) ||
                            !proposalAttribute.getName().equals(name) ||
                            (proposalAttribute.getAdditionalId() != additionalId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                            finderArgs, proposalAttribute);
                    }
                }

                return proposalAttribute;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (ProposalAttribute) result;
            }
        }
    }

    /**
     * Returns all the proposal attributes.
     *
     * @return the proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal attributes
     * @param end the upper bound of the range of proposal attributes (not inclusive)
     * @return the range of proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal attributes
     * @param end the upper bound of the range of proposal attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttribute> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<ProposalAttribute> list = (List<ProposalAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALATTRIBUTE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ProposalAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ProposalAttribute>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the proposal attributes where proposalId = &#63; and version = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalIdVersion(long proposalId, int version)
        throws SystemException {
        for (ProposalAttribute proposalAttribute : findByProposalIdVersion(
                proposalId, version)) {
            remove(proposalAttribute);
        }
    }

    /**
     * Removes all the proposal attributes where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(
        long proposalId, int version, int versionWhenCreated)
        throws SystemException {
        for (ProposalAttribute proposalAttribute : findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(
                proposalId, version, versionWhenCreated)) {
            remove(proposalAttribute);
        }
    }

    /**
     * Removes the proposal attribute where proposalId = &#63; and version = &#63; and name = &#63; and additionalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param name the name
     * @param additionalId the additional ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalIdVersionNameAdditionalId(long proposalId,
        int version, String name, long additionalId)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = findByProposalIdVersionNameAdditionalId(proposalId,
                version, name, additionalId);

        remove(proposalAttribute);
    }

    /**
     * Removes the proposal attribute where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63; and name = &#63; and additionalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param name the name
     * @param additionalId the additional ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
        long proposalId, int version, int versionWhenCreated, String name,
        long additionalId)
        throws NoSuchProposalAttributeException, SystemException {
        ProposalAttribute proposalAttribute = findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(proposalId,
                version, versionWhenCreated, name, additionalId);

        remove(proposalAttribute);
    }

    /**
     * Removes all the proposal attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ProposalAttribute proposalAttribute : findAll()) {
            remove(proposalAttribute);
        }
    }

    /**
     * Returns the number of proposal attributes where proposalId = &#63; and version = &#63;.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @return the number of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalIdVersion(long proposalId, int version)
        throws SystemException {
        Object[] finderArgs = new Object[] { proposalId, version };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSION,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSION_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSION_VERSION_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSION,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal attributes where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63;.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @return the number of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(
        long proposalId, int version, int versionWhenCreated)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, version, versionWhenCreated
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSION_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_VERSIONWHENCREATED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                qPos.add(versionWhenCreated);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal attributes where proposalId = &#63; and version = &#63; and name = &#63; and additionalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param name the name
     * @param additionalId the additional ID
     * @return the number of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalIdVersionNameAdditionalId(long proposalId,
        int version, String name, long additionalId) throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, version, name, additionalId
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_VERSION_2);

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_NAME_2);
                }
            }

            query.append(_FINDER_COLUMN_PROPOSALIDVERSIONNAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                if (name != null) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDVERSIONNAMEADDITIONALID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal attributes where proposalId = &#63; and version &ge; &#63; and versionWhenCreated &le; &#63; and name = &#63; and additionalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param version the version
     * @param versionWhenCreated the version when created
     * @param name the name
     * @param additionalId the additional ID
     * @return the number of matching proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
        long proposalId, int version, int versionWhenCreated, String name,
        long additionalId) throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, version, versionWhenCreated, name, additionalId
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(6);

            query.append(_SQL_COUNT_PROPOSALATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_VERSION_2);

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_VERSIONWHENCREATED_2);

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_NAME_2);
                }
            }

            query.append(_FINDER_COLUMN_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(version);

                qPos.add(versionWhenCreated);

                if (name != null) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALID_VERSIONGREATEREQUAL_VERSIONWHENCREATEDLESSEREQUAL_NAMEADDITIONALID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal attributes.
     *
     * @return the number of proposal attributes
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PROPOSALATTRIBUTE);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the proposal attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalAttribute>> listenersList = new ArrayList<ModelListener<ProposalAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalAttribute>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}

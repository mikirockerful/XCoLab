package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the proposal contest phase attribute type local service. This utility wraps {@link com.ext.portlet.service.impl.ProposalContestPhaseAttributeTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypeLocalService
 * @see com.ext.portlet.service.base.ProposalContestPhaseAttributeTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalContestPhaseAttributeTypeLocalServiceImpl
 * @generated
 */
public class ProposalContestPhaseAttributeTypeLocalServiceUtil {
    private static ProposalContestPhaseAttributeTypeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalContestPhaseAttributeTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the proposal contest phase attribute type to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
    * @return the proposal contest phase attribute type that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType addProposalContestPhaseAttributeType(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addProposalContestPhaseAttributeType(proposalContestPhaseAttributeType);
    }

    /**
    * Creates a new proposal contest phase attribute type with the primary key. Does not add the proposal contest phase attribute type to the database.
    *
    * @param name the primary key for the new proposal contest phase attribute type
    * @return the new proposal contest phase attribute type
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType createProposalContestPhaseAttributeType(
        java.lang.String name) {
        return getService().createProposalContestPhaseAttributeType(name);
    }

    /**
    * Deletes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param name the primary key of the proposal contest phase attribute type
    * @throws PortalException if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposalContestPhaseAttributeType(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteProposalContestPhaseAttributeType(name);
    }

    /**
    * Deletes the proposal contest phase attribute type from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
    * @throws SystemException if a system exception occurred
    */
    public static void deleteProposalContestPhaseAttributeType(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService()
            .deleteProposalContestPhaseAttributeType(proposalContestPhaseAttributeType);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static com.ext.portlet.model.ProposalContestPhaseAttributeType fetchProposalContestPhaseAttributeType(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchProposalContestPhaseAttributeType(name);
    }

    /**
    * Returns the proposal contest phase attribute type with the primary key.
    *
    * @param name the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type
    * @throws PortalException if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType getProposalContestPhaseAttributeType(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalContestPhaseAttributeType(name);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal contest phase attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attribute types
    * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
    * @return the range of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> getProposalContestPhaseAttributeTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalContestPhaseAttributeTypes(start, end);
    }

    /**
    * Returns the number of proposal contest phase attribute types.
    *
    * @return the number of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public static int getProposalContestPhaseAttributeTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getProposalContestPhaseAttributeTypesCount();
    }

    /**
    * Updates the proposal contest phase attribute type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
    * @return the proposal contest phase attribute type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType updateProposalContestPhaseAttributeType(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateProposalContestPhaseAttributeType(proposalContestPhaseAttributeType);
    }

    /**
    * Updates the proposal contest phase attribute type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
    * @param merge whether to merge the proposal contest phase attribute type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal contest phase attribute type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ProposalContestPhaseAttributeType updateProposalContestPhaseAttributeType(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateProposalContestPhaseAttributeType(proposalContestPhaseAttributeType,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static void clearService() {
        _service = null;
    }

    public static ProposalContestPhaseAttributeTypeLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ProposalContestPhaseAttributeTypeLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ProposalContestPhaseAttributeTypeLocalService.class.getName(),
                    portletClassLoader);

            _service = new ProposalContestPhaseAttributeTypeLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ProposalContestPhaseAttributeTypeLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ProposalContestPhaseAttributeTypeLocalService.class);
        }

        return _service;
    }

    public void setService(
        ProposalContestPhaseAttributeTypeLocalService service) {
        MethodCache.remove(ProposalContestPhaseAttributeTypeLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ProposalContestPhaseAttributeTypeLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ProposalContestPhaseAttributeTypeLocalService.class);
    }
}

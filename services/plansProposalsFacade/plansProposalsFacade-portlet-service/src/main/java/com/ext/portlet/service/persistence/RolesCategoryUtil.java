package com.ext.portlet.service.persistence;

import com.ext.portlet.model.RolesCategory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the roles category service. This utility wraps {@link RolesCategoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RolesCategoryPersistence
 * @see RolesCategoryPersistenceImpl
 * @generated
 */
public class RolesCategoryUtil {
    private static RolesCategoryPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(RolesCategory rolesCategory) {
        getPersistence().clearCache(rolesCategory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<RolesCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RolesCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RolesCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static RolesCategory update(RolesCategory rolesCategory)
        throws SystemException {
        return getPersistence().update(rolesCategory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static RolesCategory update(RolesCategory rolesCategory,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(rolesCategory, serviceContext);
    }

    /**
    * Caches the roles category in the entity cache if it is enabled.
    *
    * @param rolesCategory the roles category
    */
    public static void cacheResult(
        com.ext.portlet.model.RolesCategory rolesCategory) {
        getPersistence().cacheResult(rolesCategory);
    }

    /**
    * Caches the roles categories in the entity cache if it is enabled.
    *
    * @param rolesCategories the roles categories
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.RolesCategory> rolesCategories) {
        getPersistence().cacheResult(rolesCategories);
    }

    /**
    * Creates a new roles category with the primary key. Does not add the roles category to the database.
    *
    * @param roleId the primary key for the new roles category
    * @return the new roles category
    */
    public static com.ext.portlet.model.RolesCategory create(long roleId) {
        return getPersistence().create(roleId);
    }

    /**
    * Removes the roles category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the roles category
    * @return the roles category that was removed
    * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.RolesCategory remove(long roleId)
        throws com.ext.portlet.NoSuchRolesCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(roleId);
    }

    public static com.ext.portlet.model.RolesCategory updateImpl(
        com.ext.portlet.model.RolesCategory rolesCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rolesCategory);
    }

    /**
    * Returns the roles category with the primary key or throws a {@link com.ext.portlet.NoSuchRolesCategoryException} if it could not be found.
    *
    * @param roleId the primary key of the roles category
    * @return the roles category
    * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.RolesCategory findByPrimaryKey(
        long roleId)
        throws com.ext.portlet.NoSuchRolesCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(roleId);
    }

    /**
    * Returns the roles category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param roleId the primary key of the roles category
    * @return the roles category, or <code>null</code> if a roles category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.RolesCategory fetchByPrimaryKey(
        long roleId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(roleId);
    }

    /**
    * Returns all the roles categories.
    *
    * @return the roles categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.RolesCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the roles categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.RolesCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of roles categories
    * @param end the upper bound of the range of roles categories (not inclusive)
    * @return the range of roles categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.RolesCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the roles categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.RolesCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of roles categories
    * @param end the upper bound of the range of roles categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of roles categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.RolesCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the roles categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of roles categories.
    *
    * @return the number of roles categories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RolesCategoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RolesCategoryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    RolesCategoryPersistence.class.getName());

            ReferenceRegistry.registerReference(RolesCategoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RolesCategoryPersistence persistence) {
    }
}

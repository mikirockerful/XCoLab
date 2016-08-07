package com.ext.portlet.service.base;

import com.ext.portlet.service.FocusAreaLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FocusAreaLocalServiceClpInvoker {
    private String _methodName0;
    private String[] _methodParameterTypes0;
    private String _methodName1;
    private String[] _methodParameterTypes1;
    private String _methodName2;
    private String[] _methodParameterTypes2;
    private String _methodName3;
    private String[] _methodParameterTypes3;
    private String _methodName4;
    private String[] _methodParameterTypes4;
    private String _methodName5;
    private String[] _methodParameterTypes5;
    private String _methodName6;
    private String[] _methodParameterTypes6;
    private String _methodName7;
    private String[] _methodParameterTypes7;
    private String _methodName8;
    private String[] _methodParameterTypes8;
    private String _methodName9;
    private String[] _methodParameterTypes9;
    private String _methodName10;
    private String[] _methodParameterTypes10;
    private String _methodName11;
    private String[] _methodParameterTypes11;
    private String _methodName12;
    private String[] _methodParameterTypes12;
    private String _methodName13;
    private String[] _methodParameterTypes13;
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;
    private String _methodName464;
    private String[] _methodParameterTypes464;
    private String _methodName465;
    private String[] _methodParameterTypes465;
    private String _methodName470;
    private String[] _methodParameterTypes470;
    private String _methodName471;
    private String[] _methodParameterTypes471;
    private String _methodName472;
    private String[] _methodParameterTypes472;
    private String _methodName473;
    private String[] _methodParameterTypes473;
    private String _methodName474;
    private String[] _methodParameterTypes474;
    private String _methodName475;
    private String[] _methodParameterTypes475;
    private String _methodName476;
    private String[] _methodParameterTypes476;

    public FocusAreaLocalServiceClpInvoker() {
        _methodName0 = "addFocusArea";

        _methodParameterTypes0 = new String[] { "com.ext.portlet.model.FocusArea" };

        _methodName1 = "createFocusArea";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteFocusArea";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteFocusArea";

        _methodParameterTypes3 = new String[] { "com.ext.portlet.model.FocusArea" };

        _methodName4 = "dynamicQuery";

        _methodParameterTypes4 = new String[] {  };

        _methodName5 = "dynamicQuery";

        _methodParameterTypes5 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName6 = "dynamicQuery";

        _methodParameterTypes6 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
            };

        _methodName7 = "dynamicQuery";

        _methodParameterTypes7 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName8 = "dynamicQueryCount";

        _methodParameterTypes8 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery"
            };

        _methodName9 = "dynamicQueryCount";

        _methodParameterTypes9 = new String[] {
                "com.liferay.portal.kernel.dao.orm.DynamicQuery",
                "com.liferay.portal.kernel.dao.orm.Projection"
            };

        _methodName10 = "fetchFocusArea";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getFocusArea";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getFocusAreas";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getFocusAreasCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateFocusArea";

        _methodParameterTypes15 = new String[] { "com.ext.portlet.model.FocusArea" };

        _methodName464 = "getBeanIdentifier";

        _methodParameterTypes464 = new String[] {  };

        _methodName465 = "setBeanIdentifier";

        _methodParameterTypes465 = new String[] { "java.lang.String" };

        _methodName470 = "store";

        _methodParameterTypes470 = new String[] {
                "com.ext.portlet.model.FocusArea"
            };

        _methodName471 = "getTerms";

        _methodParameterTypes471 = new String[] {
                "com.ext.portlet.model.FocusArea"
            };

        _methodName472 = "removeTerm";

        _methodParameterTypes472 = new String[] {
                "com.ext.portlet.model.FocusArea", "java.lang.Long"
            };

        _methodName473 = "addTerm";

        _methodParameterTypes473 = new String[] {
                "com.ext.portlet.model.FocusArea", "java.lang.Long"
            };

        _methodName474 = "tagClass";

        _methodParameterTypes474 = new String[] {
                "com.ext.portlet.model.FocusArea", "java.lang.Class",
                "java.lang.Long"
            };

        _methodName475 = "getOntologyTermFromFocusAreaWithOntologySpace";

        _methodParameterTypes475 = new String[] {
                "com.ext.portlet.model.FocusArea",
                "com.ext.portlet.model.OntologySpace"
            };

        _methodName476 = "getAllOntologyTermsFromFocusAreaWithOntologySpace";

        _methodParameterTypes476 = new String[] {
                "com.ext.portlet.model.FocusArea",
                "com.ext.portlet.model.OntologySpace"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return FocusAreaLocalServiceUtil.addFocusArea((com.ext.portlet.model.FocusArea) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return FocusAreaLocalServiceUtil.createFocusArea(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return FocusAreaLocalServiceUtil.deleteFocusArea(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return FocusAreaLocalServiceUtil.deleteFocusArea((com.ext.portlet.model.FocusArea) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return FocusAreaLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return FocusAreaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return FocusAreaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return FocusAreaLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return FocusAreaLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return FocusAreaLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return FocusAreaLocalServiceUtil.fetchFocusArea(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getFocusArea(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getFocusAreas(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getFocusAreasCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return FocusAreaLocalServiceUtil.updateFocusArea((com.ext.portlet.model.FocusArea) arguments[0]);
        }

        if (_methodName464.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes464, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName465.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes465, parameterTypes)) {
            FocusAreaLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName470.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes470, parameterTypes)) {
            FocusAreaLocalServiceUtil.store((com.ext.portlet.model.FocusArea) arguments[0]);

            return null;
        }

        if (_methodName471.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes471, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getTerms((com.ext.portlet.model.FocusArea) arguments[0]);
        }

        if (_methodName472.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes472, parameterTypes)) {
            FocusAreaLocalServiceUtil.removeTerm((com.ext.portlet.model.FocusArea) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName473.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes473, parameterTypes)) {
            FocusAreaLocalServiceUtil.addTerm((com.ext.portlet.model.FocusArea) arguments[0],
                (java.lang.Long) arguments[1]);

            return null;
        }

        if (_methodName474.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes474, parameterTypes)) {
            FocusAreaLocalServiceUtil.tagClass((com.ext.portlet.model.FocusArea) arguments[0],
                (java.lang.Class) arguments[1], (java.lang.Long) arguments[2]);

            return null;
        }

        if (_methodName475.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes475, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getOntologyTermFromFocusAreaWithOntologySpace((com.ext.portlet.model.FocusArea) arguments[0],
                (com.ext.portlet.model.OntologySpace) arguments[1]);
        }

        if (_methodName476.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes476, parameterTypes)) {
            return FocusAreaLocalServiceUtil.getAllOntologyTermsFromFocusAreaWithOntologySpace((com.ext.portlet.model.FocusArea) arguments[0],
                (com.ext.portlet.model.OntologySpace) arguments[1]);
        }

        throw new UnsupportedOperationException();
    }
}

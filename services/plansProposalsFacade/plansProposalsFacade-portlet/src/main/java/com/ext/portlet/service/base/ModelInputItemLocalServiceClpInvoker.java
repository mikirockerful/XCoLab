package com.ext.portlet.service.base;

import com.ext.portlet.service.ModelInputItemLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ModelInputItemLocalServiceClpInvoker {
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
    private String _methodName626;
    private String[] _methodParameterTypes626;
    private String _methodName627;
    private String[] _methodParameterTypes627;
    private String _methodName632;
    private String[] _methodParameterTypes632;
    private String _methodName633;
    private String[] _methodParameterTypes633;
    private String _methodName634;
    private String[] _methodParameterTypes634;
    private String _methodName635;
    private String[] _methodParameterTypes635;
    private String _methodName636;
    private String[] _methodParameterTypes636;
    private String _methodName637;
    private String[] _methodParameterTypes637;
    private String _methodName639;
    private String[] _methodParameterTypes639;
    private String _methodName640;
    private String[] _methodParameterTypes640;

    public ModelInputItemLocalServiceClpInvoker() {
        _methodName0 = "addModelInputItem";

        _methodParameterTypes0 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName1 = "createModelInputItem";

        _methodParameterTypes1 = new String[] { "long" };

        _methodName2 = "deleteModelInputItem";

        _methodParameterTypes2 = new String[] { "long" };

        _methodName3 = "deleteModelInputItem";

        _methodParameterTypes3 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

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

        _methodName10 = "fetchModelInputItem";

        _methodParameterTypes10 = new String[] { "long" };

        _methodName11 = "getModelInputItem";

        _methodParameterTypes11 = new String[] { "long" };

        _methodName12 = "getPersistedModel";

        _methodParameterTypes12 = new String[] { "java.io.Serializable" };

        _methodName13 = "getModelInputItems";

        _methodParameterTypes13 = new String[] { "int", "int" };

        _methodName14 = "getModelInputItemsCount";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "updateModelInputItem";

        _methodParameterTypes15 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName626 = "getBeanIdentifier";

        _methodParameterTypes626 = new String[] {  };

        _methodName627 = "setBeanIdentifier";

        _methodParameterTypes627 = new String[] { "java.lang.String" };

        _methodName632 = "getItemsForModel";

        _methodParameterTypes632 = new String[] {
                "edu.mit.cci.roma.client.Simulation"
            };

        _methodName633 = "getItemForMetaData";

        _methodParameterTypes633 = new String[] {
                "java.lang.Long", "edu.mit.cci.roma.client.MetaData"
            };

        _methodName634 = "getItemForGroupId";

        _methodParameterTypes634 = new String[] { "java.lang.Long" };

        _methodName635 = "getMetaData";

        _methodParameterTypes635 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName636 = "getModel";

        _methodParameterTypes636 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName637 = "getPropertyMap";

        _methodParameterTypes637 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };

        _methodName639 = "saveProperties";

        _methodParameterTypes639 = new String[] {
                "com.ext.portlet.model.ModelInputItem", "java.util.Map"
            };

        _methodName640 = "store";

        _methodParameterTypes640 = new String[] {
                "com.ext.portlet.model.ModelInputItem"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName0.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.addModelInputItem((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName1.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.createModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName2.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.deleteModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName3.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.deleteModelInputItem((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName4.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery();
        }

        if (_methodName5.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName6.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName7.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue(),
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[3]);
        }

        if (_methodName8.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0]);
        }

        if (_methodName9.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery) arguments[0],
                (com.liferay.portal.kernel.dao.orm.Projection) arguments[1]);
        }

        if (_methodName10.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.fetchModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName11.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModelInputItem(((Long) arguments[0]).longValue());
        }

        if (_methodName12.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getPersistedModel((java.io.Serializable) arguments[0]);
        }

        if (_methodName13.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModelInputItems(((Integer) arguments[0]).intValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModelInputItemsCount();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.updateModelInputItem((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName626.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes626, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName627.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes627, parameterTypes)) {
            ModelInputItemLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName632.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes632, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemsForModel((edu.mit.cci.roma.client.Simulation) arguments[0]);
        }

        if (_methodName633.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes633, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemForMetaData((java.lang.Long) arguments[0],
                (edu.mit.cci.roma.client.MetaData) arguments[1]);
        }

        if (_methodName634.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes634, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getItemForGroupId((java.lang.Long) arguments[0]);
        }

        if (_methodName635.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes635, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getMetaData((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName636.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes636, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getModel((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName637.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes637, parameterTypes)) {
            return ModelInputItemLocalServiceUtil.getPropertyMap((com.ext.portlet.model.ModelInputItem) arguments[0]);
        }

        if (_methodName639.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes639, parameterTypes)) {
            ModelInputItemLocalServiceUtil.saveProperties((com.ext.portlet.model.ModelInputItem) arguments[0],
                (java.util.Map<java.lang.String, java.lang.String>) arguments[1]);

            return null;
        }

        if (_methodName640.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes640, parameterTypes)) {
            ModelInputItemLocalServiceUtil.store((com.ext.portlet.model.ModelInputItem) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}

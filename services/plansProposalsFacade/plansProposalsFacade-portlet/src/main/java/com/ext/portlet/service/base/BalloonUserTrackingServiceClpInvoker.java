package com.ext.portlet.service.base;

import com.ext.portlet.service.BalloonUserTrackingServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BalloonUserTrackingServiceClpInvoker {
    private String _methodName490;
    private String[] _methodParameterTypes490;
    private String _methodName491;
    private String[] _methodParameterTypes491;

    public BalloonUserTrackingServiceClpInvoker() {
        _methodName490 = "getBeanIdentifier";

        _methodParameterTypes490 = new String[] {  };

        _methodName491 = "setBeanIdentifier";

        _methodParameterTypes491 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName490.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes490, parameterTypes)) {
            return BalloonUserTrackingServiceUtil.getBeanIdentifier();
        }

        if (_methodName491.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes491, parameterTypes)) {
            BalloonUserTrackingServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}

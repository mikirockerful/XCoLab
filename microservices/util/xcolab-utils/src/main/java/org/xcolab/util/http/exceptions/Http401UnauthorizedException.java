package org.xcolab.util.http.exceptions;

public class Http401UnauthorizedException extends HttpRuntimeException {
    public Http401UnauthorizedException(HttpServiceExceptionObject httpServiceExceptionObject) {
        super(httpServiceExceptionObject);
    }
}

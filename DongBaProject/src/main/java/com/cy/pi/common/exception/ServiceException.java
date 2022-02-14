package com.cy.pi.common.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 8052531282358440614L;

    public ServiceException() {
        super();
    }
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}

package com.excilys.cdb.service;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     */
    public ServiceException() {
    }

    /**
     * @param message message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * @param cause cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message message
     * @param cause cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message message
     * @param cause cause
     * @param enableSuppression enableSuppresion
     * @param writableStackTrace writableStackTrace
     */
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

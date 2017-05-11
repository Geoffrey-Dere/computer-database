package com.excilys.cdb.validator;

public class ValidatorException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    public ValidatorException() {
    }

    /**
     * @param message message
     */
    public ValidatorException(String message) {
        super(message);
    }

    /**
     * @param cause cause
     */
    public ValidatorException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message message
     * @param cause cause
     */
    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message message
     * @param cause cause
     * @param enableSuppression enableSuppression
     * @param writableStackTrace writableStackTrace
     */
    public ValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

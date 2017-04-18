package com.excilys.computerDatabase.persistence;

public class ExceptionDAO extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     */
    public ExceptionDAO() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message message
     */
    public ExceptionDAO(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause cause
     */
    public ExceptionDAO(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message message
     * @param cause cause
     */
    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message the message
     * @param cause the cause
     * @param enableSuppression enable suppression
     * @param writableStackTrace write trace
     */
    public ExceptionDAO(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}

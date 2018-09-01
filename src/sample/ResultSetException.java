/*
 Own created exception for handling ResultSet error
 */
package sample;

/**
 * The Class ResultSetException.
 */
public class ResultSetException extends Exception { //own created exception for handling resultset's errors while operating on db

    /**
     * Instantiates a new result set exception.
     *
     * @param message the message
     */
	
    public ResultSetException(String message) {
        super(message);
    }

    /**
     * Instantiates a new result set exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ResultSetException(String message, Throwable cause) {
        super(message, cause);
    }
}

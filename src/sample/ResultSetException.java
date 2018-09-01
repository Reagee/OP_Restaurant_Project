/*
 Own created exception for handling ResultSet error
 */
package sample;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultSetException.
 */
public class ResultSetException extends Exception {

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

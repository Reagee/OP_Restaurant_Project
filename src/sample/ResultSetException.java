package sample;

public class ResultSetException extends Exception {

    public ResultSetException(String message) {
        super(message);
    }

    public ResultSetException(String message, Throwable cause) {
        super(message, cause);
    }
}

package cz.seznam.fulltext.robot.exception;

public class UnsupportedProcessingTypeException extends Exception {
    public UnsupportedProcessingTypeException() {
        super();
    }

    public UnsupportedProcessingTypeException(String message) {
        super(message);
    }

    public UnsupportedProcessingTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedProcessingTypeException(Throwable cause) {
        super(cause);
    }
}

package fmi.designpatterns.labels.exceptions;

public class HelpLabelException extends RuntimeException {

    public HelpLabelException(String message) {
        super(message);
    }

    public HelpLabelException(String message, Throwable cause) {
        super(message, cause);
    }

}

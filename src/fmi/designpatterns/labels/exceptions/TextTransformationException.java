package fmi.designpatterns.labels.exceptions;

public class TextTransformationException extends RuntimeException {

    public TextTransformationException(String message) {
        super(message);
    }

    public TextTransformationException(String message, Throwable cause) {
        super(message, cause);
    }
}

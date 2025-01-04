package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;

public class CapitalizeTransformation implements TextTransformation {

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new TextTransformationException("Could not transform text. Text is null.");
        }

        if (text.isEmpty()) {
            return text;
        }

        char firstSymbol = Character.isLetter(text.charAt(0)) ? Character.toUpperCase(text.charAt(0)) : text.charAt(0);
        return firstSymbol + text.substring(1);
    }
}

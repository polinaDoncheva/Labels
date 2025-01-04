package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;

public class DecorateTransformation implements TextTransformation {

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new TextTransformationException("Could not transform text. Text is null.");
        }

        return "-={ " + text + " }=-";
    }
}

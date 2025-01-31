package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;

public class CensorTransformation implements TextTransformation {

    private final String wordToCensor;
    private final String censored;

    public CensorTransformation(String wordToCensor) {
        this.wordToCensor = wordToCensor;
        censored = "*".repeat(wordToCensor.length());
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new TextTransformationException("Could not transform text. Text is null.");
        }

        return text.replace(wordToCensor, censored);
    }
}

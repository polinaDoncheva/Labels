package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;

public class ReplaceTransformation implements TextTransformation {

    private final String wordToReplace;
    private final String replacement;

    public ReplaceTransformation(String wordToReplace, String replacement) {
        this.wordToReplace = wordToReplace;
        this.replacement = replacement;
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new TextTransformationException("Could not transform text. Text is null.");
        }

        return text.replace(wordToReplace, replacement);
    }
}

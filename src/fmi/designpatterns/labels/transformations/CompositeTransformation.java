package fmi.designpatterns.labels.transformations;

import java.util.List;

public class CompositeTransformation implements TextTransformation {

    private final List<TextTransformation> transformations;

    public CompositeTransformation(List<TextTransformation> transformations) {
        this.transformations = transformations;
    }

    @Override
    public String transform(String text) {
        String result = text;

        for(TextTransformation transformation : transformations) {
            result = transformation.transform(text);
        }

        return result;
    }
}

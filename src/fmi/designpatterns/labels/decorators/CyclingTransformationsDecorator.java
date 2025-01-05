package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;

import java.util.List;

public class CyclingTransformationsDecorator extends LabelDecoratorBase {

    private final List<TextTransformation> transformations;
    private int index;

    public CyclingTransformationsDecorator(Label label, List<TextTransformation> transformations) {
        super(label);
        this.transformations = transformations;
        this.index = 0;
    }

    @Override
    public String getText() {
        if (transformations.isEmpty()) {
            return super.getText();
        }

        return transformations.get(getAndIncrementIndex()).transform(super.getText());
    }

    private int getAndIncrementIndex() {
        int currentIndex = index;
        index = (index + 1) % transformations.size();

        return currentIndex;
    }
}

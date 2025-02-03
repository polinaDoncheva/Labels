package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;

import java.util.List;
import java.util.Objects;

public class CyclingTransformationsDecorator extends LabelDecoratorBase {

    private final List<TextTransformation> transformations;
    private int index;

    public CyclingTransformationsDecorator(Label label, List<TextTransformation> transformations) {
        super(label);
        this.transformations = transformations;
        this.index = 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        CyclingTransformationsDecorator other = (CyclingTransformationsDecorator) object;
        boolean areLabelsEqual = !(label instanceof LabelDecoratorBase) || label.equals(other.label);

        return areLabelsEqual && index == other.index &&
            Objects.equals(transformations, other.transformations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transformations, label);
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

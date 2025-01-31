package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomTransformationDecorator extends LabelDecoratorBase {

    private final List<TextTransformation> transformations;
    private final Random random;

    public RandomTransformationDecorator(Label label, List<TextTransformation> transformations) {
        super(label);
        this.transformations = transformations;
        this.random = new Random();
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        RandomTransformationDecorator other = (RandomTransformationDecorator) object;
        boolean areLabelsEqual = !(label instanceof LabelDecoratorBase) || label.equals(other.label);

        return areLabelsEqual &&
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

        int randomIndex = random.nextInt(transformations.size());
        return transformations.get(randomIndex).transform(super.getText());
    }
}

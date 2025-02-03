package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;

import java.util.Objects;

public class TextTransformationDecorator extends LabelDecoratorBase {
    
    private final TextTransformation transformation;

    public TextTransformationDecorator(Label label, TextTransformation transformation) {
        super(label);
        this.transformation = transformation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        TextTransformationDecorator other = (TextTransformationDecorator) object;
        boolean areLabelsEqual = !(label instanceof LabelDecoratorBase) || label.equals(other.label);

        return areLabelsEqual && transformation.equals(other.transformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transformation, label);
    }

    @Override
    public String getText() {
        return transformation.transform(super.getText());
    }
}

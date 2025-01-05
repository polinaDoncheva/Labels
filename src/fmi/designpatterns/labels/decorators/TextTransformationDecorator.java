package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;

public class TextTransformationDecorator extends LabelDecoratorBase {
    
    private final TextTransformation transformation;

    public TextTransformationDecorator(Label label, TextTransformation transformation) {
        super(label);
        this.transformation = transformation;
    }

    @Override
    public String getText() {
        return transformation.transform(super.getText());
    }
}

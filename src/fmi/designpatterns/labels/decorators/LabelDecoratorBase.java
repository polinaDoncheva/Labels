package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;

public abstract class LabelDecoratorBase implements Label {

    Label label;

    public LabelDecoratorBase(Label label) {
        this.label = label;
    }

    @Override
    public String getText() {
        return label.getText();
    }

    public static Label removeDecoratorFrom(Label label, LabelDecoratorBase decorator) {
        if (label == null) {
            throw new IllegalArgumentException("Could not remove decorator from label. Label is null");
        }

        return label instanceof LabelDecoratorBase ? ((LabelDecoratorBase) label).removeDecorator(decorator) : label;
    }

    public Label removeDecorator(LabelDecoratorBase decorator) {
        if (this.equals(decorator)) {
            return label != null ? label : this;
        } else if (label instanceof LabelDecoratorBase) {
            label = ((LabelDecoratorBase) label).removeDecorator(decorator);
        }

        return this;
    }
}

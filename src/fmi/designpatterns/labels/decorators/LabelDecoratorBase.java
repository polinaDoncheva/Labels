package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;

public abstract class LabelDecoratorBase implements Label {

    private final Label label;

    public LabelDecoratorBase(Label label) {
        this.label = label;
    }

    @Override
    public String getText() {
        return label.getText();
    }
}

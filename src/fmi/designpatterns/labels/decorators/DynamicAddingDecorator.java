package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;

import java.util.List;
import java.util.Objects;

public class DynamicAddingDecorator extends LabelDecoratorBase {

    private List<LabelDecoratorBase> decorators;

    public DynamicAddingDecorator(Label label) {
        this(label, List.of());
    }

    public DynamicAddingDecorator(Label label, List<LabelDecoratorBase> decorators) {
        super(label);
        this.decorators = decorators;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DynamicAddingDecorator other = (DynamicAddingDecorator) obj;
        return Objects.equals(decorators, other.decorators) &&
            Objects.equals(super.label, other.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(decorators, super.label);
    }

    public void add(LabelDecoratorBase decorator) {
        if (decorator == null) {
            throw new IllegalArgumentException("Could not add decorator. Decorator is null");
        }

        decorators.add(decorator);
    }

    @Override
    public String getText() {
        String text = super.getText();

        for(LabelDecoratorBase decorator : decorators) {
            text = decorator.getText();
        }

        return text;
    }
}

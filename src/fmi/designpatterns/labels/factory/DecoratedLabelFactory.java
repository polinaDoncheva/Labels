package fmi.designpatterns.labels.factory;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.reader.LabelEntry;
import fmi.designpatterns.labels.decorators.CyclingTransformationsDecorator;
import fmi.designpatterns.labels.decorators.DynamicAddingDecorator;
import fmi.designpatterns.labels.decorators.RandomTransformationDecorator;
import fmi.designpatterns.labels.decorators.TextTransformationDecorator;
import fmi.designpatterns.labels.transformations.TextTransformation;

import java.util.List;

public class DecoratedLabelFactory {

    public static Label createFrom(LabelEntry entry, Label baseLabel) {
        List<TextTransformation> transformations = entry.transformations().entrySet().stream()
            .map(transformationEntry -> TextTransformationFactory.createFrom(transformationEntry.getKey(),
                transformationEntry.getValue()))
            .toList();

        return getDecoratedLabel(entry.decorator(), baseLabel, transformations);
    }

    private static Label getDecoratedLabel(String decorator , Label baseLabel, List<TextTransformation> transformations) {
        if(decorator == null || decorator.isEmpty()) {
            return  baseLabel;
        }

        return switch (decorator.toLowerCase()) {
            case "cycling" -> new CyclingTransformationsDecorator(baseLabel, transformations);
            case "dynamic adding" -> new DynamicAddingDecorator(baseLabel, List.of());
            case "random" -> new RandomTransformationDecorator(baseLabel, transformations);
            case "single" -> new TextTransformationDecorator(baseLabel, transformations.getFirst());
            default -> throw new IllegalArgumentException("Unknown decorator: " + decorator);
        };
    }

}

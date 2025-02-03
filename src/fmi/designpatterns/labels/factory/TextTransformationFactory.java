package fmi.designpatterns.labels.factory;

import fmi.designpatterns.labels.transformations.CapitalizeTransformation;
import fmi.designpatterns.labels.transformations.CensorTransformation;
import fmi.designpatterns.labels.transformations.CompositeTransformation;
import fmi.designpatterns.labels.transformations.DecorateTransformation;
import fmi.designpatterns.labels.transformations.NormalizeSpaceTransformation;
import fmi.designpatterns.labels.transformations.ReplaceTransformation;
import fmi.designpatterns.labels.transformations.TextTransformation;
import fmi.designpatterns.labels.transformations.TrimLeftTransformation;
import fmi.designpatterns.labels.transformations.TrimRightTransformation;

import java.util.List;

public class TextTransformationFactory {

    public static TextTransformation createFrom(String name, List<String> parameters) {
        return switch (name.toLowerCase()) {
            case "capitalize" -> new CapitalizeTransformation();
            case "censor" -> CensorTransformationFactory.createFrom(parameters.getFirst());
            case "trim left" -> new TrimLeftTransformation();
            case "trim right" -> new TrimRightTransformation();
            case "normalize space" -> new NormalizeSpaceTransformation();
            case "replace" -> new ReplaceTransformation(parameters.getFirst(), parameters.get(1));
            case "decorate" -> new DecorateTransformation();
            case "composite" -> new CompositeTransformation(List.of());
            default -> throw new IllegalArgumentException("Unknown transformation: " + name);
        };
    }

}

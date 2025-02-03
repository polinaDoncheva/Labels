package fmi.designpatterns.labels.factory;

import fmi.designpatterns.labels.DynamicLabel;
import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.RichLabel;
import fmi.designpatterns.labels.SimpleLabel;
import fmi.designpatterns.labels.creator.LabelEntry;
import fmi.designpatterns.labels.decorators.CyclingTransformationsDecorator;
import fmi.designpatterns.labels.decorators.DynamicAddingDecorator;
import fmi.designpatterns.labels.decorators.RandomTransformationDecorator;
import fmi.designpatterns.labels.decorators.TextTransformationDecorator;
import fmi.designpatterns.labels.helpLabels.BytesCountHelpText;
import fmi.designpatterns.labels.helpLabels.HelpLabel;
import fmi.designpatterns.labels.helpLabels.LineSeparatorHelpText;
import fmi.designpatterns.labels.helpLabels.NoHelpText;
import fmi.designpatterns.labels.helpLabels.WordsCountHelpText;
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
import java.util.Scanner;

public class LabelFactory {

    public static Label createFrom(LabelEntry entry) {
        HelpLabel help = getHelp(entry.helpText());
        Label baseLabel = getBaseLabel(entry, help);

        List<TextTransformation> transformations = entry.transformations().entrySet().stream()
            .map(transformationEntry -> getTransformation(transformationEntry.getKey(), transformationEntry.getValue()))
            .toList();

        return getDecoratedLabel(entry.decorator(), baseLabel, transformations);
    }

    private static HelpLabel getHelp(String helpText) {
        HelpLabel help = new NoHelpText();

        if (helpText != null) {
            help = switch (helpText.toLowerCase()) {
                case "bytes" -> new BytesCountHelpText();
                case "lines" -> new LineSeparatorHelpText();
                case "words" -> new WordsCountHelpText();
                default -> throw new IllegalArgumentException("Invalid help text type: " + helpText);
            };
        }

        return help;
    }

    private static Label getBaseLabel(LabelEntry entry, HelpLabel help) {
        return switch (entry.type().toLowerCase()) {
            case "simple" -> new SimpleLabel(help, entry.text());
            case "rich" -> new RichLabel(help, entry.text(), entry.configuration());
            case "dynamic" -> new DynamicLabel(help, new Scanner(System.in), entry.configuration());
            default -> throw new IllegalArgumentException("Unsupported label type: " + entry.type());
        };
    }

    private static TextTransformation getTransformation(String name, List<String> parameters) {
        return switch (name.toLowerCase()) {
            case "capitalize" -> new CapitalizeTransformation();
            case "censor" -> new CensorTransformation(parameters.getFirst());
            case "trim left" -> new TrimLeftTransformation();
            case "trim right" -> new TrimRightTransformation();
            case "normalize space" -> new NormalizeSpaceTransformation();
            case "replace" -> new ReplaceTransformation(parameters.getFirst(), parameters.get(1));
            case "decorate" -> new DecorateTransformation();
            case "composite" -> new CompositeTransformation(List.of());
            default -> throw new IllegalArgumentException("Unknown transformation: " + name);
        };
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

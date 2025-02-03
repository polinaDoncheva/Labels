package fmi.designpatterns.labels.factory;

import fmi.designpatterns.labels.DynamicLabel;
import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.RichLabel;
import fmi.designpatterns.labels.SimpleLabel;
import fmi.designpatterns.labels.reader.LabelEntry;
import fmi.designpatterns.labels.helplabels.HelpLabel;

import java.util.Scanner;

public class LabelFactory {

    public static Label createFrom(LabelEntry entry) {
        HelpLabel help = HelpLabelFactory.createFrom(entry.helpText());
        Label baseLabel = getBaseLabel(entry, help);

        return DecoratedLabelFactory.createFrom(entry, baseLabel);
    }

    private static Label getBaseLabel(LabelEntry entry, HelpLabel help) {
        return switch (entry.type().toLowerCase()) {
            case "simple" -> new SimpleLabel(help, entry.text());
            case "rich" -> new RichLabel(help, entry.text(), entry.configuration());
            case "dynamic" -> new DynamicLabel(help, new Scanner(System.in), entry.configuration());
            default -> throw new IllegalArgumentException("Unsupported label type: " + entry.type());
        };
    }

}

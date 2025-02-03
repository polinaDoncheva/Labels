package fmi.designpatterns.labels.factory;

import fmi.designpatterns.labels.helplabels.BytesCountHelpText;
import fmi.designpatterns.labels.helplabels.HelpLabel;
import fmi.designpatterns.labels.helplabels.LineSeparatorHelpText;
import fmi.designpatterns.labels.helplabels.NoHelpText;
import fmi.designpatterns.labels.helplabels.WordsCountHelpText;

public class HelpLabelFactory {

    public static HelpLabel createFrom(String helpText) {
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

}

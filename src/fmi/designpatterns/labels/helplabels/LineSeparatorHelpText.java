package fmi.designpatterns.labels.helplabels;

import fmi.designpatterns.labels.Label;

public class LineSeparatorHelpText implements HelpLabel {

    @Override
    public String getHelp(Label label) {
        String visibleLineSeparator = System.lineSeparator()
            .replace("\n", "\\n").replace("\r", "\\r");
        return "The line separator in this system is: '" + visibleLineSeparator + "'";
    }
}

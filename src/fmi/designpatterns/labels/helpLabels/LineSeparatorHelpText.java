package fmi.designpatterns.labels.helpLabels;

import fmi.designpatterns.labels.Label;

public class LineSeparatorHelpText implements HelpLabel {
    private final Label label;

    public LineSeparatorHelpText(Label label) {
        this.label = label;
    }
    @Override
    public String getHelp() {
        String visibleLineSeparator = System.lineSeparator()
            .replace("\n", "\\n").replace("\r", "\\r");
        return "The line separator in this system is: '" + visibleLineSeparator + "'";
    }
}

package fmi.designpatterns.labels;

import fmi.designpatterns.labels.exceptions.HelpLabelException;
import fmi.designpatterns.labels.helplabels.HelpLabel;

public abstract class Label {

    private final HelpLabel helpLabel;

    public Label(HelpLabel helpLabel) {
        this.helpLabel = helpLabel;
    }

    public String getHelp() {
        if (helpLabel == null) {
            throw new HelpLabelException("Could not get help text. No help text given for this label.");
        }

        return helpLabel.getHelp(this);
    }

    public abstract String getText();
}

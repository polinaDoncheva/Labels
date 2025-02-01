package fmi.designpatterns.labels;

import fmi.designpatterns.labels.helpLabels.HelpLabel;
import fmi.designpatterns.labels.helpLabels.NoHelpText;

public class SimpleLabel extends Label {

    public String value;

    public SimpleLabel(String value) {
        this(new NoHelpText(), value);
    }

    public SimpleLabel(HelpLabel helpLabel, String value) {
        super(helpLabel);
        this.value = value;
    }

    public String getText() {
        return value;
    }
}
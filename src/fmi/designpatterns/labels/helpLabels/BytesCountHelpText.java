package fmi.designpatterns.labels.helpLabels;

import fmi.designpatterns.labels.Label;

public class BytesCountHelpText implements HelpLabel {

    private final Label label;

    public BytesCountHelpText(Label label) {
        this.label = label;
    }

    @Override
    public String getHelp() {
        return "The label contains " + label.getText().getBytes().length + " bytes.";
    }
}

package fmi.designpatterns.labels.helplabels;

import fmi.designpatterns.labels.Label;

public class BytesCountHelpText implements HelpLabel {

    @Override
    public String getHelp(Label label) {
        return "The label contains " + label.getText().getBytes().length + " bytes.";
    }
}

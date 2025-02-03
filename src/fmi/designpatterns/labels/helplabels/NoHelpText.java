package fmi.designpatterns.labels.helplabels;

import fmi.designpatterns.labels.Label;

public class NoHelpText implements HelpLabel {

    @Override
    public String getHelp(Label label) {
        return "No help text available";
    }
}

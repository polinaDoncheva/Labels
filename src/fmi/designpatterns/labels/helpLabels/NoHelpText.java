package fmi.designpatterns.labels.helpLabels;

import fmi.designpatterns.labels.Label;

public class NoHelpText implements HelpLabel {

    @Override
    public String getHelp(Label label) {
        return "No help text available";
    }
}

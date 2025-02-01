package fmi.designpatterns.labels.helpLabels;

import fmi.designpatterns.labels.Label;

public class WordsCountHelpText implements HelpLabel {

    @Override
    public String getHelp(Label label) {
        int wordCount = label.getText().split("\\s+").length;
        return "The label contains " + wordCount + " words.";
    }
}

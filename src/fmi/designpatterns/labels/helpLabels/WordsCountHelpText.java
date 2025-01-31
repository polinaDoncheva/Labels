package fmi.designpatterns.labels.helpLabels;

import fmi.designpatterns.labels.Label;

public class WordsCountHelpText implements HelpLabel {

    private final Label label;

    public WordsCountHelpText(Label label) {
        this.label = label;
    }
    @Override
    public String getHelp() {
        int wordCount = label.getText().split("\\s+").length;
        return "The label contains " + wordCount + " words.";
    }
}

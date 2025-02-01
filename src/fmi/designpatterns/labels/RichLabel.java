package fmi.designpatterns.labels;

import fmi.designpatterns.labels.helpLabels.HelpLabel;
import fmi.designpatterns.labels.helpLabels.NoHelpText;

public class RichLabel extends Label {

    private final String text;
    private final String color;
    private final String fontName;
    private final int fontSize;

    public RichLabel(String text, String color, String fontName, int fontSize) {
        this(new NoHelpText(), text, color, fontName, fontSize);
    }

    public RichLabel(HelpLabel helpLabel, String text, String color, String fontName, int fontSize) {
        super(helpLabel);
        this.text = text;
        this.color = color;
        this.fontName = fontName;
        this.fontSize = fontSize;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "RichLabel{" +
            "text='" + text + '\'' +
            ", color='" + color + '\'' +
            ", fontName='" + fontName + '\'' +
            ", fontSize=" + fontSize +
            '}';
    }
}


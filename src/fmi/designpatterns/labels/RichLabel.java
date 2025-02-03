package fmi.designpatterns.labels;

import fmi.designpatterns.labels.helpLabels.HelpLabel;
import fmi.designpatterns.labels.helpLabels.NoHelpText;

import java.util.List;

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

    public RichLabel(HelpLabel helpLabel, String text, List<String> configuration) {
        super(helpLabel);

        if (configuration.size() != 3) {
            throw new IllegalArgumentException("Could not create rich label, requires color, font name, and font size.");
        }

        int fontSize = 0;

        try {
            fontSize = Integer.parseInt(configuration.get(2));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Could not create rich label, expects 3rd argument int for font size");
        }

        this.text = text;
        this.color = configuration.getFirst();
        this.fontName = configuration.get(1);
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


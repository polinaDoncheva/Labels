package fmi.designpatterns.labels;

import fmi.designpatterns.labels.helplabels.HelpLabel;
import fmi.designpatterns.labels.helplabels.NoHelpText;

import java.util.List;
import java.util.Scanner;

public class DynamicLabel extends Label {

    private final int timeout;
    private final Scanner scanner;
    private int count;
    private String text = null;

    public DynamicLabel(Scanner scanner, int timeout) {
        this(new NoHelpText(), scanner, timeout);
    }

    public DynamicLabel(HelpLabel helpLabel, Scanner scanner, int timeout) {
        super(helpLabel);
        this.scanner = scanner;
        this.timeout = timeout;
        count = 0;
    }

    public DynamicLabel(HelpLabel helpLabel, Scanner scanner, List<String> configuration) {
        super(helpLabel);

        if (configuration.size() != 1) {
            throw new IllegalArgumentException("DynamicLabel requires only timeout parameter.");
        }

        int timeout = 0;

        try {
            timeout = Integer.parseInt(configuration.getFirst());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Could not create dynamic label, expects 1st argument int for timeout");
        }

        this.scanner = scanner;
        this.timeout = timeout;
        count = 0;
    }

    @Override
    public String getText() {
        if (text == null) {
            text = scanner.nextLine();
        }

        ++count;

        if (count >= timeout) {
            updateText();
        }

        return text;
    }

    private void updateText() {
        System.out.println("Would yo like to update the label '" + text + "'? (y/n)");

        String response = scanner.nextLine();

        if (response.equals("y")) {
            System.out.println("Enter new label text: ");
            text = scanner.nextLine();
            count = 1;
        }
    }
}

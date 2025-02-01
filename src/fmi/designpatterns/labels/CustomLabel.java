package fmi.designpatterns.labels;

import fmi.designpatterns.labels.helpLabels.HelpLabel;
import fmi.designpatterns.labels.helpLabels.NoHelpText;

import java.util.Scanner;

public class CustomLabel extends Label {

    private final int timeout;
    private final Scanner scanner;
    private int count;
    private String text = null;

    public CustomLabel(Scanner scanner, int timeout) {
        this(new NoHelpText(), scanner, timeout);
    }

    public CustomLabel(HelpLabel helpLabel, Scanner scanner, int timeout) {
        super(helpLabel);
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

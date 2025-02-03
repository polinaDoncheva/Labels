package fmi.designpatterns.labels.reader;

import java.util.Scanner;

public class HelpReader {

    public static String read(Scanner scanner) {
        String helpText = null;
        String helpChoice = PromptHelper.prompt(scanner, "Do you want help text? (y/n): ");

        if (helpChoice.equals("y")) {
            helpText = PromptHelper.prompt(scanner, "Choose help text type (bytes, lines, words):");
        }

        return helpText;
    }

}

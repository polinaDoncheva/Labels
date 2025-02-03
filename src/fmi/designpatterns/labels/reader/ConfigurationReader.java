package fmi.designpatterns.labels.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigurationReader {

    public static List<String> read(Scanner scanner, String type) {
        List<String> configuration = new ArrayList<>();

        if (type.equalsIgnoreCase("rich")) {
            configuration.add(PromptHelper.prompt(scanner, "Enter the label color: "));
            configuration.add(PromptHelper.prompt(scanner, "Enter the label font name: "));
            configuration.add(PromptHelper.prompt(scanner, "Enter the label font size: "));
        } else if (type.equalsIgnoreCase("dynamic")) {
            configuration.add(PromptHelper.prompt(scanner, "Enter timeout: "));
        }

        return configuration;
    }

}

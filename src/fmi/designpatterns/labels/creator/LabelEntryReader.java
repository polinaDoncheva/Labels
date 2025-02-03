package fmi.designpatterns.labels.creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LabelEntryReader {

    public LabelEntry read(Scanner scanner) {
        String type = prompt(scanner, "Enter the label type (simple, rich, dynamic): ");
        String text = null;

        if(!type.equalsIgnoreCase("dynamic")) {
            text = prompt(scanner, "Enter the label text: ");
        }

        List<String> configuration = getConfiguration(scanner, type);
        String helpText = getHelp(scanner);
        String decorator = getDecorator(scanner);
        Map<String, List<String>> transformations = getTransformations(scanner, decorator);

        return new LabelEntry(type, text, configuration, helpText, decorator, transformations);
    }

    private static String prompt(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static List<String> getConfiguration(Scanner scanner, String type) {
        List<String> configuration = new ArrayList<>();

        if (type.equalsIgnoreCase("rich")) {
            configuration.add(prompt(scanner, "Enter the label color: "));
            configuration.add(prompt(scanner, "Enter the label font name: "));
            configuration.add(prompt(scanner, "Enter the label font size: "));
        } else if (type.equalsIgnoreCase("custom")) {
            configuration.add(prompt(scanner, "Enter timeout: "));
        }

        return configuration;
    }

    private static String getHelp(Scanner scanner) {
        String helpText = null;
        String helpChoice = prompt(scanner, "Do you want help text? (y/n): ");

        if (helpChoice.equals("y")) {
            helpText = prompt(scanner, "Choose help text type (bytes, lines, words):");
        }

        return helpText;
    }

    private static String getDecorator(Scanner scanner) {
        String decorator = null;
        String decoratorChoice = prompt(scanner, "Do you want decorator? (y/n): ");

        if (decoratorChoice.equals("yes")) {
            decorator = prompt(scanner, "Choose decorator type (cycling, random, single, adding): ");
        }

        return decorator;
    }

    private static Map<String, List<String>> getTransformations(Scanner scanner, String decorator) {
        Map<String, List<String>> transformations = new HashMap<>();

        if (decorator != null && (decorator.equals("cycling") || decorator.equals("random"))) {
            String input = prompt(scanner,
                "Enter transformations (comma-separated), or press Enter for none: ");

            if (!input.trim().isEmpty()) {
                transformations = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .collect(Collectors.toMap(
                        key -> key,
                        key -> getTransformationParameters(scanner, key)
                    ));
            }
        } else if (decorator != null && decorator.equals("single")) {
            String input = prompt(scanner, "Enter one transformation for the single decorator: ");

            if (!input.trim().isEmpty()) {
                transformations.put(input.trim(), getTransformationParameters(scanner, input));
            }
        }

        return transformations;
    }

    private static List<String> getTransformationParameters(Scanner scanner, String transformation) {
        List<String> result = new ArrayList<>();

        if (transformation.equalsIgnoreCase("censor")) {
            result.add(prompt(scanner, "Enter word you want to censor: "));
        } else if (transformation.equalsIgnoreCase("replace")) {
            result.add(prompt(scanner, "Enter word you want to be replaced: "));
            result.add(prompt(scanner, "Enter word you want to be the replacement: "));
        }

        return result;
    }
}

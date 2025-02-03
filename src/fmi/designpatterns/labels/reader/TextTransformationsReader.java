package fmi.designpatterns.labels.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextTransformationsReader {

    public static Map<String, List<String>> read(Scanner scanner, String decorator) {
        Map<String, List<String>> transformations = new HashMap<>();

        if (decorator != null && (decorator.equals("cycling") || decorator.equals("random"))) {
            String input = PromptHelper.prompt(scanner,
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
            String input = PromptHelper.prompt(scanner, "Enter one transformation for the single decorator: ");

            if (!input.trim().isEmpty()) {
                transformations.put(input.trim(), getTransformationParameters(scanner, input));
            }
        }

        return transformations;
    }

    private static List<String> getTransformationParameters(Scanner scanner, String transformation) {
        List<String> result = new ArrayList<>();

        if (transformation.equalsIgnoreCase("censor")) {
            result.add(PromptHelper.prompt(scanner, "Enter word you want to censor: "));
        } else if (transformation.equalsIgnoreCase("replace")) {
            result.add(PromptHelper.prompt(scanner, "Enter word you want to be replaced: "));
            result.add(PromptHelper.prompt(scanner, "Enter word you want to be the replacement: "));
        }

        return result;
    }

}

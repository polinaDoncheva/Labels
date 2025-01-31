package fmi.designpatterns.labels.creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CreationDataReader {

    public CreatorEntry read(Scanner scanner) {
        System.out.println("Enter the label type (simple, rich, custom): ");
        String type = scanner.nextLine();

        System.out.println("Enter the label text: ");
        String text = scanner.nextLine();

        String helpText = null;
        System.out.println("Do you want help text? (y/n): ");
        String helpChoice = scanner.nextLine().trim().toLowerCase();
        if (helpChoice.equals("y")) {
            System.out.println("Choose help text type (bytes, lines, words): ");
            helpText = scanner.nextLine();
        }

        String decorator = null;
        System.out.println("Do you want decorator? (y/n): ");
        String decoratorChoice = scanner.nextLine().trim().toLowerCase();
        if (decoratorChoice.equals("yes")) {
            System.out.println("Choose decorator type (cycling, random, single, adding): ");
            String decoratorType = scanner.nextLine();
            decorator = decoratorType;
        }

        List<String> transformations = new ArrayList<>();
        if (decorator != null && (decorator.equals("cycling") || decorator.equals("random"))) {
            System.out.println("Enter transformations (comma-separated), or press Enter for none: ");
            String transformationInput = scanner.nextLine();
            if (!transformationInput.trim().isEmpty()) {
                transformations = Arrays.stream(transformationInput.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            }
        } else if (decorator != null && decorator.equals("single")) {
            System.out.println("Enter one transformation for the custom label: ");
            String transformationInput = scanner.nextLine();
            if (!transformationInput.trim().isEmpty()) {
                transformations.add(transformationInput.trim());
            }
        }

        return new CreatorEntry(type, text, helpText, decorator, transformations);
    }
}

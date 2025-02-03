package fmi.designpatterns.labels.reader;

import java.util.Scanner;

public class DecoratorReader {

    public static String read(Scanner scanner) {
        String decorator = null;
        String decoratorChoice = PromptHelper.prompt(scanner, "Do you want decorator? (y/n): ");

        if (decoratorChoice.equals("yes")) {
            decorator = PromptHelper.prompt(scanner, "Choose decorator type (cycling, random, single, adding): ");
        }

        return decorator;
    }

}

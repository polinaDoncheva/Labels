package fmi.designpatterns.labels.reader;

import java.util.Scanner;

public class PromptHelper {

    public static String prompt(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

}

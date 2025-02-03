package fmi.designpatterns.labels.reader;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LabelReader {

    public LabelEntry read(Scanner scanner) {
        String type = PromptHelper.prompt(scanner, "Enter the label type (simple, rich, dynamic): ");
        String text = null;

        if(!type.equalsIgnoreCase("dynamic")) {
            text = PromptHelper.prompt(scanner, "Enter the label text: ");
        }

        List<String> configuration = ConfigurationReader.read(scanner, type);
        String helpText = HelpReader.read(scanner);
        String decorator = DecoratorReader.read(scanner);
        Map<String, List<String>> transformations = TextTransformationsReader.read(scanner, decorator);

        return new LabelEntry(type, text, configuration, helpText, decorator, transformations);
    }

}

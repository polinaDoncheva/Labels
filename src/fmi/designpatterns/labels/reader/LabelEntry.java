package fmi.designpatterns.labels.reader;

import java.util.List;
import java.util.Map;

public record LabelEntry(String type, String text, List<String> configuration, String helpText,
                         String decorator, Map<String, List<String>> transformations) {
}

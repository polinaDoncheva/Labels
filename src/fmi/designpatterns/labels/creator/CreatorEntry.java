package fmi.designpatterns.labels.creator;

import java.util.List;

public record CreatorEntry(String type, String text, String helpText,
                           String tooltip, List<String> transformations) {
}

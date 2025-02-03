package fmi.designpatterns.labels.factory;

import fmi.designpatterns.labels.transformations.CensorTransformation;

import java.util.HashMap;
import java.util.Map;

public class CensorTransformationFactory {

    private static Map<String, CensorTransformation> storage = new HashMap<>();

    public static CensorTransformation createFrom(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Could not create censor transformation, word is null");
        }

        if (word.length() <= 4) {
            return storage.computeIfAbsent(word, CensorTransformation::new);
        }

        return new CensorTransformation(word);
    }

}

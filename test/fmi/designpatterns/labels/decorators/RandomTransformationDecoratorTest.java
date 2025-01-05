package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RandomTransformationDecoratorTest {
    private Label labelMock = Mockito.mock();

    @BeforeEach
    public void setUp() {
        when(labelMock.getText()).thenReturn(" some text ");
    }

    @Test
    public void testSingleTransformation() {
        TextTransformation transformationMock = Mockito.mock();
        when(transformationMock.transform(" some text ")).thenReturn("transformed text");

        List<TextTransformation> transformations = List.of(transformationMock);
        Label transformedLabel = new RandomTransformationDecorator(labelMock, transformations);

        assertEquals("transformed text", transformedLabel.getText());
    }

    @Test
    public void testRandomTransformation() {
        TextTransformation transformationMock1 = Mockito.mock();
        TextTransformation transformationMock2 = Mockito.mock();

        when(transformationMock1.transform(" some text ")).thenReturn("transformed text 1");
        when(transformationMock2.transform(" some text ")).thenReturn("transformed text 2");

        List<TextTransformation> transformations = Arrays.asList(transformationMock1, transformationMock2);

        Label transformedLabel = new RandomTransformationDecorator(labelMock, transformations);

        Set<String> possibleResults = new HashSet<>(Arrays.asList("transformed text 1", "transformed text 2"));

        String result = transformedLabel.getText();
        assertTrue(possibleResults.contains(result));
    }
/*
    @Test
    public void testMultipleChainedTransformations() {
        TextTransformation leftTrim = new LeftTrim();
        TextTransformation rightTrim = new RightTrim();
        TextTransformation capitalize = new Capitalize();
        TextTransformation normalizeSpace = new NormalizeSpace();
        TextTransformation decorate = new Decorate();
        TextTransformation censor = new Censor("text");
        TextTransformation replace = new Replace("abc", "d");

        List<TextTransformation> transformations1 = Arrays.asList(leftTrim, rightTrim);
        List<TextTransformation> transformations2 = Arrays.asList(capitalize, normalizeSpace);
        List<TextTransformation> transformations3 = Arrays.asList(decorate, censor, replace);

        Label transformedLabel1 = new RandomTransformationDecorator(label, transformations1);
        Label transformedLabel2 = new RandomTransformationDecorator(transformedLabel1, transformations2);
        Label transformedLabel3 = new RandomTransformationDecorator(transformedLabel2, transformations3);

        String result = transformedLabel3.getText();

        Set<String> possibleResults = new HashSet<>();
        possibleResults.add("-={  Some text }=-");
        possibleResults.add("-={  some *** }=-");
        possibleResults.add("-={  some d }=-");
        possibleResults.add("-={ Some text }=-");
        possibleResults.add("-={ Some *** }=-");
        possibleResults.add("-={ Some d }=-");
        possibleResults.add("-={ some *** }=-");
        possibleResults.add("-={ some d }=-");
        possibleResults.add(" Some text ");
        possibleResults.add(" some *** ");
        possibleResults.add(" some d ");
        possibleResults.add(" Some text ");
        possibleResults.add(" some *** ");
        possibleResults.add(" some d ");
        possibleResults.add(" Some text  ");
        possibleResults.add(" Some ***  ");
        possibleResults.add(" Some d  ");
        possibleResults.add(" Some text ");
        possibleResults.add(" Some *** ");
        possibleResults.add(" Some d ");
        possibleResults.add("Some text ");
        possibleResults.add("Some *** ");
        possibleResults.add("Some d ");
        possibleResults.add(" Some text");
        possibleResults.add(" some ***");
        possibleResults.add(" some d");
        possibleResults.add("Some text");
        possibleResults.add("Some ***");
        possibleResults.add("Some d");

        assertTrue(possibleResults.contains(result));
    }*/

    @Test
    public void testEmptyTransformationsList() {
        List<TextTransformation> emptyTransformations = List.of();

        Label transformedLabel = new RandomTransformationDecorator(labelMock, emptyTransformations);

        assertEquals(" some text ", transformedLabel.getText());
    }
}
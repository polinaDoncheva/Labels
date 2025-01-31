package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CyclingTransformationsDecoratorTest {
    private Label labelMock;

    private String labelText = " some text abc abcdef ";
    private String trimmedLabelText = "some text abc abcdef";
    private String capitalizedLabelText = "Some text abc abcdef";
    private String censoredLabelText = "Some text *** abcdef";
    private String decoratedLabelText = "-={ Some text *** abcdef }=-";

    @BeforeEach
    public void setUp() {
        labelMock = Mockito.mock();
        when(labelMock.getText()).thenReturn(labelText);
    }

    @Test
    public void testSingleTransformation() {
        TextTransformation transformationMock = Mockito.mock();

        when(transformationMock.transform(labelText)).thenReturn("transformed text");

        List<TextTransformation> transformations = List.of(transformationMock);
        Label transformedLabel = new CyclingTransformationsDecorator(labelMock, transformations);

        assertEquals("transformed text", transformedLabel.getText(),
            "Expected the transformed text to be returned after first call of method");
        assertEquals("transformed text", transformedLabel.getText(),
            "Expected the same transformed text to be returned after second call of method");

    }

    @Test
    public void testCycleThroughTransformations() {
        TextTransformation transformationMock1 = Mockito.mock();
        TextTransformation transformationMock2 = Mockito.mock();
        TextTransformation transformationMock3 = Mockito.mock();

        when(transformationMock1.transform(labelText)).thenReturn("transformed text 1");
        when(transformationMock2.transform(labelText)).thenReturn("transformed text 2");
        when(transformationMock3.transform(labelText)).thenReturn("transformed text 3");

        List<TextTransformation> transformations =
            List.of(transformationMock1, transformationMock2, transformationMock3);
        Label transformedLabel = new CyclingTransformationsDecorator(labelMock, transformations);

        assertEquals("transformed text 1", transformedLabel.getText(),
            "Expected first transformed text after first call of method");
        assertEquals("transformed text 2", transformedLabel.getText(),
            "Expected second transformed text after second call of method");
        assertEquals("transformed text 3", transformedLabel.getText(),
            "Expected third transformed text after third call of method");
        assertEquals("transformed text 1", transformedLabel.getText(),
            "Expected first transformed text after forth call of method with 3 transformations");
        assertEquals("transformed text 2", transformedLabel.getText(),
            "Expected second transformed text after fifth call of method with 3 transformations");
        assertEquals("transformed text 3", transformedLabel.getText(),
            "Expected third transformed text after sixth call of method with 3 transformations");
    }

    @Test
    public void testEmptyTransformationsList() {
        List<TextTransformation> emptyTransformations = List.of();

        Label transformedLabel = new CyclingTransformationsDecorator(labelMock, emptyTransformations);

        assertEquals(labelText, transformedLabel.getText());
    }

    @Test
    public void testMultipleChainedTransformations() {
        TextTransformation transformationMock1 = Mockito.mock();
        TextTransformation transformationMock2 = Mockito.mock();
        TextTransformation transformationMock3 = Mockito.mock();
        TextTransformation transformationMock4 = Mockito.mock();

        when(transformationMock1.transform(labelText)).thenReturn(trimmedLabelText);
        when(transformationMock2.transform(trimmedLabelText)).thenReturn(capitalizedLabelText);
        when(transformationMock3.transform(capitalizedLabelText)).thenReturn(censoredLabelText);
        when(transformationMock4.transform(capitalizedLabelText)).thenReturn(decoratedLabelText);

        List<TextTransformation> transformations1 = List.of(transformationMock1);
        List<TextTransformation> transformations2 = List.of(transformationMock2);
        List<TextTransformation> transformations3 = List.of(transformationMock3, transformationMock4);

        Label transformedLabel1 = new CyclingTransformationsDecorator(labelMock, transformations1);
        Label transformedLabel2 = new CyclingTransformationsDecorator(transformedLabel1, transformations2);
        Label transformedLabel3 = new CyclingTransformationsDecorator(transformedLabel2, transformations3);

        String result = transformedLabel3.getText();

        Set<String> possibleResults = new HashSet<>(List.of(censoredLabelText, decoratedLabelText));

        assertTrue(possibleResults.contains(result));
    }
}
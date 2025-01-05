package fmi.designpatterns.labels.decorators;

import fmi.designpatterns.labels.Label;
import fmi.designpatterns.labels.transformations.TextTransformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TextTransformationDecoratorTest {
    private TextTransformation transformationMock;
    private Label labelMock;

    @BeforeEach
    public void setUp() {
        transformationMock = Mockito.mock();
        labelMock = Mockito.mock();
    }

    @Test
    public void testGetTextWithOneTransformation() {
        when(labelMock.getText()).thenReturn(" some  text ");
        when(transformationMock.transform(" some  text ")).thenReturn("some  text");

        Label transformedLabel = new TextTransformationDecorator(labelMock, transformationMock);

        assertEquals("some  text", transformedLabel.getText(),
            "Expected the transformed string to be returned");
    }

    @Test
    public void testGetTextWithTwoTransformations() {
        TextTransformation leftTrimMock = Mockito.mock();
        TextTransformation rightTrimMock = Mockito.mock();

        when(labelMock.getText()).thenReturn(" some text ");
        when(leftTrimMock.transform(" some text ")).thenReturn("some text ");
        when(rightTrimMock.transform("some text ")).thenReturn("some text");

        Label trimLabel = new TextTransformationDecorator(
            new TextTransformationDecorator(labelMock, leftTrimMock), rightTrimMock);

        assertEquals("some text", trimLabel.getText(),
            "Expected all of the transformations to be applied");
    }

    @Test
    public void testGetTextWithThreeTransformations() {
        TextTransformation normalizeSpaceMock = mock(TextTransformation.class);
        TextTransformation capitalizeMock = mock(TextTransformation.class);
        TextTransformation decorateMock = mock(TextTransformation.class);

        when(labelMock.getText()).thenReturn("some   text  abc  abcdef ");
        when(normalizeSpaceMock.transform("some   text  abc  abcdef ")).thenReturn("some text abc abcdef ");
        when(capitalizeMock.transform("some text abc abcdef ")).thenReturn("Some text abc abcdef ");
        when(decorateMock.transform("Some text abc abcdef ")).thenReturn("-={ Some text abc abcdef  }=-");

        Label transformedLabel = new TextTransformationDecorator(
                                    new TextTransformationDecorator(new TextTransformationDecorator(labelMock, normalizeSpaceMock),
                                        capitalizeMock), decorateMock);

        assertEquals("-={ Some text abc abcdef  }=-", transformedLabel.getText(),
            "Expected all of the transformations to be applied");

    }


    @Test
    public void testGetTextWithMultipleTransformations() {
        TextTransformation leftTrimMock = mock(TextTransformation.class);
        TextTransformation rightTrimMock = mock(TextTransformation.class);
        TextTransformation capitalizeMock = mock(TextTransformation.class);
        TextTransformation normalizeSpaceMock = mock(TextTransformation.class);
        TextTransformation decorateMock = mock(TextTransformation.class);
        TextTransformation censorMock = mock(TextTransformation.class);
        TextTransformation replaceMock = mock(TextTransformation.class);

        when(labelMock.getText()).thenReturn("  some text abc abcdef  ");
        when(leftTrimMock.transform("  some text abc abcdef  ")).thenReturn("some text abc abcdef  ");
        when(rightTrimMock.transform("some text abc abcdef  ")).thenReturn("some text abc abcdef");
        when(capitalizeMock.transform("some text abc abcdef")).thenReturn("Some text abc abcdef");
        when(normalizeSpaceMock.transform("Some text abc abcdef")).thenReturn("Some text abc abcdef");
        when(decorateMock.transform("Some text abc abcdef")).thenReturn("-={ Some text abc abcdef }=-");
        when(censorMock.transform("-={ Some text abc abcdef }=-")).thenReturn("-={ Some text *** ***def }=-");
        when(replaceMock.transform("-={ Some text *** ***def }=-")).thenReturn("-={ Some text *** d }=-");

        Label leftTrimLabel = new TextTransformationDecorator(labelMock, leftTrimMock);
        Label rightTrimLabel = new TextTransformationDecorator(leftTrimLabel, rightTrimMock);
        Label capitalizeLabel = new TextTransformationDecorator(rightTrimLabel, capitalizeMock);
        Label normalizeSpaceLabel = new TextTransformationDecorator(capitalizeLabel, normalizeSpaceMock);
        Label decorateLabel = new TextTransformationDecorator(normalizeSpaceLabel, decorateMock);
        Label censorLabel = new TextTransformationDecorator(decorateLabel, censorMock);
        Label replaceLabel = new TextTransformationDecorator(censorLabel, replaceMock);

        assertEquals("-={ Some text *** d }=-", replaceLabel.getText(),
            "Expected all of the transformations to be applied");
    }

}

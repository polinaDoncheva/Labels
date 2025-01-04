package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTransformationTest {

    CapitalizeTransformation transformation;

    @BeforeEach
    void setup() {
        transformation = new CapitalizeTransformation();
    }

    @Test
    void testTransformWithNull() {
        assertThrows(TextTransformationException.class, () -> transformation.transform(null),
            "Expected exception to be thrown when input text is null");
    }

    @Test
    void testTransformWithEmpty() {
        assertTrue(transformation.transform("").isEmpty(),
            "Expected empty string when empty string is given");
    }

    @Test
    void testTransformWithLowercaseLetter() {
        assertEquals("SOme raNDOM text!!!",transformation.transform("sOme raNDOM text!!!"),
            "Expected first letter to be capitalized");
    }

    @Test
    void testTransformWithUppercaseLetter() {
        assertEquals("SOme raNDOM text!!!",transformation.transform("SOme raNDOM text!!!"),
            "Expected no change in the string when the first letter is capital");
    }

    @Test
    void testTransformWithSpaceFirst() {
        assertEquals(" ome raNDOM text!!!",transformation.transform(" ome raNDOM text!!!"),
            "Expected no change in the string when the first symbol is space");
    }

    @Test
    void testTransformWithSymbol() {
        assertEquals("!some raNDOM text!!!",transformation.transform("!some raNDOM text!!!"),
            "Expected no change in the string when the first symbol is not a letter");
    }
}
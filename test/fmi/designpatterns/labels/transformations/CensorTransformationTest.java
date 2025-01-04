package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CensorTransformationTest {
    CensorTransformation transformation;

    @BeforeEach
    void setup() {
        transformation = new CensorTransformation("^abc");
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
    void testTransformWithOneMatch() {
        assertEquals("abc random ^aBc text ****!!abb",transformation.transform("abc random ^aBc text ^abc!!abb"),
            "Expected last word to be censored");
    }

    @Test
    void testTransformWithNoMatch() {
        assertEquals("abc random ^aBc text abc",transformation.transform("abc random ^aBc text abc"),
            "Expected no change in the string when there are no matches");
    }
}
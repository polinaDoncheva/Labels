package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceTransformationTest {
    ReplaceTransformation transformation;

    @BeforeEach
    void setup() {
        transformation = new ReplaceTransformation("^abc", "fr");
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
        assertEquals("^Abcabc randomfr ^aBc text fr!!abb",transformation.transform("^Abcabc random^abc ^aBc text ^abc!!abb"),
            "Expected two words to be replaced");
    }

    @Test
    void testTransformWithNoMatch() {
        assertEquals("abc random ^aBc text abc",transformation.transform("abc random ^aBc text abc"),
            "Expected no change in the string when there are no matches");
    }

}
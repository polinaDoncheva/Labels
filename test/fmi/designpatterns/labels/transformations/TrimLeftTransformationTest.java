package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrimLeftTransformationTest {
    TrimLeftTransformation transformation;

    @BeforeEach
    void setup() {
        transformation = new TrimLeftTransformation();
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
    void testTransformWithOneSpace() {
        assertEquals("SOme  raNDOM text!!!",transformation.transform(" SOme  raNDOM text!!!"),
            "Expected only first space to be removed");
    }

    @Test
    void testTransformWithNoSpaces() {
        assertEquals("SOme raNDOM  text!!!  ",transformation.transform("SOme raNDOM  text!!!  "),
            "Expected no change in the string when there are no spaces in beginning");
    }

    @Test
    void testTransformWithMoreSpaces() {
        assertEquals("ome raNDOM text!! !  ",transformation.transform("     ome raNDOM text!! !  "),
            "Expected all spaces in beginning to be removed");
    }
}
package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalizeSpaceTransformationTest {
    NormalizeSpaceTransformation transformation;

    @BeforeEach
    void setup() {
        transformation = new NormalizeSpaceTransformation();
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
    void testTransformManySpaces() {
        assertEquals(" SOme raNDO M text! ! ! ",transformation.transform("  SOme  raNDO M text!  ! !   "),
            "Expected all consecutive spaces to be removed");
    }

    @Test
    void testTransformWithNoSpaces() {
        assertEquals("SOmeraNDOMtext!!!",transformation.transform("SOmeraNDOMtext!!!"),
            "Expected no change in the string when there are no spaces");
    }

    @Test
    void testTransformWithNoConsecutiveSpaces() {
        assertEquals(" ome raNDOM text!! !",transformation.transform(" ome raNDOM text!! !"),
            "Expected no change when there are no consecutive spaces");
    }
}
package fmi.designpatterns.labels.transformations;

import fmi.designpatterns.labels.exceptions.TextTransformationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecorateTransformationTest {
    DecorateTransformation transformation;

    @BeforeEach
    void setup() {
        transformation = new DecorateTransformation();
    }

    @Test
    void testTransformWithNull() {
        assertThrows(TextTransformationException.class, () -> transformation.transform(null),
            "Expected exception to be thrown when input text is null");
    }

    @Test
    void testTransformWithEmpty() {
        assertEquals("-={  }=-",transformation.transform(""),
            "Expected only decorations when empty string is given");
    }

    @Test
    void testTransformWithLongString() {
        assertEquals("-={  SOme  raNDOM text! !!  }=-",transformation.transform(" SOme  raNDOM text! !! "),
            "Expected decorations to be added in beginning and end without any changes to string");
    }

    @Test
    void testTransformWithOneSymbol() {
        assertEquals("-={ ! }=-",transformation.transform("!"),
            "Expected decorations to be added correctly");
    }
}
package esm.aoc.input;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputReaderTest {

    @Test
    public void testListOfStrings() {
        // Act
        List<String> input = InputReader.readStrings("test/listOfStrings.txt");
        // Assert
        assertEquals(List.of("a", "b", "c"), input);
    }

    @Test
    public void testListOfIntegers() {
        // Act
        List<Integer> input = InputReader.readIntegers("test/listOfIntegers.txt");
        // Assert
        assertEquals(List.of(199, 200, 208), input);
    }

    @Test
    public void testSpaceSeparated() {
        // Act
        List<List<String>> input = InputReader.readSpaceSeparated("test/spaceSeparated.txt");
        // Assert
        assertEquals(List.of(List.of("a", "1"), List.of("b", "2")), input);
    }

    @Test
    public void testSpaceSeparatedToClass() {
        // Act
        List<PairStringNumber> input = InputReader.readSpaceSeparated("test/spaceSeparated.txt", PairStringNumber::create);
        // Assert
        assertEquals(List.of(new PairStringNumber("a", 1), new PairStringNumber("b", 2)), input);
    }

}
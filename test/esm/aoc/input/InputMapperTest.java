package esm.aoc.input;

import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputMapperTest {

    @Test
    void testMatchPattern() {
        Pattern pattern = Pattern.compile("(.*), (.*) -> (.*)");
        List<String> matches = InputMapper.matchPattern("hello, foo -> bar", pattern);
        assertEquals(List.of("hello", "foo", "bar"), matches);
    }
}
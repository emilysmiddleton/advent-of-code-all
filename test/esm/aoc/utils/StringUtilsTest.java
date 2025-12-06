package esm.aoc.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    public void test_splitByLength() {
        String input = "abcdefghij";
        int length = 3;
        var result = StringUtils.splitByLength(input, length);

        assertEquals(4, result.size());
        assertEquals("abc", result.get(0));
        assertEquals("def", result.get(1));
        assertEquals("ghi", result.get(2));
        assertEquals("j", result.get(3));
    }

}
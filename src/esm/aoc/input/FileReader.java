package esm.aoc.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Reads a file into a list of lines.
 */
public class FileReader {

    public static void main(String[] args) {
        System.out.println(readGroups("day1"));
    }

    public static List<List<String>> readGroups(final String resourceName) {
        final List<List<String>> groups = new ArrayList<>();
        final List<String> current = new ArrayList<>();
        for (final var line : readStrings(resourceName)) {
            if (line.isEmpty()) {
                groups.add(current);
                current.clear();
            } else {
                current.add(line);
            }
        }
        groups.add(current);
        return groups;
    }

    public static List<String> readStrings(final String resourceName) {
        final List<String> lines = new ArrayList<>();
        try (InputStream inputStream = FileReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

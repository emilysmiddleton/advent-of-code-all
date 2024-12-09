package esm.aoc.y24.d09;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import esm.aoc.input.InputReader;

public class MainY24D09 {

    public static void main(String[] args) {
        final var input = getFiles(InputReader.readSeparated("2024/day09.txt", "").getFirst());
        System.out.println(STR."Part 1: \{solve1(input)}");
        System.out.println(STR."Part 2: \{solve2(input)}");
    }

    private static BigInteger solve1(final List<File> list) {
        final var rearranged = rearrange1(list);
        return getChecksum(rearranged);
    }

    private static BigInteger solve2(final List<File> list) {
        final var fileList = new FileList(list);
        fileList.rearrange();
        final var expanded = fileList.expand();
        return getChecksum(expanded);
    }

    private static List<Integer> rearrange1(final List<File> list) {
        final var expanded = expand(list);
        final List<Integer> rearranged = new ArrayList<>();
        int leftCounter = 0;
        int rightCounter = expanded.size() - 1;
        while (leftCounter <= rightCounter) {
            final var valueLeft = expanded.get(leftCounter);
            final var valueRight = expanded.get(rightCounter);
            if (valueLeft >= 0) {
                rearranged.add(valueLeft);
                leftCounter++;
            } else {
                if (valueRight >= 0) {
                    rearranged.add(valueRight);
                    leftCounter++;
                }
                rightCounter--;
            }
        }
        return rearranged;
    }

    private static BigInteger getChecksum(final List<Integer> list) {
        var checksum = BigInteger.ZERO;
        for (int i = 0; i < list.size(); i++) {
            checksum = checksum.add(BigInteger.valueOf(list.get(i)).multiply(BigInteger.valueOf(i)));
        }
        return checksum;
    }

    private static List<File> getFiles(final List<String> input) {
        final List<File> files = new ArrayList<>(input.size());
        for(int i = 0; i < input.size(); i++) {
            final var file = new File(i / 2, Integer.parseInt(input.get(i)), i % 2 == 1);
            if (file.size() > 0) {
                files.add(file);
            }
        }
        return files;
    }

    private static List<Integer> expand(final List<File> files) {
        return files.stream().map(file -> file.expand(-1)).flatMap(Collection::stream).toList();
    }

}

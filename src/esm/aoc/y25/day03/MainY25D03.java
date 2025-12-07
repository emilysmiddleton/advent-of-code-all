package esm.aoc.y25.day03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Utils;

public class MainY25D03 {

    public static void main(String[] args) {
        final List<List<Integer>> inputs = InputReader.readSeparated("2025/day03.txt", "")
            .stream()
            .map(InputMapper::getIntegerList)
            .toList();
        System.out.println(STR."Part 1: \{solve(inputs, 2)}");
        System.out.println(STR."Part 2: \{solve(inputs, 12)}");
    }

    private static long solve(final List<List<Integer>> inputs, final int targetSize) {
        return inputs.stream().mapToLong(input -> getJoltage(input, targetSize)).sum();
    }

    public static long getJoltage(final List<Integer> digits, final int targetSize) {
        Set<InterimState> states = Collections.singleton(new InterimState(new ArrayList<>(), digits));
        for (int i = 0; i < targetSize; i++) {
            states = addNextDigit(states, targetSize - i);
        }
        return states.stream().mapToLong(InterimState::getSum).max().orElse(0);
    }

    private static Set<InterimState> addNextDigit(final Set<InterimState> currentExamples, final int digitsLeft) {
        return currentExamples.stream().map(input -> addNextDigit(input, digitsLeft)).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    private static Set<InterimState> addNextDigit(final InterimState input, final int digitsLeft) {
        final Set<InterimState> results = new HashSet<>();
        final List<Integer> remaining = input.remainingList;
        // Find the biggest next digit that isn't too close to the end of the list to be usable.
        final Integer largestDigit = getLargestDigit(remaining.subList(0, remaining.size() - (digitsLeft - 1)));
        for (int i = 0; i < remaining.size() - (digitsLeft - 1); i++) {
            if (remaining.get(i).equals(largestDigit)) {
                results.add(new InterimState(Utils.addAndCopy(input.digits, largestDigit), remaining.subList(i + 1, remaining.size())));
            }
        }
        return results;
    }

    private static Integer getLargestDigit(final List<Integer> digits) {
        return digits.stream().max(Integer::compareTo).orElse(0);
    }

    private record InterimState(List<Integer> digits, List<Integer> remainingList) {
        long getSum() {
            return Long.parseLong(digits.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }

}

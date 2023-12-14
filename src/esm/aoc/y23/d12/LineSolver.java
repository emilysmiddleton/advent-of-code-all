package esm.aoc.y23.d12;

import esm.aoc.structures.collections.Counter;
import esm.aoc.structures.collections.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LineSolver {

    private final Map<Line, Long> cache = new LinkedHashMap<>();

    public long solve(final Line line) {
        if (line.targets().isEmpty()) {
            return 1;
        }
        if (cache.containsKey(line)) {
            return cache.get(line);
        }
        final var next = getNext(line);
        final var result = next.getKeys().stream().mapToLong(l -> solve(l) * next.getCount(l)).sum();
        cache.put(line, result);
        return result;
    }

    private Counter<Line> getNext(final Line line) {
        final Counter<Line> counter = new Counter<>();
        final var targets = line.targets();
        if (targets.isEmpty()) {
            return counter;
        }
        final var target = targets.get(0);
        final List<Integer> nextTargets = targets.size() == 1 ? Collections.emptyList() : targets.subList(1, targets.size());
        getGroupsUntilFirstHash(line.groups())
                .stream()
                .map(g -> nextLines(g.left(), g.right(), target, nextTargets))
                .flatMap(Collection::stream)
                .forEach(counter::add);
        return counter;
    }

    static List<Pair<String, List<String>>> getGroupsUntilFirstHash(final List<String> groups) {
        final List<Pair<String, List<String>>> matching = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            final var group = groups.get(i);
            final List<String> remaining = i == groups.size() - 1 ? Collections.emptyList() : groups.subList(i + 1, groups.size());
            final var pair = new Pair<>(group, remaining);
            matching.add(pair);
            if (group.contains("#")) {
                break;
            }
        }
        return matching;
    }

    static List<Line> nextLines(final String group, final List<String> remainingGroups, final int target, final List<Integer> remainingTargets) {
        final List<Line> nextLines = new ArrayList<>();
        final var remainders = getRemainders(group, target);
        for (final var remainder : remainders) {
            final List<String> newGroups = new ArrayList<>(remainingGroups);
            if (!remainder.isEmpty()) {
                newGroups.add(0, remainder);
            }
            final var newLine = new Line(newGroups, remainingTargets);
            if (newLine.viable()) {
                nextLines.add(newLine);
            }
        }
        return nextLines;
    }

    static List<String> getRemainders(final String group, final int target) {
        final List<String> remainders = new ArrayList<>();
        int i = 0;
        while (i <= group.length() - target) {
            final var left = group.substring(0, i);
            final var right = group.substring(i + target);
            if (!left.contains("#") && !right.startsWith("#")) {
                remainders.add(right.isEmpty() ? "" : right.substring(1));
            }
            i++;
        }
        return remainders;
    }

    static int indexOfNextGroupWithHash(final Line line) {
        for (int i = 0; i < line.groups().size(); i++) {
            if (line.groups().get(i).contains("#")) {
                return i;
            }
        }
        return -1;
    }

}

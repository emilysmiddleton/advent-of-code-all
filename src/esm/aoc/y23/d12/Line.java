package esm.aoc.y23.d12;

import esm.aoc.input.InputMapper;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public record Line(List<String> groups, List<Integer> targets) {

    public static Line parse(final List<String> parts, final boolean expand) {
        var left = parts.get(0);
        if (expand) {
            left = String.format("%s?%s?%s?%s?%s", left, left, left, left, left);
        }
        var right = parts.get(1);
        if (expand) {
            right = String.format("%s,%s,%s,%s,%s", right, right, right, right, right);
        }
        final var groups = Arrays.stream(left.split("\\.+")).filter(s -> !s.isEmpty()).toList();
        return new Line(groups, InputMapper.getIntegerList(InputMapper.getSeparatedStrings(right, ",")));
    }

    public boolean viable() {
        final var springs = groups.stream().mapToInt(String::length).sum();
        final var hashes = groups.stream().map(g -> List.of(g.split(""))).flatMap(Collection::stream).filter("#"::equals).count();
        final var targetTotal = targets.stream().mapToInt(i -> i).sum();
        return springs >= targetTotal && hashes <= targetTotal;
    }

    public boolean complete() {
        return targets.isEmpty();
    }


}

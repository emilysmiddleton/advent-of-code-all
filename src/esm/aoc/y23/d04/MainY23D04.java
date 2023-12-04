package esm.aoc.y23.d04;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Utils;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static esm.aoc.input.InputMapper.getIntegerList;

public class MainY23D04 {

    public static void main(String[] args) {
        final var input = InputReader.readSeparated("2023/day04.txt", " \\| ", s -> s.replaceFirst("Card.*: ", ""));
        final var matches = buildWinningCards(input);
        final var totals = buildTotalWinnings(matches);
        final var score = matches.values().stream().mapToInt(MainY23D04::getScore).sum();
        final var total = matches.keySet().stream().mapToInt(totals::get).sum();
        System.out.println("Part 1: " + score);
        System.out.println("Part 1: " + total);
    }

    private static void addLeaves(final Map<Integer, Set<Integer>> matches, final Map<Integer, Integer> winningCards) {
        final var leaves = matches.entrySet()
                                  .stream()
                                  .filter(e -> e.getValue().isEmpty() || e.getValue().stream().allMatch(winningCards::containsKey))
                                  .map(Map.Entry::getKey)
                                  .collect(Collectors.toSet());
        for (final var leaf : leaves) {
            final int total = matches.get(leaf).stream().mapToInt(winningCards::get).sum() + 1;
            winningCards.put(leaf, total);
        }
    }

    private static Map<Integer, Set<Integer>> buildWinningCards(final List<List<String>> input) {
        final Map<Integer, Set<Integer>> matches = new LinkedHashMap<>();
        for (int i = 1; i <= input.size(); i++) {
            final var parts = input.get(i - 1).stream().map(s -> List.of(s.trim().split(" +"))).toList();
            final var winners = getIntegerList(parts.get(0));
            final var held = getIntegerList(parts.get(1));
            final var union = Utils.union(new LinkedHashSet<>(winners), new LinkedHashSet<>(held));
            final Set<Integer> cards = new LinkedHashSet<>();
            for (int j = 1; j <= union.size(); j++) {
                cards.add(i + j);
            }
            matches.put(i, cards);
        }
        return matches;
    }

    public static Map<Integer, Integer> buildTotalWinnings(final Map<Integer, Set<Integer>> matches) {
        final Map<Integer, Integer> winningCards = new LinkedHashMap<>();
        while (winningCards.keySet().size() < matches.keySet().size()) {
            addLeaves(matches, winningCards);
        }
        return winningCards;
    }

    private static int getScore(final Set<Integer> matches) {
        return matches.isEmpty() ? 0 : (int) Math.pow(2, matches.size() - 1);
    }

}

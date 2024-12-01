package esm.aoc.y23.d07;

import esm.aoc.structures.collections.Multimap;
import esm.aoc.structures.collections.Utils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public record Hand(List<Integer> rawCards, Multimap<Integer, Integer> groupedCards, long bid) implements Comparable<Hand> {

    @Override
    public int compareTo( final Hand other) {
        final var rank1 = getRank();
        final var rank2 = other.getRank();
        if (rank1 != rank2) {
            return rank1 - rank2;
        }
        return Utils.zip(List.of(rawCards(), other.rawCards()), values -> values.get(0) - values.get(1))
                    .stream()
                    .filter(v -> v != 0)
                    .findFirst()
                    .orElse(0);
    }

    public static Hand parse(final List<String> parts) {
        final int bid = Integer.parseInt(parts.get(1));
        final List<Integer> rawCards = Arrays.stream(parts.get(0).split("")).map(c -> getValue(c, false)).toList();
        final Multimap<Integer, Integer> groupedCards = new Multimap<>();
        rawCards.forEach(card -> groupedCards.add(card, card));

        return new Hand(rawCards, groupedCards, bid);
    }

    public static Hand parseWithJokers(final List<String> parts) {
        final int bid = Integer.parseInt(parts.get(1));
        final List<Integer> rawCards = Arrays.stream(parts.get(0).split("")).map(c -> getValue(c, true)).toList();
        final long numberOfJokers = rawCards.stream().filter(card -> card == 0).count();
        if (numberOfJokers == 0) {
            return parse(parts);
        }
        final Multimap<Integer, Integer> groupedCards = new Multimap<>();
        rawCards.stream().filter(c -> c != 0).forEach(card -> groupedCards.add(card, card));
        final int biggest = groupedCards.values().stream().sorted(new CardComparator().reversed()).map(l -> l.get(0)).findFirst().orElse(14);
        for (int i = 0; i < numberOfJokers; i++) {
            groupedCards.add(biggest, biggest);
        }
        return new Hand(rawCards, groupedCards, bid);
    }

    private static int getValue(final String card, final boolean joker) {
        return switch (card) {
            case "A" -> 14;
            case "K" -> 13;
            case "Q" -> 12;
            case "J" -> joker ? 0 : 11;
            case "T" -> 10;
            default -> Integer.parseInt(card);
        };
    }

    public int getRank() {
        final var sizes = groupedCards.values().stream().map(List::size).toList();
        final int maxSize = getMaxSize();
        return switch (maxSize) {
            case 5 -> 7;
            case 4 -> 6;
            case 3 -> sizes.contains(2) ? 5 : 4;
            case 2 -> sizes.size() == 3 ? 3 : 2;
            default -> 1;
        };
    }

    private int getMaxSize() {
        return groupedCards.values().stream().mapToInt(List::size).max().orElseThrow();
    }

    public List<Integer> getCardsByOrder() {
        return groupedCards.values().stream().sorted(new CardComparator().reversed()).map(l -> l.get(0)).toList();
    }

    private static class CardComparator implements  Comparator<List<Integer>> {
        @Override
        public int compare(final List<Integer> o1, final List<Integer> o2) {
            if (o1.size() != o2.size()) {
                return o1.size() - o2.size();
            }
            return o1.get(0) - o2.get(0);
        }
    }

}

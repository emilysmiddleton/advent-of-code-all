package esm.aoc.y16.d11;

import esm.aoc.structures.collections.Multimap;

public class State {
    final Multimap<Integer, Item> items;

    public State(final Multimap<Integer, Item> items) {
        this.items = items;
    }

}

package esm.aoc.y23.d15;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Box {

    private final int index;
    private final List<String> labels = new ArrayList<>();
    private final Map<String, Lens> lenses = new LinkedHashMap<>();

    Box(final int index) {
        this.index = index;
    }

    void remove(final String label) {
        labels.remove(label);
        lenses.remove(label);
    }

    void add(final Lens lens) {
        if (!labels.contains(lens.label())) {
            labels.add(lens.label());
        }
        lenses.put(lens.label(), lens);
    }

    Map<String, Integer> getPower() {
        final Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < labels.size(); i++) {
            final var label = labels.get(i);
            final int power = (index + 1) * (i + 1) * lenses.get(label).value();
            map.put(label, power);
        }
        return map;
    }
}

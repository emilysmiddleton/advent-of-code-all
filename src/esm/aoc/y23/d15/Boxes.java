package esm.aoc.y23.d15;

import java.util.LinkedHashMap;
import java.util.Map;

class Boxes {

    private final Map<Integer, Box> boxes = new LinkedHashMap<>();

    void apply(final Instruction instruction) {
        final var index = instruction.box();
        if (!boxes.containsKey(index)) {
            boxes.put(index, new Box(index));
        }
        final var box = boxes.get(instruction.box());
        if (instruction.instruction().equals("=")) {
            box.add(new Lens(instruction.label(), instruction.value()));

        } else {
            box.remove(instruction.label());
        }
    }

    Map<String, Integer> getPower() {
        final Map<String, Integer> map = new LinkedHashMap<>();
        for (final var box : boxes.values()) {
            map.putAll(box.getPower());
        }
        return map;
    }

}

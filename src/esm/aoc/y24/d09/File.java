package esm.aoc.y24.d09;

import java.util.ArrayList;
import java.util.List;

public record File(int id, int size, boolean empty) {

    @Override
    public String toString() {
        return String.valueOf(empty ? "." : id).repeat(Math.max(0, size));
    }

    List<Integer> expand(final int sub) {
        final List<Integer> expanded = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            expanded.add(empty ? sub : id);
        }
        return expanded;
    }
}

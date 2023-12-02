package esm.aoc.input;

import java.util.List;
import java.util.Objects;

public record PairStringNumber(String string, Integer number) {

    public static PairStringNumber create(final List<String> strings) {
        return new PairStringNumber(strings.get(0), Integer.parseInt(strings.get(1)));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PairStringNumber that = (PairStringNumber) o;
        return Objects.equals(string, that.string) && Objects.equals(number, that.number);
    }
}

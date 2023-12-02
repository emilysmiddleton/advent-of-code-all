package esm.aoc.input;

import java.util.List;
import java.util.Objects;

public class PairStringNumber {

    public static PairStringNumber create(final List<String> strings) {
        return new PairStringNumber(strings.get(0), Integer.parseInt(strings.get(1)));
    }

    private final String string;
    private final Integer number;

    public PairStringNumber(final String string, final Integer number) {
        this.string = string;
        this.number = number;
    }

    public String getString() {
        return string;
    }

    public Integer getNumber() {
        return number;
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

    @Override
    public int hashCode() {
        return Objects.hash(string, number);
    }
}

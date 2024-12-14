package esm.aoc.y24.d13;

import java.util.List;
import java.util.Optional;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Pair;

public class MainY24D13 {

    private static final long OFFSET = 10000000000000L;

    public static void main(String[] args) {
        final var configs = InputReader.readGroups("2024/day13.txt", Config::parse);
        System.out.println(STR."Part 1: \{solve(configs, 0)}");
        System.out.println(STR."Part 2: \{solve(configs, OFFSET)}");
    }

    private static long solve(final List<Config> configList, final long offset) {
        return configList.stream().map(config -> solveEquations(config, offset))
                .filter(Optional::isPresent).map(Optional::get)
                .mapToLong(p -> p.left() * 3 + p.right())
                .sum();
    }

    private static Optional<Pair<Long, Long>> solveEquations(final Config config, final long offset) {

        final var ax = config.buttonA().x();
        final var ay = config.buttonA().y();
        final var bx = config.buttonB().x();
        final var by = config.buttonB().y();
        final var px = config.prize().x();
        final var py = config.prize().y();
        /*
         *
         * Simultaneous equations!!
         *
         * We are trying to solve m,n for:
         *   1. ax.m + bx.n = px + offset
         *   2. ay.m + by.n = py + offset
         *
         * (2) - (1) yields
         *
         *  m(ay - ax) + n(by - bx) = py - px.
         *
         * If ay = ax or by = bx we can solve this more simply.
         *
         */

        if (ax == ay) {
            /*
             * n = (py - px) / (by - bx)
             * m = ((px + offset) - (bx.n)) / ax
             */
            final var top1 = py - px;
            final var bottom1 = by - bx;
            if (top1 % bottom1 != 0) {
                return Optional.empty();
            }
            final var n = top1 / bottom1;
            final var top2 = ((px + offset) - (bx * n));
            if (top2 % ax != 0) {
                return Optional.empty();
            }
            final var m = top2 / ax;
            return Optional.of(new Pair<>(m, n));
        }

        if (bx == by) {
            /*
             * m = (py - px) / (ay - ax)
             * n = ((px + offset) - (ax.m)) / bx
             */
            final var top1 = py - px;
            final var bottom1 = ay - ax;
            if (top1 % bottom1 != 0) {
                return Optional.empty();
            }
            final var m = top1 / bottom1;
            final var top2 = ((px + offset) - (ax * m));
            if (top2 % bx != 0) {
                return Optional.empty();
            }
            final var n = top2 / bx;
            return Optional.of(new Pair<>(m, n));
        }

        /*
         * Rearrange:
         *
         *  1. m = (px + offset - bx.n) / ax
         *  2. m = (py + offset - by.n) / ay
         *
         * So
         *     (px + offset - bx.n) / ax = (py + offset - by.n) / ay
         *     ay.px + ay.offset - ay.bx.n = ax.py + ax.offset - ax.by.n
         *     ax.by.n - ay.bx.n = ax.py + ax.offset - ay.px - ay.offset
         *     n (ax.by - ay.bx) = ax.py - ay.px + (ax - ay).offset
         */

        final var top1 = (ax * py) - (ay * px) + ((ax - ay) * offset);
        final var bottom1 = (ax * by) - (ay * bx);

        if (top1 % bottom1 != 0) {
            return Optional.empty();
        }

        final var n = top1 / bottom1;

        /*
         * Sub back into
         *   m(ay - ax) + n(by - bx) = py - px
         *
         *  m = ((py - px) - n(by - bx)) / (ay - ax)
         */

        final var top2 = (py - px) - (n * (by - bx));
        final var bottom2 = ay - ax;
        if (top2 % bottom2 != 0) {
            return Optional.empty();
        }

        final var m = top2 / bottom2;
        return Optional.of(new Pair<>(m, n));
    }

}

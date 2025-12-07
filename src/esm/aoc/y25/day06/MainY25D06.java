package esm.aoc.y25.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Range;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.maths.Operator;

public class MainY25D06 {

    public static void main(String[] args) {
        final CharGrid grid = InputReader.readCharGrid("2025/day06.txt");
        final List<Column> columns = getColumns(grid);
        final List<Equation> part1 = columns.stream().map(MainY25D06::columnToEquationPart1).toList();
        final List<Equation> part2 = columns.stream().map(MainY25D06::columnToEquationPart2).toList();
        System.out.println(STR."Part 1: \{solve(part1)}");
        System.out.println(STR."Part 1: \{solve(part2)}");
    }

    private static long solve(final List<Equation> equations) {
        return equations.stream().mapToLong(Equation::solve).sum();
    }

    private static Equation columnToEquationPart1(final Column column) {
        List<Long> values = new ArrayList<>();
        for (int y = 1; y <= column.column.getMaxY(); y++) {
            values.add(Long.parseLong(String.join("", column.column.getRowValues(y)).trim()));
        }
        return new Equation(values, column.operator);
    }

    private static Equation columnToEquationPart2(final Column column) {
        List<Long> values = new ArrayList<>();
        for (long x = column.column.getMinX(); x <= column.column.getMaxX(); x++) {
            List<String> columnValue = column.column.getColumnValues(x).reversed();
            values.add(Long.parseLong(String.join("", columnValue).trim()));
        }
        return new Equation(values, column.operator);
    }

    private static List<Column> getColumns(final CharGrid grid) {
        final List<Range> operationRanges = getOperationRanges(grid);
        final List<Column> columns = new ArrayList<>();
        for (final Range range : operationRanges) {
            final Map<Coordinate, String> columnMap = grid.getMap().entrySet().stream()
                .filter(e -> e.getKey().x() >= range.start() && e.getKey().x() <= range.end() && e.getKey().y() != 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            columns.add(new Column(new CharGrid(columnMap), Operator.parse(grid.get(range.start(), 0))));
        }
        return columns;
    }

    private static List<Range> getOperationRanges(final CharGrid charGrid) {
        final List<Long> operations = charGrid.getMap().entrySet().stream()
            .filter(e -> e.getValue().equals("+") || e.getValue().equals("*"))
            .map(e -> e.getKey().x())
            .sorted()
            .toList();
        final List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < operations.size() - 1; i++) {
            ranges.add(new Range(operations.get(i), operations.get(i + 1) - 2));
        }
        ranges.add(new Range(operations.getLast(), charGrid.getMaxX()));
        return ranges;
    }

    private record Column(CharGrid column, Operator operator) {
    }

    private record Equation(List<Long> values, Operator operator) {
        long solve() {
            return values.stream().reduce(operator.getIdentity(), operator::apply);
        }
    }

}

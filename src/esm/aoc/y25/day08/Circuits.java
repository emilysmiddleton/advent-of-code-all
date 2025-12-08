package esm.aoc.y25.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import esm.aoc.structures.grid.Coordinate3D;

public class Circuits {

    private final DistancesQueue distances;
    private final List<Set<Coordinate3D>> circuits = new ArrayList<>();
    private JunctionPair lastConnection;

    public Circuits(final List<Coordinate3D> junctions) {
        this.distances = new DistancesQueue(junctions);
        for (final Coordinate3D junction : junctions) {
            circuits.add(new HashSet<>(Collections.singleton(junction)));
        }
    }

    public void addConnection() {
        // Get the next smallest pair
        final JunctionPair smallest = distances.getNext();
        lastConnection = smallest;
        // Find out if either coordinate is already in a circuit
        final Set<Coordinate3D> circuitLeft = findCircuitContaining(smallest.left());
        final Set<Coordinate3D> circuitRight = findCircuitContaining(smallest.right());
        // Both in a separate circuit - combine them.
        // If they were already both in the same circuit we do nothing.
        if (circuitLeft != circuitRight) {
            circuits.remove(circuitLeft);
            circuits.remove(circuitRight);
            final Set<Coordinate3D> combined = new HashSet<>(circuitLeft);
            combined.addAll(circuitRight);
            circuits.add(combined);
        }
    }

    public boolean isComplete() {
        return circuits.size() == 1;
    }

    public JunctionPair getLastConnection() {
        return lastConnection;
    }

    public List<Set<Coordinate3D>> getCircuits() {
        return circuits;
    }

    private Set<Coordinate3D> findCircuitContaining(final Coordinate3D coordinate) {
        return circuits.stream()
            .filter(circuit -> circuit.contains(coordinate))
            .findFirst()
            .orElse(null);
    }

}

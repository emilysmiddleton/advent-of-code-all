package esm.aoc.y25.day08;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import esm.aoc.structures.grid.Coordinate3D;

public class DistancesQueue {

    private final Queue<JunctionPair> queue;

    public DistancesQueue(final List<Coordinate3D> junctions) {
        queue = new PriorityQueue<>((a, b) -> Float.compare(a.distance(), b.distance()));
        for (int i = 0; i < junctions.size(); i++) {
            for (int j = i + 1; j < junctions.size(); j++) {
                final Coordinate3D a = junctions.get(i);
                final Coordinate3D b = junctions.get(j);
                final float distance = a.getEuclideanDistance(b);
                queue.add(new JunctionPair(a, b, distance));
            }
        }
    }

    public JunctionPair getNext() {
        return queue.remove();
    }

}

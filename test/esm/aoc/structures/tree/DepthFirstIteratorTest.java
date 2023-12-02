package esm.aoc.structures.tree;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class DepthFirstIteratorTest {

    @Test
    public void test() {
        // Arrange
        final var d = new SimpleTreeNode<>("d", emptyList());
        final var e = new SimpleTreeNode<>("e", emptyList());
        final var f = new SimpleTreeNode<>("f", emptyList());
        final var g = new SimpleTreeNode<>("g", emptyList());
        final var b = new SimpleTreeNode<>("b", List.of(d, e));
        final var c = new SimpleTreeNode<>("c", List.of(f, g));
        final var a = new SimpleTreeNode<>("a", List.of(b, c));

        // Act / Assert
        final var iterator = new DepthFirstIterator<>(a);
        assertEquals(a, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(d, iterator.next());
        assertEquals(e, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(f, iterator.next());
        assertEquals(g, iterator.next());
        assertFalse(iterator.hasNext());
    }

}
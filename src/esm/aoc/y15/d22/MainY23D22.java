package esm.aoc.y15.d22;

import esm.aoc.structures.tree.DepthFirstIterator;
import esm.aoc.structures.tree.LazyTreeNode;
import esm.aoc.structures.tree.TreeNode;

public class MainY23D22 {

    public static void main(String[] args) {
        GameState startingState = new GameState(
                true,
                new Wizard(500, 50, 0, 0, 0),
                new Boss(58, 9),
                0,
                0
        );
        GameStateFunction function = new GameStateFunction();
        TreeNode<GameState> rootNode = new LazyTreeNode<>(function, startingState);
        final DepthFirstIterator<GameState> dfs = new DepthFirstIterator<>(rootNode);
        int min = Integer.MAX_VALUE;
        while (dfs.hasNext()) {
            final GameState next = dfs.next().getState();
            if (next.isBossDead()) {
                min = Math.min(min, next.getManaTotal());
            }
        }
        System.out.println(min);
    }
}

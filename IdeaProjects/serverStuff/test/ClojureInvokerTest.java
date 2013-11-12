import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ClojureInvokerTest {
    @Test
    public void testEvaluatesBoardStatus() throws IOException {
        ClojureInvoker clojureInvoker = new ClojureInvoker();
        ArrayList board = new ArrayList();
        board.add("x");
        board.add(2);
        board.add(3);
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);
        Object gameOver = clojureInvoker.gameOver(board);
        Assert.assertEquals(false, gameOver);

        board = new ArrayList();
        board.add("x");
        board.add("x");
        board.add("x");
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);
        gameOver = clojureInvoker.gameOver(board);
        Assert.assertEquals(true, gameOver);
    }
}

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ClojureParserTest {
    @Test
    public void testEvaluatesBoardStatus() {
        ClojureParser clojureParser = new ClojureParser();
        ArrayList board = new ArrayList();
        board.add(1);
        board.add(2);
        board.add(3);
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);

        Object gameOver = clojureParser.gameOver(board);

        Assert.assertEquals(false, gameOver);


    }
}

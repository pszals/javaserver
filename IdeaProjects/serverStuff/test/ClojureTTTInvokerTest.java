import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClojureTTTInvokerTest {
    @Test
    public void testEvaluatesBoardStatus() throws IOException {
        ClojureTTTInvoker clojureTTTInvoker = new ClojureTTTInvoker();
        ArrayList board = new ArrayList();
        board.add("x");
        board.add("x");
        board.add(3);
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);
        Object gameOver = clojureTTTInvoker.gameOver(board);
        Assert.assertEquals(false, gameOver);

        board.set(2, "x");
        gameOver = clojureTTTInvoker.gameOver(board);
        Assert.assertEquals(true, gameOver);
    }

    @Test
    public void testWinnerOnBoard() {
        ClojureTTTInvoker clojureTTTInvoker = new ClojureTTTInvoker();
        ArrayList board = new ArrayList();
        board.add("x");
        board.add("x");
        board.add("x");
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);
        Object isThereWinner = clojureTTTInvoker.winnerOnBoard(board);
        assertTrue((Boolean) isThereWinner);

        board.set(1, 2);
        isThereWinner = clojureTTTInvoker.winnerOnBoard(board);
        assertFalse((Boolean) isThereWinner);
    }

    @Test
    public void testGetWinningPiece() {
        ClojureTTTInvoker clojureTTTInvoker = new ClojureTTTInvoker();
        ArrayList board = new ArrayList();
        board.add("x");
        board.add("x");
        board.add("x");
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);
        Object winningPiece = clojureTTTInvoker.getWinningPiece(board);
        assertEquals("x", winningPiece);
    }

    @Test
    public void testGetAiMove() {
        ClojureTTTInvoker clojureTTTInvoker = new ClojureTTTInvoker();
        ArrayList board = new ArrayList();
        board.add("x");
        board.add(2);
        board.add(3);
        board.add(4);
        board.add("o");
        board.add(6);
        board.add(7);
        board.add(8);
        board.add("x");
        Object aiMove = clojureTTTInvoker.getBoardWithAiMove(board);

        board.set(1, "o");
        assertEquals(board, aiMove);
    }

    @Test
    public void testPlacesPieceOnBoard() {
        ClojureTTTInvoker clojureTTTInvoker = new ClojureTTTInvoker();
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

        Object placedPiece = clojureTTTInvoker.placePiece(3, board);
        board.set(2, "x");
        assertEquals(board, placedPiece);

    }
}

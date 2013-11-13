import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;


public class ClojureBoardHolderTest {
    @Test
    public void testCreatesBlankBoard() {
        ClojureBoardHolder boardHolder = new ClojureBoardHolder();
        boardHolder.resetBoard();
        ArrayList board = boardHolder.getBoard();
        ArrayList newBoard = blankBoard();

        assertEquals(newBoard, board);
    }

    @Test
    public void testPlacesPiece() {
        ClojureBoardHolder boardHolder = new ClojureBoardHolder();
        ArrayList board = blankBoard();
        board.set(0, "x");
        boardHolder.resetBoard();
        boardHolder.placePiece(1);
        assertEquals(board, boardHolder.getBoard());
    }

    private ArrayList blankBoard() {
        ArrayList board = new ArrayList();
        for (int x = 1; x < 10; x++) {
            board.add(x);
        }
        return board;
    }
}

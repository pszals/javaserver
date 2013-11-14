import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;


public class ClojureBoardHolderTest {
    private ClojureBoardHolder boardHolder = new ClojureBoardHolder();

    @Test
    public void testCreatesBlankBoard() {
        boardHolder.resetBoard();
        ArrayList board = boardHolder.getBoard();
        ArrayList newBoard = blankBoard();

        assertEquals(newBoard, board);
    }

    @Test
    public void testPlacesPiece() {
        ArrayList board = blankBoard();
        board.set(0, "x");
        boardHolder.resetBoard();
        boardHolder.placePiece(1);
        assertEquals(board, boardHolder.getBoard());
    }

    @Test
    public void testWritesBoardStateToFile() throws IOException {
        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        ArrayList board = blankBoard();
        boardHolder.recordBoardState(board);

        assertEquals(new String(myFileReader.readFileContents(), "UTF-8"), "123456789");
    }

    @Test
    public void testReadFileWithBoardState() throws IOException {
        String boardState = boardHolder.readBoardStateFile();
        assert("123456789".equals(boardState));
    }

    private ArrayList blankBoard() {
        ArrayList board = new ArrayList();
        for (int x = 1; x < 10; x++) {
            board.add(x);
        }
        return board;
    }
}

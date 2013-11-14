import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ClojureBoardParserTest {
    @Test
    public void testConvertsStringIntoBoard() {
        ClojureBoardParser boardParser = new ClojureBoardParser();
        String board = "123456789";
        ArrayList parsedBoard = boardParser.convertStringToBoardArray(board);
        ArrayList arrayBoard = new ArrayList();
        for (int i = 1; i < 10; i++){
            arrayBoard.add(i);
        }
        assertEquals(arrayBoard, parsedBoard);

        board = "x23456789";
        parsedBoard = boardParser.convertStringToBoardArray(board);
        arrayBoard.set(0, "x");

        assertEquals(arrayBoard, parsedBoard);
    }

    @Test
    public void testConvertsBoardToString() {
        ArrayList board = new ArrayList();
        for (int i = 1; i < 10; i++) {
            board.add(i);
        }

        String stringBoard = ClojureBoardParser.convertBoardToString(board);
        assertTrue(stringBoard.equals("123456789"));

        board.set(0, "x");
        stringBoard = ClojureBoardParser.convertBoardToString(board);
        assertTrue(stringBoard.equals("x23456789"));
    }
}

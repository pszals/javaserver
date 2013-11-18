import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ClojureTTTBoardParserTest {
    @Test
    public void testConvertsStringIntoBoard() {
        ClojureTTTBoardParser boardParser = new ClojureTTTBoardParser();
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

        String stringBoard = ClojureTTTBoardParser.convertBoardToString(board);
        assertTrue(stringBoard.equals("123456789"));

        board.set(0, "x");
        stringBoard = ClojureTTTBoardParser.convertBoardToString(board);
        assertTrue(stringBoard.equals("x23456789"));
    }

    @Test
    public void testRemovesBracketsAndWhiteSpaceFromBoard() {
        String persistentVectorBoard = "[1 2 \"x\" 4 5 6 7 8 9]";
        String convertedBoard = ClojureTTTBoardParser.formatClojureBoard(persistentVectorBoard);
        assert("12x456789".equals(convertedBoard));
    }
}

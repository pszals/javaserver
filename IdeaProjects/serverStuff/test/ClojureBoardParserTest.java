import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class ClojureBoardParserTest {
    @Test
    public void testConvertsStringIntoBoard() {
        ClojureBoardParser boardParser = new ClojureBoardParser();
        String board = "123456789";
        ArrayList parsedBoard = boardParser.parseStringBoard(board);
        ArrayList arrayBoard = new ArrayList();
        for (int i = 1; i < 10; i++){
            arrayBoard.add(i);
        }
        assertEquals(arrayBoard, parsedBoard);

        board = "x23456789";
        parsedBoard = boardParser.parseStringBoard(board);
        arrayBoard.set(0, "x");

        assertEquals(arrayBoard, parsedBoard);



    }
}

import java.util.ArrayList;

import static java.lang.Character.getNumericValue;

public class ClojureBoardParser {
    public ArrayList parseStringBoard(String board) {
        ArrayList parsedBoard = new ArrayList();

        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);
            int charValue = getNumericValue(c);
            if (charValue > 0 && charValue < 10) {
                int value = getNumericValue(c);
                parsedBoard.add(value);
            } else {
                String value = Character.toString(c);
                parsedBoard.add(value);
            }
        }

        return parsedBoard;
    }
}

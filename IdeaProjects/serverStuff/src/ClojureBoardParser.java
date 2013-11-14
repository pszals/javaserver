import java.util.ArrayList;

import static java.lang.Character.getNumericValue;

public class ClojureBoardParser {
    public ArrayList convertStringToBoardArray(String board) {
        ArrayList parsedBoard = new ArrayList();

        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);
            int numericValueOfChar = getNumericValue(c);

            if (numericValueOfChar > 0 && numericValueOfChar < 10) {
                parsedBoard.add(numericValueOfChar);
            } else {
                String value = Character.toString(c);
                parsedBoard.add(value);
            }
        }

        return parsedBoard;
    }

    public static String convertBoardToString(ArrayList board) {
        String stringBoard = "";
        for (int i = 0; i < board.size(); i++) {
            stringBoard += board.get(i).toString();
        }
        return stringBoard;
    }
}

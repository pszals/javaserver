import java.util.ArrayList;

import static java.lang.Character.getNumericValue;

public class ClojureBoardParser {
    public ArrayList convertStringToBoardArray(String board) {
        ArrayList newBoard = new ArrayList();

        for (int i = 0; i < board.length(); i++) {
            char valueOfSquare = board.charAt(i);
            int numericValueOfChar = getNumericValue(valueOfSquare);

            if (squareIsEmpty(valueOfSquare, board)) {
                newBoard.add(numericValueOfChar);
            } else {
                String value = Character.toString(valueOfSquare);
                newBoard.add(value);
            }
        }

        return newBoard;
    }

    private Boolean squareIsEmpty(char potentialMove, String board){
        int numericValueOfChar = getNumericValue(potentialMove);
        return numericValueOfChar < (board.length() + 1);
    }

    public static String convertBoardToString(ArrayList board) {
        String stringBoard = "";
        for (int i = 0; i < board.size(); i++) {
            stringBoard += board.get(i).toString();
        }
        return stringBoard;
    }

    public static String formatPVBoard(String persistentVectorBoard) {
        String withoutBrackets = removeBrackets(persistentVectorBoard);
        return removeSpacesAndQuotes(withoutBrackets);
    }

    private static String removeBrackets(String persistentVectorBoard) {
        return persistentVectorBoard.substring(1, persistentVectorBoard.length() - 1);
    }

    private static String removeSpacesAndQuotes(String removedBrackets) {
        return removedBrackets.replaceAll("\\s+","").replaceAll("\"+", "");
    }


}

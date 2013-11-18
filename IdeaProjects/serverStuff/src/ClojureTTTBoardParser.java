import java.util.ArrayList;

import static java.lang.Character.getNumericValue;

public class ClojureTTTBoardParser {
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

    public static String convertBoardToString(ArrayList board) {
        String stringBoard = "";
        for (int i = 0; i < board.size(); i++) {
            stringBoard += board.get(i).toString();
        }
        return stringBoard;
    }

    public static String formatClojureBoard(String persistentVectorBoardAsString) {
        String withoutBrackets = removeBrackets(persistentVectorBoardAsString);
        return removeSpacesAndQuotes(withoutBrackets);
    }

    private Boolean squareIsEmpty(char potentialMove, String board){
        int numericValueOfChar = getNumericValue(potentialMove);
        return numericValueOfChar < (board.length() + 1);
    }

    private static String removeBrackets(String persistentVectorBoard) {
        return persistentVectorBoard.substring(1, persistentVectorBoard.length() - 1);
    }

    private static String removeSpacesAndQuotes(String removedBrackets) {
        return removedBrackets.replaceAll("\\s+","").replaceAll("\"+", "");
    }


}

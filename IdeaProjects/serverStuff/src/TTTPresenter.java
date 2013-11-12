import java.util.ArrayList;
import java.util.List;

public class TTTPresenter {
    public ArrayList convertSquareToHtml(ArrayList board) {
        ArrayList htmlBoardWithIndex = new ArrayList();
        int numberOfSquares = board.size();
        for (int index = 0; index < numberOfSquares; index += 1) {
            htmlBoardWithIndex.add(
                    "<td id=" + index + ">" +
                            "<form action='/play_game' method='POST'>" +
                            "<input type='submit' name='square' value=" + board.get(index) +">" +
                            "</form>" +
                            "</td>");
        }
        return htmlBoardWithIndex;
    }

    public String wrapEachRowInHtml(List<Object> squares) {
        String board = "";
        for (int index = 0; index < squares.size(); index += Math.sqrt(squares.size())){
            List<Object> squaresSubList = squares.subList(index, (index + (int) Math.sqrt(squares.size())));
            board += combineRowIntoString(squaresSubList);
        }
        return board;
    }

    private String combineRowIntoString(List<Object> squaresSubList) {
        String rowString = "";
        for (int index = 0; index < squaresSubList.size(); index += 1) {
            rowString += squaresSubList.get(index);
        }
        return wrapRowInHtmlTags(rowString);
    }

    public String wrapInPlayGameForm(String page) {
        return "<form action='/play_game' method='POST'>" + page + "</form>";
    }

    public String wrapInHtml(String page) {
        return "<html>" + page + "</html>";
    }

    public String wrapRowInHtmlTags(String page) {
        return "<tr>" + page + "</tr>";
    }

    public String boardAsHtml(ArrayList board) {
        String boardAsHtml =

        wrapInHtml(
        wrapInPlayGameForm(
        wrapEachRowInHtml(
            convertSquareToHtml(board))));
        return boardAsHtml;
    }
}

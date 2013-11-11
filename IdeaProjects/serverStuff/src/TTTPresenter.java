import java.util.ArrayList;

public class TTTPresenter {
    public String wrapInHtml(String page) {
        return "<html>" + page + "</html>";
    }

    public String wrapInPlayGameForm(String page) {
        return "<form action='/play_game' method='POST'>" + page + "</form>";
    }

    public ArrayList convertRowToHtml(ArrayList board) {
        ArrayList htmlBoardWithIndex = new ArrayList();
        int numberOfSquares = board.size();
        for (int index = 0; index < numberOfSquares; index += 1) {

            htmlBoardWithIndex.add(
                    "<td id=" + index + ">" +
                    "<form action='/play_game' method='POST'>" +
                    "<input type='submit' name='square' value=" + board.get(index) +">");
        }

        return htmlBoardWithIndex;
    }
}

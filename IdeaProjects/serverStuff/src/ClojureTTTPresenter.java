import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClojureTTTPresenter {

     public ArrayList convertSquaresToHtml(ArrayList board) {
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

    public String wrapInHtmlHead(String page) {
        return "<html>" +
        "<head>" +
        "<meta charset=\"utf-8\" />" +
        "<title>Tic Tac Toe</title>" +
        "<link rel=\"stylesheet\" type=\"text/css\" href=\"tictac.css\" />" +
        "</head>" +
        "<body id=\"home\"" +
        page + "</html>";
    }

    public String wrapRowInHtmlTags(String page) {
        return "<tr>" + page + "</tr>";
    }

    public String displayHtmlBoard(ArrayList board) throws IOException {
        String boardAsHtml =
            wrapInHtmlHead(
                    wrapInResetBoard(
                            wrapInPlayGameForm(
                                    wrapInTableTags(
                                            wrapEachRowInHtml(
                                                    convertSquaresToHtml(board))))));
        ClojureInvoker game = new ClojureInvoker();
        if (game.gameOver(board).equals(true))
            boardAsHtml = addMessage(boardAsHtml);
        return boardAsHtml;
    }

    public String wrapInTableTags(String page) {
        return "<table id=\"tictac\">" + page + "</table>";
    }

    public String wrapInResetBoard(String page) {
        return page + "<form action='/reset' method='POST'>" +
                "<input type='submit' name='reset' value=Reset>" +
                "</form>";
    }

    public String addMessage(String page) {
        return "<p>GAME OVER</p>" + page;
    }
}

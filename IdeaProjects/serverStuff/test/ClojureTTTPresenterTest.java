import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class ClojureTTTPresenterTest {
    private ClojureTTTPresenter clojureTttPresenter;
    private String page;

    @Before
    public void initObjects(){
        clojureTttPresenter = new ClojureTTTPresenter();
        page = "hello";
    }

    @Test
    public void testWrapsHtmlTags() {
        assertEquals("<html>" +
                "<head>" +
                "<meta charset=\"utf-8\" />" +
                "<title>Tic Tac Toe</title>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"tictac.css\" />"  +
                "</head><body id=\"home\"" +
                "hello</html>", clojureTttPresenter.wrapInHtmlHead(page));
    }

    @Test
    public void testWrapsInTableTags() {
        assertEquals("<table id=\"tictac\">hello</table>", clojureTttPresenter.wrapInTableTags(page));
    }

    @Test
    public void testWrapsFormToPlayGame() {
        assertEquals("<form action='/play_game' method='POST'>hello</form>", clojureTttPresenter.wrapInPlayGameForm(page));
    }

    @Test
    public void testAddsResetBoardButton() {
        assertEquals("hello<form action='/reset' method='POST'><input type='submit' name='reset' value=Reset></form>", clojureTttPresenter.wrapInResetBoard(page));
    }

    @Test
    public void testWrapsRowInTableRow() {
        assertEquals("<tr>hello</tr>", clojureTttPresenter.wrapRowInHtmlTags(page));
    }

    @Test
    public void testWrapsEACHRowInTableRow() {
        ArrayList rows = new ArrayList();
        rows.add(1);
        rows.add(2);
        rows.add(3);
        rows.add(4);
        rows.add(5);
        rows.add(6);
        rows.add(7);
        rows.add(8);
        rows.add(9);
        assertEquals("<tr>123</tr><tr>456</tr><tr>789</tr>", clojureTttPresenter.wrapEachRowInHtml(rows));
    }

    @Test
    public void testMakesTableDataFromBoardArrayList() {
        ArrayList convertedSquare = new ArrayList();
        ArrayList rawSquare = new ArrayList();
        rawSquare.add(1);

        convertedSquare.add(
                "<td id=0>" +
                        "<form action='/play_game' method='POST'>" +
                        "<input type='submit' name='square' value=1>" +
                        "</form>" +
                        "</td>");

        assertEquals(convertedSquare, clojureTttPresenter.convertSquaresToHtml(rawSquare));

        rawSquare.set(0, "o");

        convertedSquare.set(0,
                "<td id=0>" +
                        "<form action='/play_game' method='POST'>" +
                        "<input type='submit' name='square' value=o>" +
                        "</form>" +
                        "</td>");
        assertEquals(convertedSquare, clojureTttPresenter.convertSquaresToHtml(rawSquare));


    }

    @Test
    public void testDisplaysWholeBoard() throws IOException {
        ArrayList board = new ArrayList();
        board.add(1);
        board.add(2);
        board.add(3);
        board.add(4);
        board.add(5);
        board.add(6);
        board.add(7);
        board.add(8);
        board.add(9);
        String boardAsHtml = "" +
                "<html>" +
                "<head>" +
                "<meta charset=\"utf-8\" />" +
                "<title>Tic Tac Toe</title>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"tictac.css\" />"  +
                "</head><body id=\"home\"" +
                "<form action='/play_game' method='POST'>" +
                "<table id=\"tictac\">" +
                "<tr>" +
                "<td id=0>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=1>" +
                "</form>" +
                "</td>" +
                "<td id=1>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=2>" +
                "</form>" +
                "</td>" +
                "<td id=2>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=3>" +
                "</form>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td id=3>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=4>" +
                "</form>" +
                "</td>" +
                "<td id=4>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=5>" +
                "</form>" +
                "</td>" +
                "<td id=5>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=6>" +
                "</form>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td id=6>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=7>" +
                "</form>" +
                "</td>" +
                "<td id=7>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=8>" +
                "</form>" +
                "</td>" +
                "<td id=8>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=9>" +
                "</form>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</form>" +
                "<form action='/reset' method='POST'><input type='submit' name='reset' value=Reset></form>" +
                "</html>";

        assertEquals(boardAsHtml, clojureTttPresenter.displayHtmlBoard(board));
    }

    @Test
    public void testDisplaysWinnerOrTieMessage() {
        assertEquals("<p>Player O Wins</p>hello", clojureTttPresenter.gameOverMessage(page, "o"));
        assertEquals("<p>Player X Wins</p>hello", clojureTttPresenter.gameOverMessage(page, "x"));
        assertEquals("<p>Tie Game</p>hello", clojureTttPresenter.gameOverMessage(page, null));
    }
}

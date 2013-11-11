import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class TTTPresenterTest {
    @Test
    public void testWrapsHtmlTags() {
        TTTPresenter tttPresenter = new TTTPresenter();
        String page = "";

        assertEquals("<html></html>", tttPresenter.wrapInHtml(page));
    }

    @Test
    public void testWrapsFormToPlayGame() {
        TTTPresenter tttPresenter = new TTTPresenter();
        String page = "";

        assertEquals("<form action='/play_game' method='POST'></form>", tttPresenter.wrapInPlayGameForm(page));
    }

    @Test
    public void testMakesTableDataFromBoardArrayList() {
        TTTPresenter tttPresenter = new TTTPresenter();
        ArrayList board = new ArrayList();
        board.add(1);

        ArrayList presentedBoard = new ArrayList();
        presentedBoard.add(
                ("<td id=0>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=1>")
        );

        assertEquals(presentedBoard, tttPresenter.convertRowToHtml(board));

        board.add(2);
        presentedBoard = new ArrayList();
        presentedBoard.add(
                "<td id=0>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=1>");
        presentedBoard.add(
                "<td id=1>" +
                "<form action='/play_game' method='POST'>" +
                "<input type='submit' name='square' value=2>");
        assertEquals(presentedBoard, tttPresenter.convertRowToHtml(board));


    }


}

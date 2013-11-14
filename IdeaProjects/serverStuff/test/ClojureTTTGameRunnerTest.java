import org.junit.Test;

import java.io.IOException;

public class ClojureTTTGameRunnerTest {
    @Test
    public void testRunsThroughOneCycle() throws IOException {
        ClojureTTTGameRunner runner = new ClojureTTTGameRunner();
        ClojureBoardHolder holder = new ClojureBoardHolder();
        holder.resetBoard();
        String move = "9";
        String board = "x234o6789";
        MyFileWriter myFileWriter = new MyFileWriter("/Users/pszalwinski/GoogleDrive/programming/Projects/JavaServer/serverData/clojureTTTboard.txt");
        myFileWriter.write(board);

        runner.runCycle(move);

        MyFileReader myFileReader = new MyFileReader("serverData/clojureTTTboard.txt");
        String boardString = new String(myFileReader.readFileContents(), "UTF-8");

        assert("xo34o678x".equals(boardString));
    }

}

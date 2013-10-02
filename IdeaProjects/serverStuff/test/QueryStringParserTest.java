import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class QueryStringParserTest {
    @Test
    public void testConvertsHexCodesToASCIISymbols() {
        QueryStringParser queryStringParser = new QueryStringParser();

        String hexCodeForSpace = "20";

        assertEquals(" ", queryStringParser.convertHexStringToASCII(hexCodeForSpace));
    }

    @Test
    public void testSplitsStringBetweenPercents(){
      String string = "variable_1=Operators%20";
      QueryStringParser queryStringParser = new QueryStringParser();
      String[] splittedString = queryStringParser.splitStringBetweenPercents(string);

      String[] knownSplittedString = new String[2];
      knownSplittedString[0] = "variable_1=Operators";
      knownSplittedString[1] = "20";

      assertArrayEquals(knownSplittedString, splittedString);

    }

    @Test
    public void testConvertsStringArrayOfHexToStringArrayStrings() {
        QueryStringParser queryStringParser = new QueryStringParser();
        String[] hexCodes = new String[2];

        hexCodes[0] = "20";
        hexCodes[1] = "3C";

        String[] converted = new String[2];
        converted[0] = " ";
        converted[1] = "<";

        assertArrayEquals(converted, queryStringParser.convertHexArrayToStringArray(hexCodes));
    }

    @Test
    public void convertsQueryStringIntoSymbolString(){
        QueryStringParser queryStringParser = new QueryStringParser();
        String queryString = "20%3C";

        assertEquals(" <", queryStringParser.queryStringToSymbolString(queryString));

        String realQueryString = "variable_1=Operators%20%3C";

        assertEquals("variable_1 = Operators <", queryStringParser.queryStringToSymbolString(realQueryString));

    }


}

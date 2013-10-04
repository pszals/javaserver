import org.junit.Test;
import java.io.UnsupportedEncodingException;

import static junit.framework.Assert.assertEquals;


public class QueryStringParserTest {

    @Test
    public void convertsQueryStringIntoSymbolString() throws UnsupportedEncodingException {
        QueryStringParser queryStringParser = new QueryStringParser();
        String queryString = "%20&%3C";

        assertEquals(" <", queryStringParser.queryStringToSymbolString(queryString));

        String realQueryString = "variable_1=Operators%20%3C&variable2=%3C";

        assertEquals("variable_1 = Operators <variable2 = <", queryStringParser.queryStringToSymbolString(realQueryString));
    }


}

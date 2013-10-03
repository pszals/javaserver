import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QueryStringParser {

    public String queryStringToSymbolString(String queryString) throws UnsupportedEncodingException {
        URLDecoder urlDecoder = new URLDecoder();

        String spacedEquals = queryString.replaceAll("=", " = ");
        String[] splitVariableAssignments = spacedEquals.split("&");


        String string1 = urlDecoder.decode(splitVariableAssignments[0], "UTF-8");
        if (splitVariableAssignments.length > 1) {
            String string2 = urlDecoder.decode(splitVariableAssignments[1], "UTF-8");
            return string1 + string2;
        }

        String result = string1;

        return result;
    }
}

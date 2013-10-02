import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QueryStringParser {

    public String[] splitStringBetweenPercents(String string) {
        String[] splittedString = string.split("\\%");
        return splittedString;
    }

    public String convertHexStringToASCII(String hexCodeForSpace) {
        StringBuilder stringBuilder = new StringBuilder();
        int decimal = Integer.parseInt(hexCodeForSpace, 16);
        stringBuilder.append((char) decimal);
        return stringBuilder.toString();
    }

    public String[] convertHexArrayToStringArray(String[] hexCodes) {
        String[] newHexCodes = hexCodes;
        for (int i=0; i < hexCodes.length; i++){
            if (newHexCodes[i].length() == 2) {
                newHexCodes[i] = convertHexStringToASCII(newHexCodes[i]);
            } else if(newHexCodes[i].length() != 2) {
                newHexCodes[i] = newHexCodes[i].replaceAll("=", " = ");
                newHexCodes[i] = newHexCodes[i].replaceAll("&", " ");
            }
        }
        return newHexCodes;
    }

    public String queryStringToSymbolString(String queryString) throws UnsupportedEncodingException {

        URLDecoder urlDecoder = new URLDecoder();

        String result = urlDecoder.decode(queryString, "UTF-8");

        return result.replaceAll("=", " = ");
//        String[] splittedString = splitStringBetweenPercents(queryString);
//        String[] hexCodes = convertHexArrayToStringArray(splittedString);
//
//        StringBuilder builder = new StringBuilder();
//        for(String s : hexCodes) {
//            builder.append(s);
//        }
//        return builder.toString();
    }
}

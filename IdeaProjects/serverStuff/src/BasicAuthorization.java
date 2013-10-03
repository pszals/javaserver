import org.apache.commons.codec.binary.Base64;

public class BasicAuthorization {
    public boolean compareCredentials(byte[] encodedCredentials) {
        System.out.println(new String(encodedCredentials));
        String authorizedString = "admin:hunter2";

        Base64 base64 = new Base64();
        String credentials = new String(base64.decode(encodedCredentials));

        return authorizedString.equals(credentials);
    }
}

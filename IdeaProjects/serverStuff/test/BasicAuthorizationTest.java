import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BasicAuthorizationTest {

    @Test
    public void testComparesEncodedBytes(){
        BasicAuthorization basicAuthorization = new BasicAuthorization();

        Base64 base64 = new Base64();
        String authorizationCreds = "admin:hunter2";
        byte[] authorizedBytes = authorizationCreds.getBytes();

        byte[] encodedCreds = base64.encode(authorizedBytes);

        assertTrue(basicAuthorization.compareCredentials(encodedCreds));
    }
}

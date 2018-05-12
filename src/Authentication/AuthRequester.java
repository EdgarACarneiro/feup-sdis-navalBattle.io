package Authentication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthRequester {

    public void authentication(String username) throws NoSuchAlgorithmException {

        String ip = ""; // to be obtained with socket
        username = "test"; // to be obtained with input

        //se for para dar hash e não é ncessário guardar o username
        String userHashed = username + ip;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(userHashed.getBytes());

        String login = new BigInteger(1, hash).toString(16);

        //sends login string to server
    }
}

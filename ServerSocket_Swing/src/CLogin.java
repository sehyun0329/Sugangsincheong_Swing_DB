import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CLogin implements IControl {

    private static final String ACCOUNT_FILE = "account/account.txt";

    public CLogin() {

    }

    public String getUserInfo() {
        String userInfo = "userInfo";
        return userInfo;
    }

    public String login(String credentials) {
        String[] tokens = credentials.split(",");
        if (tokens.length != 2) {
            return "INVALID";
        }

        String username = tokens[0];
        String password = tokens[1];

        String userData = checkCredentials(username, password);
        if (userData != null) {
            String[] userDataTokens = userData.split(",");
            String fullName = userDataTokens[2];
            return "SUCCESS," + fullName;
        } else {
            return "FAILURE";
        }
    }

    private String checkCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 3 && tokens[0].equals(username) && tokens[1].equals(password)) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
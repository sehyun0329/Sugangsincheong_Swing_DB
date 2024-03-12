public class CLogin extends Stub implements ICLogin {

    public CLogin() {
        super("localhost", 12345);
    }

    public String getUserInfo() {
        String result = this.request("cLogin", "getUserInfo", "");
        return result;
    }

    public boolean login(String username, String password) {
        String requestMessage = username + "," + password;
        String result = this.request("cLogin", "login", requestMessage);

        return result.startsWith("Result: SUCCESS");
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CMain {
    private void run() {
        View view = new View();
        view.showLogin();
    }

    public static void main(String[] args) {
        CMain main = new CMain();
        main.run();
    }
}

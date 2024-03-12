// View.java

import java.util.Scanner;

import javax.swing.SwingUtilities;

import view.Main;

public class View {
    private ICLogin cLogin;

    public View() {
        this.cLogin = new CLogin();
    }

    public void showLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("사용자명을 입력해주세요: ");
        String username = scanner.nextLine();
        System.out.print("비밀번호를 입력해주세요: ");
        String password = scanner.nextLine();

        boolean loginResult = cLogin.login(username, password);

        if (loginResult) {
            System.out.println("로그인 성공!");
            System.out.println("---------------------------------------------------------------------");
                Main main = new Main();
                main.initialize();
        } else {
            System.out.println("로그인 실패. 사용자명과 비밀번호를 확인해주세요");
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Stub {
	private String serverIP;
    private int serverPort;
    
	public Stub(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}

	public String request(String objectName, String methodName, String args) {
        try {
            // 서버에 연결
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Stub-connected to skeleton");

            // 클라이언트와 통신을 위한 입출력 스트림 생성
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 서버로 메시지 보내기
            out.println(objectName);
            out.println(methodName);
            out.println(args);
            
            System.out.println("Stub-Message sent to skeleton: " + objectName + methodName + args);

            // 서버로부터 에코된 메시지 받기
            String userInfo = in.readLine();
            System.out.println("message received from server: " + userInfo);

            // 연결 종료
            socket.close();
            
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
}
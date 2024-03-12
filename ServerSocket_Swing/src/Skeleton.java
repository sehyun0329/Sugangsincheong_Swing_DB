import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Skeleton {
    private int port = 12345; // ���� ��Ʈ ��ȣ
    HashMap<String, Object> objectMap;

    public Skeleton() {
        this.port = 12345;
        this.objectMap = new HashMap<String, Object>();
        this.objectMap.put("cLogin", new CLogin());
    }

    public void process() {
        try {
            // ���� ���� ����
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Ŭ���̾�Ʈ�� ����� ���� ����� ��Ʈ�� ����
                Session session = new Session(clientSocket, objectMap);
                session.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Session extends Thread {
        private Socket clientSocket;
        private HashMap<String, Object> objectMap;

        public Session(Socket clientSocket, HashMap<String, Object> objectMap) {
            this.clientSocket = clientSocket;
            this.objectMap = objectMap;
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String objectName = reader.readLine();
                String methodName = reader.readLine();
                String args = reader.readLine();
                System.out.println("Server Received: " + objectName);
                System.out.println("Method: " + methodName);
                System.out.println("Arguments: " + args);

                Object object = this.objectMap.get(objectName);
                String result = (String) object.getClass().getMethod(methodName, String.class).invoke(object, args);

                // Ŭ���̾�Ʈ�� ���� �޽��� ������
                writer.println("Result: " + result);

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Server Response: " + line);
                }

                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress());

            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
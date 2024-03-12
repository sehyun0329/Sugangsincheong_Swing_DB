import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SMain {
	private Skeleton skeleton;
	private void run() {
		 this.skeleton = new Skeleton();
		 this.skeleton.process();
	    }
	
	public static void main(String[] args) {
		SMain main = new SMain();
		main.run();
	}
}

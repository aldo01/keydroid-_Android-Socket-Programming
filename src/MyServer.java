import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	Robot robot = new Robot();

	public static void main(String[] args) throws AWTException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;

		try {
			serverSocket = new ServerSocket(9080);
			System.out.println("Listening :9080");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			try {
				socket = serverSocket.accept();
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(
						socket.getOutputStream());
				System.out.println("ip: " + socket.getInetAddress());
				
				String message= dataInputStream.readUTF();
				
				System.out.println("message: " + message);

				if (message.equals("arrowup")){

					System.out.println("\nyou are in arrow up");
					new MyServer();
					System.out.println("\nyou are done arrow up");
				}

				dataOutputStream.writeUTF("Hello!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dataOutputStream != null) {
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public MyServer() throws AWTException {

		robot.delay(50);
		type(KeyEvent.VK_DOWN);

	}

	private void type(int i) {
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}

}
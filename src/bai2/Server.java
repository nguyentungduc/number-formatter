package bai2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static long giaiThua(int n) {

		long result = 1L;
		for (long i = 1L; i <= n; i++)
			result = result * i;

		return result;
	}

	public static void lietKe(int a, int b, int c, int d, DataOutputStream dos) {
		if (a == 0)
			return;
		else {
			try {
				dos.writeUTF(a + "" + b + "" + c + "." + d);
				dos.writeUTF(a + "" + b + "" + d + "." + c);
				dos.writeUTF(a + "" + c + "" + d + "." + b);
				dos.writeUTF(a + "" + c + "" + b + "." + d);
				dos.writeUTF(a + "" + d + "" + c + "." + b);
				dos.writeUTF(a + "" + d + "" + b + "." + c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = serverSocket.accept();
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			while (true) {
				String chuoia = dis.readUTF();
				String chuoib = dis.readUTF();
				String chuoic = dis.readUTF();
				String chuoid = dis.readUTF();
				try {
					int a = Integer.parseInt(chuoia);
					int b = Integer.parseInt(chuoib);
					int c = Integer.parseInt(chuoic);
					int d = Integer.parseInt(chuoid);

					long ketQua = 0;
					if (a != 0 && b != 0 && c != 0 && d != 0) {
						ketQua = giaiThua(4);
					} else {
						ketQua = giaiThua(4) - giaiThua(3);
					}

					System.out.println(a + " " + b + " " + c + " " + d);
					dos.writeUTF(Long.toString(ketQua));

					lietKe(a, b, c, d, dos);
					lietKe(b, a, c, d, dos);
					lietKe(c, b, a, d, dos);
					lietKe(d, b, c, a, dos);
				} catch (Exception e) {
					dos.writeUTF("Error");
				}

				dos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}

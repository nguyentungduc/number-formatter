package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8888);
		System.out.println("Server is running on port 8888");
		Socket socket = server.accept();
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream din = new DataInputStream(socket.getInputStream());
		boolean isServerStarted = true;
		while (isServerStarted) {
			String st = din.readUTF();
			long[] numbers = getNumbers(st);
			long count = countZero(numbers[1]) - countZero(numbers[0]);
			dos.writeUTF(count + "");
			dos.flush();
		}
		if (dos != null)
			dos.close();
		if (din != null)
			din.close();
		if (socket != null)
			socket.close();
		if (server != null)
			server.close();

	}
	
	public static long[] getNumbers(String st) {
		String[] s = st.split(",");
		long[] numbers = new long[2];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Long.parseLong(s[i]);
		}
		return numbers;
	}

	public static long countZero(long n) {
		long count = 0L;
		for (long i = 5L; n / i >= 1; i *= 5) {
			count += n / i;
		}
		return count;
	}
	
}

package bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

	@Override
	public void run() {
	}

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 8888);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
		Scanner scanner = new Scanner(System.in);

		while (true) {
			scanner = scanner.reset();
			int so1, so2, so3, so4;
			{
				System.out.print("Nhap so thu 1: ");
				so1 = Integer.parseInt(scanner.nextLine());
				System.out.print("Nhap so thu 2: ");
				so2 = Integer.parseInt(scanner.nextLine());
				System.out.print("Nhap so thu 3: ");
				so3 = Integer.parseInt(scanner.nextLine());
				System.out.print("Nhap so thu 4: ");
				so4 = Integer.parseInt(scanner.nextLine());
			}
			dout.writeUTF(so1 + "");
			dout.writeUTF(so2 + "");
			dout.writeUTF(so3 + "");
			dout.writeUTF(so4 + "");
			String msgRecieve = din.readUTF();
			System.out.println("Lap duoc " + msgRecieve + " so");
			for (int i = 0; i < Integer.parseInt(msgRecieve); i++) {
				String mgRecieve = din.readUTF();
				System.out.println(mgRecieve);
			}
			if (msgRecieve.equals("exit"))
				break;
			dout.flush();
		}
		din.close();
		dout.close();
		socket.close();
	}

}

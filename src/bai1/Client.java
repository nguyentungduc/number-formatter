package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket sock = new Socket("localhost", 8888);
		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
		DataInputStream din = new DataInputStream(sock.getInputStream());
		Scanner scan = new Scanner(System.in);
		boolean isFinished = false;
		while (!isFinished) {
			System.out.print("Nhap so thu nhat: ");
			long n1 = Long.parseLong(scan.nextLine());
			System.out.print("Nhap so thu hai: ");
			long n2 = Long.parseLong(scan.nextLine());
			scan = scan.reset();
			dos.writeUTF(n1 + "," + n2);
			dos.flush();
			
			String st = din.readUTF();
			System.out.println("So chu so 0 tan cung cua tich cac so lien tuc tu " + n1 + " den " + n2 + " la: " + st);
		}
		if (dos != null)
			dos.close();
		if (din != null)
			din.close();
		if (sock != null)
			sock.close();

	}
}

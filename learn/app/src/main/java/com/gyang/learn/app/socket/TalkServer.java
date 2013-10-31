package com.gyang.learn.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket server = null;
		try {
			server = new ServerSocket(4700);

		} catch (IOException e) {
			e.printStackTrace();
		}

		Socket socket = null;
		try {
			socket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String line;

		BufferedReader is;
		try {
			is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter os = new PrintWriter(socket.getOutputStream());

			BufferedReader sin = new BufferedReader(new InputStreamReader(
					System.in));

			System.out.println("Client:" + is.readLine());
			line = sin.readLine();

			while (!line.equals("bye")) {

				// 如果该字符串为 "bye"，则停止循环

				os.println(line);

				os.flush();

				System.out.println("Server:" + line);

				System.out.println("Client:" + is.readLine());

				line = sin.readLine();

			}

			os.close(); // 关闭Socket输出流

			is.close(); // 关闭Socket输入流

			socket.close(); // 关闭Socket

			server.close(); // 关闭ServerSocket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

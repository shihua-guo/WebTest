package com.briup.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.briup.Thread.ServerThread;
import com.briup.util.PortUtil;

public class Server {
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean flag = true;

	public Server() {
		try {
			serverSocket = new ServerSocket(PortUtil.getPort("port1"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parser() {
		try {
			while (flag) {
				socket = serverSocket.accept();
				Thread th = new Thread(new ServerThread(socket));
				th.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.parser();
	}
}

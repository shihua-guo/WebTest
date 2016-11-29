package com.briup.Thread;

import java.io.IOException;
import java.net.Socket;

import com.briup.http.HttpAccessProcessor;
import com.briup.http.HttpCreator;
import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.httpImpl.HttpCreatorImpl;

public class ServerThread implements Runnable {
	private Socket socket;

	public ServerThread() {
	}

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		HttpCreator creator = new HttpCreatorImpl(socket);
		HttpRequest req = creator.getHttpRequest();
		HttpResponse res = creator.getHttpResponse();
		HttpAccessProcessor proc = creator.getHttpAccessProcessor();
		if (req.isDynamicResource()) {
			proc.processDynamicResource(req, res);
		} else if (req.isStaticResource()) {
			proc.processStaticResource(req, res);
		} else if (req.isNullRequest()) {
			try {
				throw new Exception("浏览器发送空请求");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			proc.sendError(404, req, res);
		}
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

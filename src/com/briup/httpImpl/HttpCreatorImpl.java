package com.briup.httpImpl;

import java.net.Socket;

import com.briup.http.HttpAccessProcessor;
import com.briup.http.HttpCreator;
import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;

public class HttpCreatorImpl implements HttpCreator{
	private Socket socket;
	private HttpRequest req;
	private HttpResponse res;
	private HttpAccessProcessor proc;
	public HttpCreatorImpl() {}
	public HttpCreatorImpl(Socket socket) {
		this.socket = socket;
		this.req = new HttpRequestImpl(socket);
		this.res = new HttpResponseImpl(socket);
		this.proc = new HttpAccessProcessorImpl();
	}
	@Override
	public HttpRequest getHttpRequest() {
		return req;
	}

	@Override
	public HttpResponse getHttpResponse() {
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public HttpAccessProcessor getHttpAccessProcessor() {
		// TODO Auto-generated method stub
		return proc;
	}

}

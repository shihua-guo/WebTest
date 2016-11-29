package com.briup.httpImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.briup.http.HttpResponse;
import com.briup.util.ConfigUtil;
import com.briup.util.MIMEUtil;
import com.briup.util.StatusCodeUtil;

public class HttpResponseImpl implements HttpResponse{
	private Socket socket;
	private OutputStream out;
	private PrintWriter pw;
	private StringBuffer sb;
	public HttpResponseImpl (){}
	public HttpResponseImpl (Socket socket){
		this.socket = socket;
		try {
			this.out = socket.getOutputStream();
			this.pw  = new PrintWriter(out,true);
			sb = new StringBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public OutputStream getOutputStream() throws Exception {
		// TODO Auto-generated method stub
		return this.out;
	}

	@Override
	public PrintWriter getPrintWriter() throws Exception {
		// TODO Auto-generated method stub
		return this.pw;
	}

	@Override
	public void setStatusLine(String statusCode) {
		sb.append("HTTP/1.1 "+statusCode+" "+StatusCodeUtil.getStatus(statusCode));
		setCRLF();
	}

	@Override
	public void setStatusLine(int statusCode) {
		setStatusLine(""+statusCode);
	}

	@Override
	public void setResponseHeader(String hName, String hValue) {
		sb.append(hName+": "+hValue);
		setCRLF();
	}

	@Override
	public void setContentType(String contentType) {
		sb.append("Content-Type: "+ MIMEUtil.getContentType(contentType));
		setCRLF();
	}

	@Override
	public void setContentType(String contentType, String charsetName) {
		sb.append("Content-Type: "+ MIMEUtil.getContentType(contentType)+";charset="+charsetName);
		setCRLF();
	}

	@Override
	public void setCRLF() {
		sb.append("\r\n");
	}

	@Override
	public void printResponseHeader() {
		String resHeader = sb.toString();
		System.out.println("resHeader:"+resHeader);
		pw.write(resHeader);
		pw.write("\r\n");
		pw.flush();
	}

	@Override
	public void printResponseContent(String requestPath) {
		File file = new File(
				ConfigUtil.getConfigValue("root"), requestPath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int len = -1;
			printResponseHeader();//打印响应体
			while(-1!=(len=fis.read(b))){
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

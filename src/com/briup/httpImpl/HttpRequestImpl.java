package com.briup.httpImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.briup.http.HttpRequest;
import com.briup.util.ConfigUtil;
import com.briup.util.ServletMappingUtil;

public class HttpRequestImpl implements HttpRequest {
	private Socket socket;
	private BufferedReader br;
	private String requestMethod;
	private String protocol;
	private String requestPath;
	private Map<String, String> parameters;
	private Map<String, String> requestHeader;

	public HttpRequestImpl() {
	}

	public HttpRequestImpl(Socket socket) {
		this.socket = socket;
		parameters = new HashMap<String,String>();
		requestHeader = new HashMap<String,String>();
		init();
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return this.protocol;
	}

	@Override
	public String getRequestMethod() {
		// TODO Auto-generated method stub
		return this.requestMethod;
	}

	@Override
	public String getRequestPath() {
		// TODO Auto-generated method stub
		return this.requestPath;
	}

	@Override
	public Map<String, String> getRequestHeader() {
		// TODO Auto-generated method stub
		return this.requestHeader;
	}

	@Override
	public String getParameter(String parameterName) {
		// TODO Auto-generated method stub
		return this.parameters.get(parameterName);
	}

	@Override
	public boolean isStaticResource() {
		String child = getRequestPath();
		String parent = ConfigUtil.getConfigValue("root");
		File file = new File(parent, child);
		return file.exists() && file.isFile();
	}

	@Override
	public boolean isDynamicResource() {
		return ServletMappingUtil.isContainKey(getRequestPath());
	}

	@Override
	public boolean isNullRequest() {
		// TODO Auto-generated method stub
		return this.requestPath == null;
	}

	public void init() {
		try {
			System.out.println(socket==null);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			String line = null;
			line = br.readLine();
			String[] firstLine = line.split(" ");
			// set method
			setRequestMethod(firstLine[0]);
			// set path
			setRequestPath(firstLine[1]);
			// set protocol
			setProtocol(firstLine[2]);
			// set header
			setHeader();
			// set parameter
			setParameters();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void putParam(String allParam) throws Exception {
		String[] params = allParam.split("&");
		for (String str : params) {
			String[] param = str.split("=");
			if (param.length == 2) {
				this.parameters.put(param[0], param[1]);
			} else {
				throw new Exception("参数的值为空！");
			}
		}
	}

	public void setParameters() {
		try {
			// post
			if (br.ready()) {
				String line = "";
				char[] c = new char[512];
				int len = -1;
				while (-1 != (len = br.read(c))) {
					line += new String(c, 0, len);
				}
				putParam(line);
			}
			// get
			else {
				String[] pathParam = this.requestPath.split("\\?");
				setRequestPath(pathParam[0]);
				if (pathParam.length == 2) {// 判断是否有参数
					putParam(pathParam[1]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setHeader() {
		try {
			String line = null;
			while ((line = br.readLine()).trim().length() != 0) {
				String[] header = line.split(": ");
				this.requestHeader.put(header[0], header[1]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

}

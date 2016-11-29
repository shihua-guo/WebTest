package com.briup.httpImpl;

import com.briup.http.HttpAccessProcessor;
import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.http.Servlet;
import com.briup.util.ErrorPageUtil;
import com.briup.util.MIMEUtil;
import com.briup.util.ServletMappingUtil;

public class HttpAccessProcessorImpl implements HttpAccessProcessor {

	@Override
	public void processStaticResource(HttpRequest req, HttpResponse res) {
		System.out.println("i am static ");
		String[] pathDivi = req.getRequestPath().split("[.]");
		String fileType = pathDivi[pathDivi.length - 1];
		res.setStatusLine(200);
		res.setContentType(fileType, "UTF-8");
		System.out.println(req.getRequestPath());
		res.printResponseContent(req.getRequestPath());
	}

	@Override
	public void processDynamicResource(HttpRequest req, HttpResponse res) {
		System.out.println("i am DynamicResource ");
		String className = ServletMappingUtil.getValue(req.getRequestPath());
		try {
			Object obj = Class.forName(className).newInstance();
			if (obj instanceof Servlet) {
				((Servlet) obj).servive(req, res);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendError(int errorCode, HttpRequest req, HttpResponse res) {
		System.out.println("i am error");
		res.setStatusLine(errorCode);
		res.setContentType("html", "UTF-8");
		res.printResponseContent(ErrorPageUtil.getErrorPage(404));
	}

}

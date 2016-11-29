package com.briup.servlet;

import java.io.PrintWriter;

import com.briup.http.HttpRequest;
import com.briup.http.HttpResponse;
import com.briup.http.Servlet;

public class HelloWorldServlet implements Servlet{

	@Override
	public void servive(HttpRequest req, HttpResponse res) {
		PrintWriter pw = null;
		try {
			pw = res.getPrintWriter();
			res.setStatusLine(200);
			res.setContentType("html","UTF-8");
			res.printResponseHeader();
			pw.print("<html>");
			pw.print("<body>");
			pw.print("我是");
			pw.print("</body>");
			pw.print("</html>");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

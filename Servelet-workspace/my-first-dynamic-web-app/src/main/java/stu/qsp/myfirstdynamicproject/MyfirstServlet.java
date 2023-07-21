package stu.qsp.myfirstdynamicproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyfirstServlet  extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out =resp.getWriter();
		
		Date d=new Date();
		String currentDateAndTime=d.toString();
		
		//Send generated time as a response to browser
		String html="<!doctype HTML>"
				+"<html>"
				+"<head>"
				//+"<meta http-equiv=\"refresh\" content=\"1\">"
				+"<title>Dynamic resource</title>"
				+"</head>"
				+"<body style=\"background:#aaaafd \">"
				+"<h1>This is a <mark>Dynamic resource </mark>"
				+"which generates <i>Dynamic response  </i><h1>"
				+"<h2>"
				+"Current date and Time is "
				+currentDateAndTime
				+"</h2>"
				+"</body>"
				+"</html>";
		
		out.print(html);
		
		resp.setHeader("refresh","1");
	}
}

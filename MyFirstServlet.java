import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyFirstServlet extends HTTPServlet{

public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{

	
	PrintWriter o=res.getWriter();
	o.println("hi i am munawaer Husain ");




}




}


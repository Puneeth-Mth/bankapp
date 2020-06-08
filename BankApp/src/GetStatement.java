

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetStatement extends HttpServlet 
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		HttpSession session=request.getSession();
		String accno=(String) session.getAttribute("accno");
		System.out.println("accno="+accno);
		
		
		Model m = new Model();
		m.setAccno(accno);
		
		m.getstatement();
		
		ArrayList al1 = m.al1;
		ArrayList al2 = m.al2;
		System.out.println(al1);
		System.out.println(al2);
		
		session.setAttribute("al1", al1);
		session.setAttribute("al2", al2);
		
		response.sendRedirect("/BankApp/dispStatement.jsp");
	}
}

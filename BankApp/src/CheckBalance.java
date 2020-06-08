

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckBalance extends HttpServlet 
{	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String accno =  (String) session.getAttribute("accno");
		
		Model m = new Model();
		m.setAccno(accno);
		
		m.checkBalance();
		
		String balance = m.getBalance();
		
		session.setAttribute("bal", balance);
		
		response.sendRedirect("/BankApp/dispBalance.jsp");
	}
		
	
}

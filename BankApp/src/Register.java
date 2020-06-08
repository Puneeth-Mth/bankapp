

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String name = request.getParameter("name");
		String accno = request.getParameter("accno");
		String pwd = request.getParameter("pwd");
		String balance = request.getParameter("balance");
		String email = request.getParameter("email");
		
		Model m = new Model();
		m.setName(name);
		m.setAccno(accno);
		m.setPwd(pwd);
		m.setBalance(balance);
		m.setEmail(email);
		
		int x = m.register();
		System.out.println("model.java page="+x);
		
		if(x==1)
		{
			response.sendRedirect("/BankApp/successReg.html");
		}
		else
		{
			response.sendRedirect("/BankApp/failureReg.html");
		}
	}
		
}


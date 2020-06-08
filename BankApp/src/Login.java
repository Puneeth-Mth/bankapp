

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet 
{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String accno = request.getParameter("accno");
		String pwd = request.getParameter("pwd");
		
		Model m = new Model();
		m.setAccno(accno);
		m.setPwd(pwd);
		
		int x = m.login();
		
		if(x==0)
		{
			response.sendRedirect("/BankApp/failureLogin.html");
		}
		else
		{
			String name = m.getName();
			HttpSession session = request.getSession(true);
			session.setAttribute("name", name);
			session.setAttribute("accno", accno);
			response.sendRedirect("/BankApp/home.jsp");
		}
	}
	
}

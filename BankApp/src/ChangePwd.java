

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePwd extends HttpServlet
{


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		Model m = new Model();
		m.setAccno(accno);
		m.setOldpwd(oldpwd);
		m.setNewpwd(newpwd);
		
		int x = m.changePwd();
		
		if(x==1)
		{
			response.sendRedirect("/BankApp/successChangePwd.html");
		}
		else
		{
			response.sendRedirect("/BankApp/failChangePwd.html");
		}
	}
}
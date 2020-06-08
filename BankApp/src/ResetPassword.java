

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ResetPassword extends HttpServlet
{
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		String sotp=(String) session.getAttribute("sotp");
		
		String rotp=request.getParameter("rotp");
		String npwd=request.getParameter("npwd");
		
		if(sotp.equals(rotp))
		{
				Model m=new Model();
				m.setNpwd(npwd);
				m.setEmail(email);
			
				int x=m.resetPassword();
				
				if(x==1) 
				{
					response.sendRedirect("/BankApp/successReset.html");
				}
				else 
				{
					response.sendRedirect("/BankApp/failureReset.html");
				}
		}
		else 
		{
			response.sendRedirect("/BankApp/invalidOTP.html");
		}
	}

}



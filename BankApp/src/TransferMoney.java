

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TransferMoney extends HttpServlet 
{

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session = request.getSession();
		String accno= (String) session.getAttribute("accno");
		
		String raccno = request.getParameter("raccno");
		String amt = request.getParameter("amt");
		
		Model m = new Model();
		m.setAccno(accno);
		m.setAmt(amt);
		m.setRaccno(raccno);
		
		int x = m.transferMoney();
		
		if(x==1)
		{
			response.sendRedirect("/BankApp/successTransfer.html");
		}
		else
		{
			response.sendRedirect("/BankApp/failTransfer.html");
		}
		
		
		
	}
}



import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class TransferMoneyFilter
 */
public class TransferMoneyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransferMoneyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
	
			String raccno = request.getParameter("raccno");
			String amt = request.getParameter("amt");
			
			if(raccno.length()!=0 || amt.length()!=0)
			{
				chain.doFilter(request, response);
			}
			else
			{
				((HttpServletResponse)response).sendRedirect("/BankApp/errorTransfer.html");
			}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

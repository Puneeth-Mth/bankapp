

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ResetForgotPwdFilter
 */
public class ResetForgotPwdFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ResetForgotPwdFilter() {
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

		String npwd=request.getParameter("npwd");
		String cnewpwd=request.getParameter("cnewpwd");
		System.out.println(npwd+"  "+cnewpwd);
		
		if(npwd.length()!=0 && npwd.equals(cnewpwd)) 
		{
			chain.doFilter(request, response);
		}
		else
		{
			((HttpServletResponse) response).sendRedirect("/BankApp/errorPassword.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

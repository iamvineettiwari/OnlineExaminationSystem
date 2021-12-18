package filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CheckAdminLoginInfo
 */
@WebFilter("/CheckAdminLoginInfo")
public class CheckAdminLoginInfo implements Filter {

    /**
     * Default constructor. 
     */
    public CheckAdminLoginInfo() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
		// TODO Auto-generated method stub

		try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            if (user != "" && pass != "") {
                fc.doFilter(request, response);
            } else {
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect("AdminLogin?errMsg=Username and Password must be filled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

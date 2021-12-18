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
 * Servlet Filter implementation class ValidateUserRegistration
 */
@WebFilter("/ValidateUserRegistration")
public class ValidateUserRegistration implements Filter {

    /**
     * Default constructor. 
     */
    public ValidateUserRegistration() {
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
		String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String name = request.getParameter("name");
        String fname = request.getParameter("fname");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String qualification = request.getParameter("qualification");
        String add = request.getParameter("address");
        String city = request.getParameter("city");
        String cont = request.getParameter("contact");
        try {
            if (user != "" && pass != "" && name != "" && fname != "" && email != "" && gender != "" && dob != "" && !qualification.equals("none") && add != "" && city != "" && cont != "") {
                if (cont.length() == 10){
                    fc.doFilter(request, response);
                }  else {
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect("register?errMsg=Contact number should be only of 10 digits.");
            }
            } else {
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect("register?errMsg=All the fields are required.");
            }
        }catch (Exception e) {
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

package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bin.DatabaseConnection;

/**
 * Servlet Filter implementation class CheckForChangePasswordUser
 */
@WebFilter("/CheckForChangePasswordUser")
public class CheckForChangePasswordUser implements Filter {

    /**
     * Default constructor. 
     */
    public CheckForChangePasswordUser() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		try {
            String opass = request.getParameter("oldpass");
            String npass = request.getParameter("newpass");
            String cpass = request.getParameter("cpass");
            
            if (opass != "" && npass != "" && cpass != "") {
                HttpServletRequest req = (HttpServletRequest) request;
                HttpSession session = req.getSession();
                String username = session.getAttribute("username").toString();
                ServletRequest requests = (ServletRequest) req;
                DatabaseConnection db = new DatabaseConnection();
                db.pstmt = db.conn.prepareStatement("SELECT pswd FROM member_info WHERE user_id = ?");
                db.pstmt.setString(1, username);
                db.rst = db.pstmt.executeQuery();
                if (db.rst.next()) {
                    chain.doFilter(requests, response);
                } else {
                    HttpServletResponse res = (HttpServletResponse) response;
                    res.sendRedirect("ChangePassword?errMsg=Old password does not matched.");
                }
            } else {
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect("ChangePassword?errMsg=All the fields are required.");
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

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

import bin.DatabaseConnection;

/**
 * Servlet Filter implementation class CheckRegUserDuplicate
 */
@WebFilter("/CheckRegUserDuplicate")
public class CheckRegUserDuplicate implements Filter {

    /**
     * Default constructor. 
     */
    public CheckRegUserDuplicate() {
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
		// place your code here

		String user = request.getParameter("username");
        try {
            DatabaseConnection db = new DatabaseConnection();
            db.pstmt = db.conn.prepareStatement("SELECT * FROM member_info WHERE user_id = ?");
            db.pstmt.setString(1, user);
            db.rst = db.pstmt.executeQuery();
            if (!db.rst.next()) {
                fc.doFilter(request, response);
            } else {
                HttpServletResponse res = (HttpServletResponse) response;
                res.sendRedirect("register?errMsg=User already exists with username "+user);
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

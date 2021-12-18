package bin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DoAdminLogin
 */
@WebServlet("/DoAdminLogin")
public class DoAdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            DatabaseConnection db = new DatabaseConnection();
            db.pstmt = db.conn.prepareStatement("SELECT pswd FROM admin WHERE user = ?");
            db.pstmt.setString(1, req.getParameter("username"));
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                if (db.rst.getString(1).equals(req.getParameter("password"))) {
                    HttpSession session = req.getSession();
                    session.setAttribute("usertype", "admin");
                    session.setAttribute("username", req.getParameter("username"));
                    res.sendRedirect("welcome");
                } else {
                    res.sendRedirect("AdminLogin?errMsg=Password is incorrect.");
                }               
            } else {
                res.sendRedirect("AdminLogin?errMsg=Username is invalid.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

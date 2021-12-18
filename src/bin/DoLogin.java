package bin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin() {
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
            db.pstmt = db.conn.prepareStatement("SELECT pswd,user_status,login_status FROM member_info WHERE user_id = ?");
            db.pstmt.setString(1, req.getParameter("username"));
            db.rst = db.pstmt.executeQuery();
            if (db.rst.next()) {
                if (db.rst.getString(1).equals(req.getParameter("password"))) {
                    if (db.rst.getString(2).equals("0")){
                        if (db.rst.getString(3).equals("0")) {
                            db.pstmt = db.conn.prepareStatement("UPDATE member_info SET login_status = ? WHERE user_id = ?");
                            db.pstmt.setString(1, "1");
                            db.pstmt.setString(2, req.getParameter("username"));
                            int i = db.pstmt.executeUpdate();
                            if (i > 0) {
                                HttpSession session = req.getSession();
                                session.setAttribute("usertype", "member");
                                session.setAttribute("username", req.getParameter("username"));
                                res.sendRedirect("dashboard");
                            } else {
                                res.sendRedirect("home?errMsg=Something went wrong while logging in.");
                            }
                        } else {
                            res.sendRedirect("home?errMsg=User is already logged in. Only one loggin is allowed at a time.");
                        }
                    } else {
                        res.sendRedirect("home?errMsg=Your account has been blocked. Please contact administratorr.");
                    }
                } else {
                    res.sendRedirect("home?errMsg=Password is incorrect.");
                }               
            } else {
                res.sendRedirect("home?errMsg=Username is invalid.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

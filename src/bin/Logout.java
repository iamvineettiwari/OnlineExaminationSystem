package bin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            HttpSession session = req.getSession();
            if (session.getAttribute("usertype") != null) {
                if (session.getAttribute("usertype").equals("admin")){
                    session.removeAttribute("usertype");
                    session.removeAttribute("username");
                    session.invalidate();
                    res.sendRedirect("AdminLogin");
                } else {
                    DatabaseConnection db = new DatabaseConnection();
                    db.pstmt = db.conn.prepareStatement("UPDATE member_info SET login_status = ? WHERE user_id = ?");
                    db.pstmt.setString(1, "0");
                    db.pstmt.setString(2, session.getAttribute("username").toString());
                    int i = db.pstmt.executeUpdate();
                    if (i > 0) {
                        session.removeAttribute("usertype");
                        session.removeAttribute("username");
                        session.removeAttribute("ExamArea");
                        session.removeAttribute("ques_list_from_db");
                        session.removeAttribute("qc");
                        session.invalidate();
                        res.sendRedirect("home");
                    } else {
                        res.sendRedirect("dashboard");
                    }
                }
            } else {
                res.sendRedirect("home");
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

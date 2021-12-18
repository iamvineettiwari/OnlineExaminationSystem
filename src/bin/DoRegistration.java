package bin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoRegistration
 */
@WebServlet("/DoRegistration")
public class DoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {            
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
            
            DatabaseConnection db = new DatabaseConnection();
            db.pstmt = db.conn.prepareStatement("INSERT INTO member_info (user_id,pswd,name,fname,email,gender,dob,qualification,address,city,contact) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            db.pstmt.setString(1, user);
            db.pstmt.setString(2, pass);
            db.pstmt.setString(3, name);
            db.pstmt.setString(4, fname);
            db.pstmt.setString(5, email);
            db.pstmt.setString(6, gender);
            db.pstmt.setString(7, dob);
            db.pstmt.setString(8, qualification);
            db.pstmt.setString(9, add);
            db.pstmt.setString(10, city);
            db.pstmt.setString(11, cont);
            
            int i = db.pstmt.executeUpdate();
            if (i > 0) {
                response.sendRedirect("register?sucMsg=Registration has been successfully done.");
            } else {
                response.sendRedirect("register?errMsg=Something went wrong while registration. Please try again.");
                
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

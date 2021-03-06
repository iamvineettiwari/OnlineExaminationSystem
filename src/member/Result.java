package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bin.DatabaseConnection;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setHeader("cache-control", "no-cache, no-store, must-revalidate");
        HttpSession session = req.getSession();
        try {
            if (session.getAttribute("usertype") != null && session.getAttribute("usertype").equals("member")) {
                if (session.getAttribute("exam_area_status") != null && session.getAttribute("exam_area_status").toString().equalsIgnoreCase("active")) {
                    res.sendRedirect("ExamArea");
                } else {
                    PrintWriter out = res.getWriter();
                    DatabaseConnection db = new DatabaseConnection();

                    res.setContentType("text/html;charset=UTF-8");
                    out.println("<!DOCTYPE html>"
                            + "<html lang=\"en\">"
                            + "<head>"
                            + "<meta charset=\"UTF-8\">"
                            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                            + "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">"
                            + "<title>Dashboard</title>"
                            + "<link rel=\"stylesheet\" href=\"css/style.css\">"
                            + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">"
                            + "<link rel=\"stylesheet\" href=\"font-awesome/css/all.css\">"
                            + "</head>"
                            + "<body>"
                            + "<nav class=\"navbar sticky-top navbar-expand-lg mynav let\">"
                            + "<div class=\"container-fluid\">"
                            + "<a class=\"navbar-brand\" href=\"#\">Online Examination System</a>"
                            + "<button class=\"navbar-toggler d-lg-none\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapsibleNavId\" aria-controls=\"collapsibleNavId\""
                            + "aria-expanded=\"false\" aria-label=\"Toggle navigation\">"
                            + "<span class=\"navbar-toggler-icon\"><i class=\"fas fa-align-justify\"></i></span>"
                            + "</button>"
                            + "<div class=\"collapse navbar-collapse\" id=\"collapsibleNavId\">"
                            + "<ul class=\"navbar-nav ml-auto mt-2 mt-lg-0\">"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"dashboard\"><i class=\"fas fa-home\"></i> Home </a>"
                            + "</li>"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"Exam\"><i class=\"fas fa-book\"></i> Exam</a>"
                            + "</li>"
                            + "<li class=\"nav-item active\">"
                            + "<a class=\"nav-link\" href=\"Result\"><i class=\"fas fa-address-card\"></i> Result</a>"
                            + "</li>"
                            + "<li class=\"nav-item\">"
                            + "<a class=\"nav-link\" href=\"#\"><i class=\"fas fa-comment\"></i> Feedback</a>"
                            + "</li>	"
                            + "<li class=\"nav-item dropdown\">"
                            + "<a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"dropdownId\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"><i class=\"fas fa-user-circle\"></i> Settings</a>"
                            + "<div class=\"dropdown-menu col-md-12 mydrop\" aria-labelledby=\"dropdownId\">"
                            + "<a class=\"dropdown-item active\" href=\"Profile\"><i class=\"fas fa-user-alt\"></i> View Profile <span class=\"sr-only\">(current)</span></a>"
                            + "<a class=\"dropdown-item\" href=\"EditProfile\"><i class=\"fas fa-user-edit\"></i> Edit Profile</a>"
                            + "<a class=\"dropdown-item\" href=\"ChangePassword\"><i class=\"fas fa-cog\"></i> Change Password</a>"
                            + "<a class=\"dropdown-item\" href=\"logout\"><i class=\"fas fa-sign-out-alt\"></i> Logout</a>"
                            + "</div>"
                            + "</li>	"
                            + "</ul>"
                            + "</div>"
                            + "</div>"
                            + "</nav>"
                            + "<div class=\"container-fluid\">"
                            + "<div class=\"col-md-6 offset-md-3 user-result-main\">"
                            + "<h2 class=\"text-center\">Result Card</h2>"
                            + "<h4 class=\"text-center\">Online Examination System</h4>"
                            + "<div class=\"row\">"
                            + "<div class=\"col-md-7\">"
                            + "<div class=\"row\">"
                            + "<div class=\"col-md-5\"><h5>Name : </h5></div><div class=\"col-md-7 result-main-detail\"><p>Vineet Tiwari</p></div>"
                            + "</div>"
                            + "</div><div class=\"col-md-5\"></div>"
                            + "</div>"
                            + "</div>"
                            + "</div>"
                            + "<script src=\"js/jquery.min.js\"></script>"
                            + "<script src=\"js/bootstrap.min.js\"></script>"
                            + "</body>"
                            + "</html>");
                }
            } else {
                res.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

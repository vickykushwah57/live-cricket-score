import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Home")
public class Home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession hs = request.getSession(false);

        if (hs == null || hs.getAttribute("naam") == null) {
            response.sendRedirect("index.html");
            return;
        }

        String name = (String) hs.getAttribute("naam");

        out.println("<style>"
                + "body{"
                + "background-image:url('https://t4.ftcdn.net/jpg/05/81/30/43/240_F_581304355_O0snYxPgQbQxIi8DcZvGgTEszsiLVjG2.jpg');"
                + "background-size:cover;"
                + "background-repeat:no-repeat;"
                + "background-position:center;"
                + "font-family:Arial;}"
                
                +"h3{}"
                
                + ".btn{display:inline-block;padding:8px;"
                + "background:#216fdb;color:white;"
                + "text-decoration:none;border-radius:5px;"
                + "font-size:15px;margin:5px;}"
                
                + ".btn:hover{background:#174fa3;}"
                + "</style>");

        out.println("<h2 style='color:red;text-align:center'>Home Page</h2>");
        out.println("<h3>Welcome " + name + "</h3>");
        out.println("<a href='upcomingmatches.jsp' class='btn'>Upcoming Matches</a><br>");
        out.println("<a href='matchhistory.jsp' class='btn'>Match History</a><br>");
        out.println("<a href='Profile' class='btn'>Profile</a><br>");
        out.println("<a href='Logout' class='btn' style='background:#dc3545'>Logout</a>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

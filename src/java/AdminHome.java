import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminHome")
public class AdminHome extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession hs = request.getSession(false);

        if (hs == null) {
            response.sendRedirect("adminlogin.html");
            return;
        }

        out.println("<style>"
                + "body{"
                + "background-image:url('https://t4.ftcdn.net/jpg/05/81/30/43/240_F_581304355_O0snYxPgQbQxIi8DcZvGgTEszsiLVjG2.jpg');"
                + "background-size:cover;"
                + "background-repeat:no-repeat;"
                + "background-position:center;"
                + "font-family:Arial;}"
                
                + ".btn{display:inline-block;padding:8px;"
                + "background:#216fdb;color:white;"
                + "text-decoration:none;border-radius:5px;"
                + "font-size:15px;margin:5px;}"
                
                + ".btn:hover{background:#174fa3;}"
                + "</style>");

        out.println("<h2 style='text-align:center;color:red'>Admin Home</h2>");

        out.println("<a href='insertfuturematch.html' class='btn'>Add Future Match</a><br>");
        out.println("<a href='showfuturematches.jsp' class='btn'>Show Future Matches</a><br>");
        out.println("<a href='matchhistoryentry.html' class='btn'>Add Match History</a><br>");
        out.println("<a href='matchhistory2.jsp' class='btn'>Show Match History</a><br>");
        out.println("<a href='AdminLogout' class='btn' style='background:#dc3545'>Logout</a>");
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

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String email = request.getParameter("mail");
        String password = request.getParameter("pass");

        if (email.equalsIgnoreCase("shiva123@gmail.com") && password.equals("shiva123")) {
            HttpSession hs = request.getSession(true);

            response.sendRedirect("AdminHome");
        } else {
            out.println("<h3 style='color:red; text-align:center'>Invalid Password!</h3>");

            RequestDispatcher rd = request.getRequestDispatcher("adminlogin.html");
            rd.include(request, response);
        }

    }
}

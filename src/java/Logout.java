import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        HttpSession hs=request.getSession(false);
        hs.invalidate();
        
        out.println("<h3 style='color:red; text-align:center'>You have successfully logged out</h3>");
        
        RequestDispatcher rd=request.getRequestDispatcher("index.html");
        rd.include(request, response);
    }
}

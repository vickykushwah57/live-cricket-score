
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/Register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            String name = request.getParameter("username");
            String email = request.getParameter("mail");
            String mobile = request.getParameter("mob");
            String password = request.getParameter("pwd");
            String city = request.getParameter("city");

            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USERNAME");
            String dbPassword = System.getenv("DB_PASSWORD");

            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            PreparedStatement ps = con.prepareStatement("INSERT INTO audience(name, email, mobile, password, city) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, password);
            ps.setString(5, city);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3 style='color:red; text-align:center'>Registered Successfully</h3>");

                RequestDispatcher rd = request.getRequestDispatcher("index.html");
                rd.include(request, response);
            }

            con.close();

        } catch (Exception e) {
            out.println(e.toString());
        }
    }
}

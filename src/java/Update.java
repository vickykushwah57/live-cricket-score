
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

@WebServlet("/Update")
public class Update extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String id, name, email, mobile, city;

            id = request.getParameter("id");
            name = request.getParameter("name");
            email = request.getParameter("email");
            mobile = request.getParameter("mobile");
            city = request.getParameter("city");

            // load Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USERNAME");
            String dbPassword = System.getenv("DB_PASSWORD");

            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // create statement
            PreparedStatement ps = con.prepareStatement("UPDATE audience SET name=?, email=?, mobile=?, city=? WHERE id=?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, city);
            ps.setString(5, id);

            int i = ps.executeUpdate();

            if (i > 0) {

                out.println("<h3 style='color:red; text-align:center'>Update Success!</h3>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println(e.toString());
        }
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

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

@WebServlet("/InsertFutureMatch")
public class InsertFutureMatch extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String team1 = request.getParameter("team1");
            String team2 = request.getParameter("team2");
            String venue = request.getParameter("venue");

            Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

            PreparedStatement ps = con.prepareStatement("INSERT INTO future_matches(match_date, match_time, team1, team2, venue) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, date);
            ps.setString(2, time);
            ps.setString(3, team1);
            ps.setString(4, team2);
            ps.setString(5, venue);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h3 style='color:red; text-align:center'>Data Insert Successfully</h3>");

                RequestDispatcher rd = request.getRequestDispatcher("insertfuturematch.html");
                rd.include(request, response);
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

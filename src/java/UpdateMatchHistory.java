
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

@WebServlet("/UpdateMatchHistory")
public class UpdateMatchHistory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String id, team1, team2, win, hs, hw, hspn, hwpn, mom, md;

            id = request.getParameter("id");
            team1 = request.getParameter("team1");
            team2 = request.getParameter("team2");
            win = request.getParameter("win");
            hs = request.getParameter("hs");
            hw = request.getParameter("hw");
            hspn = request.getParameter("hspn");
            hwpn = request.getParameter("hwpn");
            mom = request.getParameter("mom");
            md = request.getParameter("md");

            // load Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USERNAME");
            String dbPassword = System.getenv("DB_PASSWORD");

            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // create statement
            PreparedStatement ps = con.prepareStatement("UPDATE match_history SET team1=?, team2=?, winner=?, high_score=?, high_wicket=?, high_score_player_name=?, high_wicket_player_name=?, man_of_the_match=?, match_date=? WHERE match_id=?");
            ps.setString(1, team1);
            ps.setString(2, team2);
            ps.setString(3, win);
            ps.setString(4, hs);
            ps.setString(5, hw);
            ps.setString(6, hspn);
            ps.setString(7, hwpn);
            ps.setString(8, mom);
            ps.setString(9, md);
            ps.setString(10, id);

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

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

@WebServlet("/MatchHistoryEntry")
public class MatchHistoryEntry extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String team1, team2, wt, hs, hw, hspn, hwpn, mom, md;
        
        try{
                team1=request.getParameter("team1");
                team2=request.getParameter("team2");
                wt=request.getParameter("wt");
                hs=request.getParameter("hs");
                hw=request.getParameter("hw");
                hspn=request.getParameter("hspn");
                hwpn=request.getParameter("hwpn");
                mom=request.getParameter("mom");
                md=request.getParameter("md");
                
                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
                
                PreparedStatement ps=con.prepareStatement("INSERT INTO match_history(team1, team2, winner, high_score, high_wicket, high_score_player_name, high_wicket_player_name, man_of_the_match, match_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, team1);
                ps.setString(2, team2);
                ps.setString(3, wt);
                ps.setString(4, hs);
                ps.setString(5, hw);
                ps.setString(6, hspn);
                ps.setString(7, hwpn);
                ps.setString(8, mom);
                ps.setString(9, md);
                
                int i=ps.executeUpdate();
                
                if(i>0){
                    out.println("<h3 style='color:red; text-align:center'>data insert successfully!</h3>");
                    
                    RequestDispatcher rd=request.getRequestDispatcher("matchhistoryentry.html");
                rd.include(request, response);
                }
            }catch(Exception e){
                out.println(e.toString());
            }
    }
}

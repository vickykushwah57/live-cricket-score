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

@WebServlet("/Reset")
public class Reset extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
            try{
                String new_password=request.getParameter("pwdnew");
                String email=request.getParameter("mail");
                
                // load Driver class
                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
                
                // create statement
                PreparedStatement ps=con.prepareStatement("UPDATE audience SET password=? WHERE email=?");
                ps.setString(1, new_password);
                ps.setString(2, email);
                
                // execute statment
                int i=ps.executeUpdate();
                
                if(i>0){
                    out.println("<h3 style='color:red; text-align:center'>Password Reset Successfully!</h3>");
                    RequestDispatcher rd = request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }
                
                // close connection
                con.close();
                
            }catch(Exception e){
                out.println(e.toString());
            }
    }
}

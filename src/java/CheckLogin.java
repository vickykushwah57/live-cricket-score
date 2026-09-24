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
import java.sql.ResultSet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        try{
            String email=request.getParameter("mail");
            String password=request.getParameter("pass");
            
            Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
                
                PreparedStatement ps = con.prepareStatement("SELECT * FROM audience WHERE email=? and BINARY password=?");
                ps.setString(1, email);
                ps.setString(2, password);
                
                ResultSet rs=ps.executeQuery();
                
                if(rs.next()){
                    String name1=rs.getString("name");
       
                    HttpSession hs=request.getSession(true);
                    hs.setAttribute("naam", name1);
                    
                    response.sendRedirect("Home");
                }else{
                    out.println("<h3 style='color:red; text-align:center'>Invalid Password!</h3>");
                    
                    RequestDispatcher rd=request.getRequestDispatcher("index.html");
                    rd.include(request, response);
                }
                
                con.close();
                
            }catch(Exception e){
                out.println(e.toString());
        }
    }
}
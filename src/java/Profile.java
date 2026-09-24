import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Profile")
public class Profile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<style>"
                + "body{"
                + "background-image: url('https://t4.ftcdn.net/jpg/05/81/30/43/240_F_581304355_O0snYxPgQbQxIi8DcZvGgTEszsiLVjG2.jpg');"
                + "background-size:cover;"
                + "background-repeat: no-repeat;"
                + "background-position: center;}"
                
                + ".btn{display:inline-block;padding:10px 18px;"
                + "background:#216fdb;color:white;"
                + "text-decoration:none;border-radius:5px;"
                + "font-size:15px;margin:6px;}"
                
                + ".btn:hover{background:#174fa3;}"
                
                +".back-btn{"
                + "float: left;"
                + "color: white;"
                + "background-color: #216fdb;"
                + "border-radius: 8px;"
                + "border: 1px solid #999;"
                + "padding: 7px;"
                + "width: 75px;"
                + "font-weight: bold;"
                + "font-size: 17px;"
                + "cursor: pointer;"
                + "text-decoration: none;"
                + "text-align: center;}"
                
                + ".back-btn:hover{"
                + "background-color: #5a6268;}"
                + "</style>");
        
        HttpSession hs = request.getSession(false);
        if(hs!=null){
           
           out.println("<button type='button' onclick=\"window.location.href = 'Home'\" class='back-btn'>Back</button>");
           RequestDispatcher rd=request.getRequestDispatcher("logout.html");
           rd.include(request, response);
        
           out.println("<h2 style='color:red; text-align:center'>Welcome Profile</h2>");
           
           String name=(String)hs.getAttribute("naam");
           out.println("<h2>Welcome "+name+"</h2>");
           out.println("<a href='edit.jsp?namee="+name+"' class='btn' >Update Profile</a>");
        }else{
            out.println("<h2>Please Login First!</h2>");
            
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request, response);
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

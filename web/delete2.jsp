<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,jakarta.servlet.RequestDispatcher"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String id = request.getParameter("idd");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

                PreparedStatement ps = con.prepareStatement("DELETE FROM future_matches WHERE match_id=?");
                ps.setString(1, id);

                int i = ps.executeUpdate();

                if (i > 0) {
                    out.print("<h3 style='color:red; text-align:center'>Delete Success!</h3>");
                    RequestDispatcher rd = request.getRequestDispatcher("showfuturematches.jsp");
                    rd.include(request, response);
                }

                con.close();

            } catch (Exception e) {
                out.println(e.toString());
            }
        %>
    </body>
</html>

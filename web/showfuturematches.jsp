<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,
        java.sql.DriverManager,
        java.sql.PreparedStatement,
        java.sql.Date,
        java.sql.Time,
        java.sql.ResultSet
        "%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>

            body{
                font-family: Arial;
                background-image: url("https://t4.ftcdn.net/jpg/05/81/30/43/240_F_581304355_O0snYxPgQbQxIi8DcZvGgTEszsiLVjG2.jpg");
                background-size: cover;        /* full screen cover */
                background-repeat: no-repeat;  /* repeat band */
                background-position: center;   /* center image */
            }
            
            td{
                text-align: center;
            }
            table tr th, td{
                padding: 5px;
            }

            table tr th{
                background-color: #ccc;
            }
             
            table tr td{
                background-color: white;
            }
            
            div a{
                float: right;
                color: white;
                background-color: #216fdb;
                border-radius: 8px;
                border: 1px solid #999;
                padding: 7px;
                width: 75px;
                font-weight: bold;
                font-size: 17px;
                cursor: pointer;
                text-decoration: none;
                text-align: center;
            }

            .back-btn{
                float: left;
                color: white;
                background-color: #216fdb;
                border-radius: 8px;
                border: 1px solid #999;
                padding: 7px;
                width: 75px;
                font-weight: bold;
                font-size: 17px;
                cursor: pointer;
                text-decoration: none;
                text-align: center;
            }

            .back-btn:hover{
                background-color: #5a6268;
            }
        </style>
    </head>
    <body>
        <%
        HttpSession httpSession=request.getSession(false);
        if(httpSession == null){
            out.println("<h3 style='color:red; text-align:center'>Please Login First</h3>");
            
            RequestDispatcher rd=request.getRequestDispatcher("adminlogin.html");
            rd.include(request, response);
            
        }else{
            out.println("<div><a href='adminlogout.html'>Logout</a></div>");
        }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

                PreparedStatement ps = con.prepareStatement("SELECT * FROM future_matches WHERE match_date >= CURDATE() ORDER BY match_date");

                ResultSet rs = ps.executeQuery();
        %>
        <table border="1" align="center" cellspacing="0">
        <button type="button" onclick="window.location.href = 'AdminHome'" class="back-btn">Back</button>
            <caption><h2 style="color: royalblue">Future Matches</h2></caption>
            <caption><h3 style="color: royalblue">🏆 ICC Men’s T20 World Cup 2026</h3></caption>
            <tr>
                <th>Id</th>
                <th>Date</th>
                <th>Time</th>
                <th>Team1</th>
                <th>Team2</th>
                <th>Venue</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <%
                while (rs.next()) {
                    String id = rs.getString("match_id");
                    Date date = rs.getDate("match_date");
                    Time time = rs.getTime("match_time");
                    String team1 = rs.getString("team1");
                    String team2 = rs.getString("team2");
                    String venue = rs.getString("venue");

            %>
            <tr>
                <td><%=id%></td>
                <td><%=date%></td>
                <td><%=time%></td>
                <td><%=team1%></td>
                <td><%=team2%></td>
                <td><%=venue%></td>
                <td><a href="edit2.jsp?idd=<%=id%>"><img src="Images\edit.png" height="50px" width="50px"></a></td>
                <td><a href="delete2.jsp?idd=<%=id%>"><img src="Images\delete.png" height="50px" width="50px"></a></td>
            </tr>
            <%
                    }
                    out.println("</table>");

                    con.close();
                } catch (Exception e) {
                    out.println(e.toString());
                }
            %>
    </body>
</html>

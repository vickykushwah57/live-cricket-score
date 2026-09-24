<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,
        java.sql.DriverManager,
        java.sql.PreparedStatement,
        java.sql.ResultSet,
        jakarta.servlet.http.HttpSession
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
                background-repeat: no-repeat;  /* repeat band */
                background-position: center;   /* center image */
                background-size: cover;        /* full screen cover */
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
            
            a{
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
            
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request, response);
            
        }else{
            out.println("<a href='logout.html'>Logout</a>");
        }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

                PreparedStatement ps = con.prepareStatement("SELECT * FROM match_history ORDER BY match_date DESC");

                ResultSet rs = ps.executeQuery();
        %>
        <button type="button" onclick="window.location.href = 'Home'" class="back-btn">Back</button>
        <table border="1" align="center" cellspacing="0">
            <caption><h2 style="color: royalblue">Match History</h2></caption>
            <tr>
                <th>Id</th>
                <th>Date</th>
                <th>Team1</th>
                <th>Team2</th>
                <th>Winning Team</th>
                <th>Highest Score</th>
                <th>Highest Wicket</th>
                <th>Highest Score Player</th>
                <th>Highest Wicket Player</th>
                <th>Man of The Match</th>
            </tr>
            <%
                while (rs.next()) {
                    String id = rs.getString("match_id");
                    String team1 = rs.getString("team1");
                    String team2 = rs.getString("team2");
                    String win = rs.getString("winner");
                    String hs = rs.getString("high_score");
                    String hw = rs.getString("high_wicket");
                    String hspn = rs.getString("high_score_player_name");
                    String hwpn = rs.getString("high_wicket_player_name");
                    String mom = rs.getString("man_of_the_match");
                    String md = rs.getString("match_date");

            %>
            <tr>
                <td><%=id%></td>
                <td><%=md%></td>
                <td><%=team1%></td>
                <td><%=team2%></td>
                <td><%=win%></td>
                <td><%=hs%></td>
                <td><%=hw%></td>
                <td><%=hspn%></td>
                <td><%=hwpn%></td>
                <td><%=mom%></td>
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

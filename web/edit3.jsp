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
                background-color: #f2f2f2;
            }

            .container{
                border: 1px solid #999;
                border-radius: 8px;
                padding: 25px;
                margin: 5% auto;
                width: 320px;
                background-color: white;
            }

            .container h3{
                text-align: center;
                color: #333;
            }

            label{
                display: block;
                font-weight: bold;
                color: #444;
                margin-bottom: 5px;
            }

            input{
                width: 100%;
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            input:focus{
                border-color: #216fdb;
                outline: none;
            }

            .btn{
                width: 80px;
                border-radius: 8px;
                background-color: #216fdb;
                color: white;
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
        <%!
            String id, team1, team2, win, hs, hw, hspn, hwpn, mom;
            Date md;
        %>
        <%
            try {
                id = request.getParameter("idd");

                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

                PreparedStatement ps = con.prepareStatement("SELECT * FROM match_history WHERE match_id=?");
                ps.setString(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    id = rs.getString("match_id");
                    team1 = rs.getString("team1");
                    team2 = rs.getString("team2");
                    win = rs.getString("winner");
                    hs = rs.getString("high_score");
                    hw = rs.getString("high_wicket");
                    hspn = rs.getString("high_score_player_name");
                    hwpn = rs.getString("high_wicket_player_name");
                    mom = rs.getString("man_of_the_match");
                    md = rs.getDate("match_date");
                }
                rs.close();;
                ps.close();
                con.close();
            } catch (Exception e) {
                out.println(e.toString());
            }
        %>

        <button type="button" onclick="window.location.href = 'matchhistory2.jsp'" class="back-btn">Back</button>
        <div class="container">
            <h3>Update Match History</h3>
            <form action="UpdateMatchHistory" method="post">
                <label>Id</label>
                <input type="text" name="id" value="<%=id%>" readonly="true" /><br>
                <label>Team 1</label>
                <input type="text" name="team1" value="<%=team1%>" required>

                <label>Team 2</label>
                <input type="text" name="team2" value="<%=team2%>" required>

                <label>Winning Team</label>
                <input type="text" name="win" value="<%=win%>" required>

                <label>Highest Score</label>
                <input type="text" name="hs" value="<%=hs%>">

                <label>Highest Wicket</label>
                <input type="text" name="hw" value="<%=hw%>">

                <label>Highest Score Player Name</label>
                <input type="text" name="hspn" value="<%=hspn%>">

                <label>Highest Wicket Player Name</label>
                <input type="text" name="hwpn" value="<%=hwpn%>">

                <label>Man Of The Match</label>
                <input type="text" name="mom" value="<%=mom%>">

                <label>Match Date</label>
                <input type="date" name="md" value="<%=md%>" required>
                <center>
                    <input class="btn" type="submit" value="Update" />
                </center>
            </form>
        </div>
    </body>
</html>

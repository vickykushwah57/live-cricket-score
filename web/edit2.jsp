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
            String id, team1, team2, venue;
            Date date;
            Time time;
        %>
        <%
            try {
                id = request.getParameter("idd");

                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

                PreparedStatement ps = con.prepareStatement("SELECT * FROM future_matches WHERE match_id=?");
                ps.setString(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    id = rs.getString("match_id");
                    date = rs.getDate("match_date");
                    time = rs.getTime("match_time");
                    team1 = rs.getString("team1");
                    team2 = rs.getString("team2");
                    venue = rs.getString("venue");
                }

                con.close();
            } catch (Exception e) {
                out.println(e.toString());
            }
        %>

        <button type="button" onclick="window.location.href = 'showfuturematches.jsp'" class="back-btn">Back</button>
        <div class="container">
        <h3>Update Your Details</h3>
            <form action="UpdateMatch">
                <label>Id</label>
                <input type="text" name="id" value="<%=id%>" readonly="true" /><br>
                <label>Date</label>
                <input type="date" name="date" value="<%=date%>" /><br>
                <label>Time</label>
                <input type="time" name="time" value="<%=time%>" /><br>
                <label>Team1</label>
                <input type="text" name="team1" value="<%=team1%>" /><br>
                <label>Team2</label>
                <input type="text" name="team2" value="<%=team2%>" /><br>
                <label>Venue</label>
                <input type="text" name="venue" value="<%=venue%>" /><br>
                <center>
                    <input class="btn" type="submit" value="Update" />
                </center>
            </form>
        </div>
    </body>
</html>

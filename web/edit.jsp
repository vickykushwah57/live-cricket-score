<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection,
        java.sql.DriverManager,
        java.sql.PreparedStatement,
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
                border: 1px solid black;
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
            String id, name, email, mobile, city;
        %>
        <%
            try {
                name = request.getParameter("namee");

                Class.forName("com.mysql.cj.jdbc.Driver");

                String dbUrl = System.getenv("DB_URL");
                String dbUser = System.getenv("DB_USERNAME");
                String dbPassword = System.getenv("DB_PASSWORD");

                Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword);

                PreparedStatement ps = con.prepareStatement("SELECT * FROM audience WHERE name=?");
                ps.setString(1, name);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    id = rs.getString("id");
                    name = rs.getString("name");
                    email = rs.getString("email");
                    mobile = rs.getString("mobile");
                    city = rs.getString("city");
                }

                con.close();
            } catch (Exception e) {
                out.println(e.toString());
            }
        %>

         <button type="button" onclick="window.location.href = 'Profile'" class="back-btn">Back</button>
        <div class="container">
        <h3>Update Your Details</h3>
            <form action="Update">
                <label>Id</label>
                <input type="text" name="id" value="<%=id%>" readonly /><br>
                <label>Name</label>
                <input type="text" name="name" value="<%=name%>" /><br>
                <label>Email</label>
                <input type="email" name="email" value="<%=email%>" /><br>
                <label>Mobile</label>
                <input type="text" name="mobile" value="<%=mobile%>" /><br>
                <label>City</label>
                <input type="text" name="city" value="<%=city%>" /><br>
                <center>
                    <input class="btn" type="submit" value="Update" />
                </center>
            </form>
        </div>
    </body>
</html>

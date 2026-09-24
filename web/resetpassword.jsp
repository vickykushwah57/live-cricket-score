<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            *{
                box-sizing: border-box;
            }

            body{
                font-family: Arial;
                background-color: #ccc;
            }

            .container{
                border: 1px solid #999;
                width: 320px;
                padding: 25px;
                margin: 8% auto;
                border-radius: 8px;
                background-color: white;
            }

            .container h2{
                text-align: center;
                margin-bottom: 20px;
                color: #1877F2;
            }

            .one{
                border-radius: 5px;
                border: 1px solid #999;
                padding: 13px;
                margin-bottom: 10px;
                width: 100%;
                font-size: 17px;
            }

            .one:focus {
                border-color: blue;
                outline: none;
            }

            .two{
                border-radius: 8px;
                border: 1px solid #999;
                padding: 7px;
                width: 90px;
                font-weight: bold;
                font-size: 17px;
                cursor: pointer;
            }
            
            .passwordmsg{
                text-align: center;
                font-size: 10px;
            }
        </style>
    </head>
    <body>

        <%
            String email = request.getParameter("maill");
        %>

        <div class="container">
        <h2>Reset Password</h2>
            <form action='Reset' method='post' onsubmit='return valid()'>
                <input class="one" type='email' name='mail' value="<%=email%>" readonly ><br>
                <input class="one" type='password' id='pwd1' name='pwdnew' placeholder='New Password' /><br>
                <input class="one" type='password' id='pwd2' placeholder='Confirm Password' /><span style='color:red' id='msg'></span><br>
                <div style="text-align: center">
                    <input class="two" type="submit" value="Reset" style="background-color: #216fdb; color: white" />
                </div>
            </form>
        </div>

        <script>
            
            function valid() {
                let p1 = document.getElementById('pwd1').value;
                let p2 = document.getElementById('pwd2').value;
                let msg = document.getElementById("msg");
                
                if (p1 === "" || p2 === "") {
                    msg.innerHTML = "<div class='passwordmsg'>Password cannot be empty<br>Please try again!</div>";
                    return false;
                }
                if (p1 === p2) {
                    return true;
                } else {
                    msg.innerHTML = "invalid password";
                    return false;
                }
            }
        </script>
    </body>
</html>

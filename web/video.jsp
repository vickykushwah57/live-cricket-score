<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Uploaded Video</title>
        <style>
            .container{
                text-align: center;
            }
            
            div video {
                max-width: 100%;
                border: 2px solid #ccc;
                border-radius: 8px;
            }
            
            h3 {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h3>Advertisement</h3>
            <video width="300" controls autoplay muted preload="metadata">
                <source src="UploadedVideos/add.mp4" type="video/mp4">
            </video>
        </div>
    </body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color:  #eaaadc;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background: #eaaadc;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .details {
            font-size: 18px;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, ${studentName}!</h1>
        <div class="details">
            <p><strong>Age:</strong> ${studentAge}</p>
            <p><strong>Course:</strong> ${studentCourse}</p>
        </div>
       
    </div>

</body>
</html>

package com.test.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String jdbcURL = "jdbc:mysql://localhost:3306/Login";
        String dbUser = "sqluser";
        String dbPassword = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Validate user credentials
            String sql = "SELECT * FROM credentials WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            System.out.println("Loginservlet.doPost()");

            if (resultSet.next()) {
 System.out.print("login");
            	// Fetch student details for the logged-in user
                String studentSql = "SELECT * FROM students WHERE username = ?";
                PreparedStatement studentStmt = connection.prepareStatement(studentSql);
                studentStmt.setString(1, username);
                ResultSet studentResult = studentStmt.executeQuery();

                if (studentResult.next()) {
                	System.out.println("i am in student ");
                    // Pass student details to the welcome page
                    request.setAttribute("studentName", studentResult.getString("name"));
                    request.setAttribute("studentAge", studentResult.getInt("age"));
                    request.setAttribute("studentCourse", studentResult.getString("course"));
                    request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
                }
                else {
                	response.setContentType("text/html");
                	PrintWriter error = response.getWriter();
                	error.println("<html> <body>NO DETAILS  students</body></html>");
                }
                studentResult.close();
                studentStmt.close();
            } else {
            	 System.out.print("loginpage");
                //request.setAttribute("errorMessage", "Invalid username or password!");
                //request.getRequestDispatcher("Login.jsp").forward(request, response);
            	 response.setContentType("text/html");
            	 PrintWriter out = response.getWriter();
            	 out.println("Invalid username or password");
            	 request.getRequestDispatcher("index.jsp").include(request, response);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

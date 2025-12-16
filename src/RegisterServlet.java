import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try (Connection c = DBConnection.getConnection()) {

            // 🔍 Step 1: Check if the connection is null
            if (c == null) {
                out.println("<h3 style='color:red;'>Database connection failed — check JDBC driver or credentials.</h3>");
                return;
            }

            // 🔹 Step 2: Prepare and execute insert statement
            String sql = "INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    out.println("<h3 style='color:green;'>Registration successful!</h3>");
                    resp.sendRedirect("login.html?registered=1");
                } else {
                    out.println("<h3 style='color:red;'>Registration failed. Please try again.</h3>");
                }
            }

        } catch (SQLException e) {
            // 🔍 Step 3: Print SQL error details
            e.printStackTrace();
            out.println("<h3 style='color:red;'>SQL Error: " + e.getMessage() + "</h3>");
        } catch (Exception e) {
            // 🔍 Step 4: Catch any other error
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Unexpected Error: " + e.getMessage() + "</h3>");
        }
    }
}

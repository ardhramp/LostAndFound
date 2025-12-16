import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT USER_ID, NAME FROM USERS WHERE EMAIL = ? AND PASSWORD = ?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession s = req.getSession();
                s.setAttribute("userId", rs.getLong("USER_ID"));
                s.setAttribute("userName", rs.getString("NAME"));
                resp.sendRedirect("dashboard.jsp");
            } else {
                resp.sendRedirect("login.html?error=1");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}


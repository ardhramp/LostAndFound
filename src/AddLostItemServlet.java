import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDate;
import java.sql.Date;

public class AddLostItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemName = req.getParameter("itemName");
        String description = req.getParameter("description");
        String location = req.getParameter("location");
        String dateStr = req.getParameter("dateFound");
        HttpSession session = req.getSession(false);
        Long userId = (session != null && session.getAttribute("userId")!=null) ? (Long)session.getAttribute("userId") : null;

        Date d = null;
        if (dateStr != null && !dateStr.isEmpty()) {
            d = Date.valueOf(dateStr); // expects yyyy-MM-dd
        }

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                "INSERT INTO ITEMS (ITEM_NAME, DESCRIPTION, LOCATION_FOUND, DATE_FOUND, STATUS, USER_ID) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, itemName);
            ps.setString(2, description);
            ps.setString(3, location);
            if (d != null) ps.setDate(4, d); else ps.setNull(4, java.sql.Types.DATE);
            ps.setString(5, "Unclaimed");
            if (userId != null) ps.setLong(6, userId); else ps.setNull(6, java.sql.Types.NUMERIC);
            ps.executeUpdate();
            resp.sendRedirect("dashboard.jsp?added=1");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

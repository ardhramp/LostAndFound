import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ClaimServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdStr = req.getParameter("itemId");
        String claimant = req.getParameter("claimant");
        long itemId = Long.parseLong(itemIdStr);

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO CLAIMS (ITEM_ID, CLAIMANT_NAME, STATUS) VALUES (?, ?, ?)")) {
            ps.setLong(1, itemId);
            ps.setString(2, claimant);
            ps.setString(3, "Pending");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // update item status to Claimed (optional until admin approves)
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps2 = c.prepareStatement("UPDATE ITEMS SET STATUS = 'Claimed' WHERE ITEM_ID = ?")) {
            ps2.setLong(1, itemId);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        resp.sendRedirect("viewItems");
    }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class ViewItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String,Object>> items = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT ITEM_ID, ITEM_NAME, DESCRIPTION, LOCATION_FOUND, DATE_FOUND, STATUS FROM ITEMS ORDER BY DATE_FOUND DESC")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String,Object> m = new HashMap<>();
                m.put("itemId", rs.getLong("ITEM_ID"));
                m.put("itemName", rs.getString("ITEM_NAME"));
                m.put("description", rs.getString("DESCRIPTION"));
                m.put("location", rs.getString("LOCATION_FOUND"));
                m.put("dateFound", rs.getDate("DATE_FOUND"));
                m.put("status", rs.getString("STATUS"));
                items.add(m);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.setAttribute("items", items);
        RequestDispatcher rd = req.getRequestDispatcher("viewItems.jsp");
        rd.forward(req, resp);
    }
}


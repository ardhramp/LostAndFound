<%@ page import="javax.servlet.http.*,java.util.*" %>
<%
  HttpSession s = request.getSession(false);
  if (s==null || s.getAttribute("userId")==null) {
    response.sendRedirect("login.html");
    return;
  }
  String name = (String) s.getAttribute("userName");
%>
<!DOCTYPE html>
<html><head><title>Dashboard</title></head>
<body>
  <h2>Welcome, <%= name %></h2>
  <a href="addLost.html">Report Found Item</a> |
  <a href="viewItems">View All Items</a> |
  <a href="LogoutServlet">Logout</a>
</body></html>

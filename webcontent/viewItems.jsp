<%@ page import="java.util.*,java.text.*" %>
<%
  List items = (List) request.getAttribute("items");
  if (items == null) items = new ArrayList();
%>
<!DOCTYPE html>
<html><head><title>Items</title></head>
<body>
  <h2>Items</h2>
  <a href="index.html">Home</a><br/><br/>
  <table border="1">
    <tr><th>ID</th><th>Item</th><th>Location</th><th>Date</th><th>Status</th><th>Action</th></tr>
    <%
      for (Object o : items) {
        Map m = (Map)o;
    %>
      <tr>
        <td><%= m.get("itemId") %></td>
        <td><%= m.get("itemName") %></td>
        <td><%= m.get("location") %></td>
        <td><%= m.get("dateFound") %></td>
        <td><%= m.get("status") %></td>
        <td>
          <form method="post" action="ClaimServlet" style="display:inline">
            <input type="hidden" name="itemId" value="<%= m.get("itemId") %>"/>
            Claimant name: <input name="claimant" required/>
            <button type="submit">Claim</button>
          </form>
        </td>
      </tr>
    <% } %>
  </table>
</body></html>

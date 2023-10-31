<%@page import="java.sql.*"%>
<%
try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
    out.print(e);
}
%>
<body bgcolor="lightpink";">
<h2 align="center"><font><strong><u>Registered Candidates!! </u></strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1" bgcolor="lightblue">

<tr bgcolor="aqua">
<td><b>Name</b></td>
<td><b>Id</b></td>
<td><b>Branch</b></td>
<td><b>Mobile</b></td>
</tr>
<%
try { 
    Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ayesha4");
    Statement statement = connection.createStatement();
    String sql = "SELECT * FROM registration";

    ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
%>
<tr bgcolor="lightblue">
    <td><%=resultSet.getInt("id") %></td>
    <td><%=resultSet.getString("username") %></td>
    <td><%=resultSet.getString("email") %></td>
    <td><%=resultSet.getString("password") %></td>
</tr>
<% 
    }
} catch (Exception e) {
    out.print(e);
}
%>
</table>
</body>

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class LGservlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Replace "your-database-connection-details" with your Oracle database connection details
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbUsername = "system";
    String dbPassword = "ayesha4";

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
      String query = "SELECT * FROM login WHERE username = ? AND password = ?";
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, username);
      pstmt.setString(2, password);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        // User exists in the login table, login successful
        rs.close();
        pstmt.close();
        conn.close();
        response.sendRedirect("welcome.jsp"); // Redirect to welcome page after successful login
      } else {
        // User not found in the login table, login failed
        rs.close();
        pstmt.close();
        conn.close();
        response.sendRedirect("registration.html?error=1"); // Redirect back to login page with an error message
      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      // Handle the login error here and show an appropriate message to the user
    }
  }
}

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    int userId = Integer.parseInt(request.getParameter("userid"));
    String password = request.getParameter("password");

    if (validateUser(userId, password)) {
      // Successful login
      request.getSession().setAttribute("userid", userId);
      response.sendRedirect("dis.jsp"); // Replace "welcome.jsp" with the desired page
    } else {
      // Failed login
      response.sendRedirect("login.html?error=1");
    }
  }

  private boolean validateUser(int userId, String password) {
    // Replace "your-database-connection-details" with your Oracle database connection details
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbUsername = "system";
    String dbPassword = "ayesha4";

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
      String query = "SELECT * FROM users WHERE userid = ? AND password = ?";
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, userId);
      pstmt.setString(2, password);
      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        // User exists in the database
        rs.close();
        pstmt.close();
        conn.close();
        return true;
      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    return false;
  }
}

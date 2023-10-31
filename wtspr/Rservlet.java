import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Replace "your-database-connection-details" with your Oracle database connection details
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String dbUsername = "system";
    String dbPassword = "ayesha4";

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
      
      // Store registration data in registration table
      String registrationQuery = "INSERT INTO registration (id, username, email, password) VALUES (registration_seq.nextval, ?, ?, ?)";
      PreparedStatement registrationStmt = conn.prepareStatement(registrationQuery);
      registrationStmt.setString(1, username);
      registrationStmt.setString(2, email);
      registrationStmt.setString(3, password);
      registrationStmt.executeUpdate();
      registrationStmt.close();

      // Store login data in login table
      String loginQuery = "INSERT INTO login (id, username, password) VALUES (login_seq.nextval, ?, ?)";
      PreparedStatement loginStmt = conn.prepareStatement(loginQuery);
      loginStmt.setString(1, username);
      loginStmt.setString(2, password);
      loginStmt.executeUpdate();
      loginStmt.close();
      
      conn.close();

      response.sendRedirect("login.html"); // Redirect to login page after successful registration
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      // Handle the registration error here and show an appropriate message to the user
    }
  }
}

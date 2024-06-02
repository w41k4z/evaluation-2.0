package proj.eval.app.service;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.stereotype.Service;
import proj.eval.app.util.ManualConnection;

@Service
public class AdminService {

  private ManualConnection manualConnection;

  public AdminService(ManualConnection manualConnection) {
    this.manualConnection = manualConnection;
  }

  public void reinitializeDatabase() throws SQLException {
    Connection connection = manualConnection.getConnection();
    connection.createStatement().execute("CALL reinitialize()");
    connection.close();
  }
}

package Modelo;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBaseDeDatos {
    //private final String AquaSoftdb = "AquaSoftDB";
    private final String user = "YahelDaOng";
    private final String password = "0312";
    private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    
    private Connection conexion = null;
 
    public Connection getConnectionDB () {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conexion = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    return conexion;   
    }
}

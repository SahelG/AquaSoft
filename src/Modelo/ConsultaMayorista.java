package Modelo;

import Controlador.Mayorista;
import Controlador.TelefonoMayorista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultaMayorista extends ConexionBaseDeDatos {
    
    public boolean RegistroMayorista (Mayorista mayorista) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Mayorista (idMayorista, Nombre, ApellidoPaterno, ApellidoMaterno, Calle, Colonia, Precio) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, mayorista.getIdMayorista());
            ps.setString(2, mayorista.getNombre());
            ps.setString(3, mayorista.getApellidoPaterno());
            ps.setString(4, mayorista.getApellidoMaterno());
            ps.setString(5, mayorista.getCalle());
            ps.setString(6, mayorista.getColonia());
            ps.setDouble(7, mayorista.getPrecio());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Mayorista.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RegistrarTelefono (TelefonoMayorista telefono) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO TelefonoMayorista (idMayorista, Telefono) VALUES (?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, telefono.getIdMayorista());
            ps.setString(2, telefono.getTelefono());
           
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Mayorista.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Verifica si el cliente ya se encuentra registrado
    public int ExistenciaDeMayorista (String mayorista) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConnectionDB(); 
        
        //Solo valida que el usuario no sea el mismo.
        String sql = "SELECT count(idMayorista) FROM Mayorista WHERE idMayorista = ? ";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, mayorista);
            
            rs = ps.executeQuery();
            
            if(rs.next())
                return rs.getInt(1);
            
            return 1;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaMayorista.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } 
    }
    
    //Modifica los datos del mayorista en caso de posibles errores
    public boolean ModificarMayorista(Mayorista mayorista) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionBaseDeDatos con = new ConexionBaseDeDatos();
        int res = 0;
        
        String sql = "UPDATE Mayorista "
                + "SET Nombre = ?, ApellidoPaterno = ?, ApellidoMaterno= ?, Calle = ?, Colonia = ?, Precio = ? "
                + "WHERE idMayorista = ?";
        
        try {
            ps = con.getConnectionDB().prepareStatement(sql);
            
            ps.setString(1, mayorista.getNombre());
            ps.setString(2, mayorista.getApellidoPaterno());
            ps.setString(3, mayorista.getApellidoMaterno());
            ps.setString(4, mayorista.getCalle());
            ps.setString(5, mayorista.getColonia());
            ps.setFloat(6, mayorista.getPrecio());
            ps.setString(7, mayorista.getIdMayorista());
            
            res = ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public boolean BuscarMayorista(Mayorista mayorista, TelefonoMayorista telefono) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT  Mayorista.idMayorista, Nombre, ApellidoPaterno, ApellidoMaterno, Calle, Colonia, Precio, Telefono  FROM Mayorista, TelefonoMayorista WHERE Mayorista.idMayorista = TelefonoMayorista.idMayorista and Mayorista.idMayorista = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, mayorista.getIdMayorista());
            rs = ps.executeQuery();
            
            if(rs.next()){
                mayorista.setIdMayorista(rs.getString("idMayorista"));
                mayorista.setNombre(rs.getString("Nombre"));
                mayorista.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                mayorista.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                mayorista.setCalle(rs.getString("Calle"));
                mayorista.setColonia(rs.getString("Colonia"));
                mayorista.setPrecio(rs.getFloat("Precio"));
                
                telefono.setTelefono(rs.getString("Telefono"));
                return true;
            }
            return false;
            
        }catch(SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            try{
                con.close();
            } catch (SQLException e){
                System.err.println(e);
            }
        }
    
    }
    
    public boolean BorrarMayorista (String id) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "DELETE FROM Mayorista WHERE idMayorista = ?";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Mayorista.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List MostrarMayoristas () {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        ConexionBaseDeDatos conectar = new ConexionBaseDeDatos();
        //Productos p = new Productos();
    
        List<Mayorista> datos = new ArrayList<>();
        try {
            con = conectar.getConnectionDB();
            ps = con.prepareStatement("select * from mayorista");
            rs = ps.executeQuery();
            while (rs.next()) {
                Mayorista p = new Mayorista();
                p.setIdMayorista(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setApellidoPaterno(rs.getString(3));
                p.setApellidoMaterno(rs.getString(4));
                p.setPrecio(rs.getFloat(7));
                
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    
    
}

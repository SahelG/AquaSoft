package Modelo;

import Controlador.Insumo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultaInsumo extends ConexionBaseDeDatos {
    
    public boolean RegistroInsumo (Insumo insumo) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        //String sql = "INSERT INTO Insumos (idInsumo, nombre, cantidad, descripcion, precio) VALUES (?, ?, ?, ?, ?)";
        String sql = "INSERT INTO Insumos (nombre, cantidad, descripcion, precio) VALUES (?, ?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            //ps.setString(1, insumo.getIdInsumo());
            ps.setString(1, insumo.getNombre());
            ps.setInt(2, insumo.getCantidad());
            ps.setString(3, insumo.getDescripcion());
            ps.setFloat(4, insumo.getPrecio());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Insumo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean ModificarInsumo (Insumo insumo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionBaseDeDatos con = new ConexionBaseDeDatos();
        int res = 0;
        
        String sql = "UPDATE Insumos "
                + "SET Nombre = ?, Cantidad = ?, Descripcion = ?, Precio = ? "
                + "WHERE idInsumo = ?";
        
        try {
            ps = con.getConnectionDB().prepareStatement(sql);
            
            ps.setString(1, insumo.getNombre());
            ps.setInt(2, insumo.getCantidad());
            ps.setString(3, insumo.getDescripcion());
            ps.setFloat(4, insumo.getPrecio());
            ps.setString(5, insumo.getIdInsumo());
            
            res = ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public boolean BuscarInsumo (Insumo insumo) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT idInsumo, Nombre, Cantidad, Descripcion, Precio FROM Insumos WHERE idInsumo = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, insumo.getIdInsumo());
            rs = ps.executeQuery();
            
            if(rs.next()){
                insumo.setIdInsumo(rs.getString("idInsumo"));
                insumo.setNombre(rs.getString("Nombre"));
                insumo.setCantidad(rs.getInt("Cantidad"));
                insumo.setDescripcion(rs.getString("Descripcion"));
                insumo.setPrecio(rs.getFloat("Precio"));
                
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
    
    public boolean BorrarInsumo (String id) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "DELETE FROM Insumos WHERE idInsumo = ?";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Insumo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List MostrarInsumo () {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        ConexionBaseDeDatos conectar = new ConexionBaseDeDatos();
        //Productos p = new Productos();
    
        List<Insumo> datos = new ArrayList<>();
        try {
            con = conectar.getConnectionDB();
            ps = con.prepareStatement("select * from Insumos");
            rs = ps.executeQuery();
            while (rs.next()) {
                Insumo p = new Insumo();
                p.setIdInsumo(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setCantidad(rs.getInt(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getFloat(5));
                
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    
    
}

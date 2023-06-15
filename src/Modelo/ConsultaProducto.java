package Modelo;

import Controlador.Producto;
import Controlador.ProductoExterno;
import Controlador.ProductoInterno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultaProducto extends ConexionBaseDeDatos {
    
    public boolean RegistroProducto (Producto producto) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Producto (idProducto, nombre, descripcion, precio) VALUES (?, ?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setFloat(4, producto.getPrecio());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RegistroProductoExterno (ProductoExterno externo) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Externos (idProducto) VALUES (?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, externo.getIdProducto());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoExterno.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RegistroProductoInterno (ProductoInterno interno) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Internos (idProducto) VALUES (?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, interno.getIdProducto());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductoExterno.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean ModificarProducto (Producto producto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionBaseDeDatos con = new ConexionBaseDeDatos();
        int res = 0;
        
        String sql = "UPDATE Producto "
                + "SET Nombre = ?, Descripcion = ?, Precio = ? "
                + "WHERE idProducto = ?";
        
        try {
            ps = con.getConnectionDB().prepareStatement(sql);
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.setString(4, producto.getIdProducto());
            
            res = ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public boolean BuscarProducto (Producto producto) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT idProducto, Nombre, Descripcion, Precio FROM Producto WHERE idProducto = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, producto.getIdProducto());
            rs = ps.executeQuery();
            
            if(rs.next()){
                producto.setIdProducto(rs.getString("idProducto"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getFloat("Precio"));
                
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
    
    public boolean BorrarProducto (String id) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "DELETE FROM Producto WHERE idProducto = ?";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List MostrarProductos () {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        ConexionBaseDeDatos conectar = new ConexionBaseDeDatos();
        //Productos p = new Productos();
    
        List<Producto> datos = new ArrayList<>();
        try {
            con = conectar.getConnectionDB();
            ps = con.prepareStatement("select * from producto");
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setPrecio(rs.getFloat(4));
                
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
}

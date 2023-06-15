package Modelo;


import Controlador.Empleado;
import Controlador.Mayorista;
import Controlador.Producto;
import Controlador.TelefonoEmpleado;
import Controlador.Venta;
import Controlador.VentaProducto;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultaVenta extends ConexionBaseDeDatos {
    public boolean RegistroVenta(Venta venta){
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        String sql = "INSERT INTO Venta (idVenta , idMayorista, idEmpleado, Total, FechaDeVenta) VALUES (?, ?, ?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            
            ps.setString(1, venta.getIdVenta());
            ps.setString(2, venta.getIdMayorista());
            ps.setString(3, venta.getIdEmpleado());
            ps.setFloat(4, venta.getTotal());
            ps.setString(5, venta.getFecha());
            
            ps.execute();
            return true;
            
             } catch (SQLException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RegistroProductoVenta (VentaProducto ventaProducto) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Venta_Producto (idProducto, idVenta, Cantidad) VALUES (?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, ventaProducto.getIdProducto());
            ps.setString(2, ventaProducto.getIdVenta());
            ps.setInt(3, ventaProducto.getCantidad());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaProducto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
    public boolean ModificarVenta(Venta venta) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionBaseDeDatos con = new ConexionBaseDeDatos();
        int res = 0;
        
        String sql = "UPDATE Venta SET Total = ?, FechaDeVenta = ? WHERE idVenta = ?";
        
        try {
            ps = con.getConnectionDB().prepareStatement(sql);
            
            ps.setFloat(1, venta.getTotal());
            ps.setString(2, venta.getFecha());
            ps.setString(3, venta.getIdVenta());

            res = ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public boolean ModificarProductoVenta(VentaProducto ventaProducto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionBaseDeDatos con = new ConexionBaseDeDatos();
        int res = 0;
        
        String sql = "UPDATE Venta_Producto "
                + "SET idProducto = ?, Cantidad = ? "
                + "WHERE idVenta = ?";
        
        try {
            ps = con.getConnectionDB().prepareStatement(sql);
            
            ps.setString(1, ventaProducto.getIdProducto());
            ps.setInt(2, ventaProducto.getCantidad());
            ps.setString(3, ventaProducto.getIdVenta());

            res = ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public List MostrarVentas () {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        ConexionBaseDeDatos conectar = new ConexionBaseDeDatos();
        //Productos p = new Productos();
    
        List<Venta> datos = new ArrayList<>();
        try {
            con = conectar.getConnectionDB();
            ps = con.prepareStatement("select * from venta");
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta p = new Venta();
                p.setIdVenta(rs.getString(1));
                p.setTotal(rs.getFloat(4));
                p.setFecha(rs.getString(5));
                
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    public boolean BuscarPrecioMayorista(Mayorista mayorista) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT idMayorista, Nombre, ApellidoPaterno, ApellidoMaterno, Precio FROM Mayorista WHERE idMayorista = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, mayorista.getIdMayorista());
            rs = ps.executeQuery();
            
            if(rs.next()){
                mayorista.setIdMayorista(rs.getString("IdMayorista"));
                mayorista.setNombre(rs.getString("Nombre"));
                mayorista.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                mayorista.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                mayorista.setPrecio(rs.getFloat("Precio"));
                
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
    
    public boolean BuscarDatosEmpleados(Empleado empleado, TelefonoEmpleado telefono) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT Empleado.idEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno, Telefono FROM Empleado, TelefonoEmpleado, Repartidor WHERE Empleado.idEmpleado = TelefonoEmpleado.idEmpleado AND Empleado.idEmpleado = Repartidor.idEmpleado AND Empleado.idEmpleado = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, empleado.getIdEmpleado());
            rs = ps.executeQuery();
            
            if(rs.next()){
                empleado.setIdEmpleado(rs.getString("idEmpleado"));
                empleado.setNombre(rs.getString("Nombre"));
                empleado.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                empleado.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                
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
}

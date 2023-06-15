package Modelo;

import Controlador.Empleado;
import Controlador.EmpleadoPlanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaEmpleadoPlanta extends ConexionBaseDeDatos {
    
    public boolean RegistrarContraseñaEmpleadoPlanta (EmpleadoPlanta ePlanta) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "UPDATE EmpleadoPlanta SET Password = ? WHERE idEmpleado = ?";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, ePlanta.getPassword());
            
            ps.setString(2, ePlanta.getIdEmpleado());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoPlanta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Verifica si el empleado ya se encuentra registrado
    public int ExistenciaDeEmpleado (String empleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConnectionDB(); 
        
        //Solo valida que el usuario no sea el mismo.
        String sql = "SELECT count(idEmpleado) FROM EmpleadoPlanta WHERE idEmpleado = ? ";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado);
            
            rs = ps.executeQuery();
            
            if(rs.next())
                return rs.getInt(1);
            
            return 1;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaMayorista.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } 
    }
    
    public boolean Login (EmpleadoPlanta empleadoPlanta) {
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "SELECT * FROM EmpleadoPlanta WHERE idEmpleado = ?";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, empleadoPlanta.getIdEmpleado());
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                if(empleadoPlanta.getPassword().equals(rs.getString(3))){
                    
                    return true;
                } else
                    return false;
            }
            return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaEmpleadoPlanta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean BuscarNombre (Empleado empleado) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT idEmpleado, Nombre FROM Empleado WHERE idEmpleado = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, empleado.getIdEmpleado());
            rs = ps.executeQuery();
            
            if(rs.next()){
                empleado.setIdEmpleado(rs.getString("IdEmpleado"));
                empleado.setNombre(rs.getString("Nombre"));
                
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

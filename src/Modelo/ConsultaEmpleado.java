package Modelo;

import Controlador.Empleado;
import Controlador.EmpleadoCURP;
import Controlador.EmpleadoPlanta;
import Controlador.EmpleadoRFC;
import Controlador.EmpleadoRepartidor;
import Controlador.Mayorista;
import Controlador.TelefonoEmpleado;
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

public class ConsultaEmpleado extends ConexionBaseDeDatos {
    
    public boolean RegistroEmpleado (Empleado empleado) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Empleado (idEmpleado, nombre, apellidoPaterno, apellidoMaterno, calle, colonia) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado.getIdEmpleado());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellidoPaterno());
            ps.setString(4, empleado.getApellidoMaterno());
            ps.setString(5, empleado.getCalle());
            ps.setString(6, empleado.getColonia());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean CURPEmpleado (EmpleadoCURP CURP) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO CURP (idEmpleado, CURP) VALUES (?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, CURP.getIdEmpleado());
            ps.setString(2, CURP.getCURP());
           
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RFCEmpleado (EmpleadoRFC RFC) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO RFC (idEmpleado, RFC) VALUES (?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, RFC.getIdEmpleado());
            ps.setString(2, RFC.getRFC());
           
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RegistrarTelefono (TelefonoEmpleado telefono) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO TelefonoEmpleado (idEmpleado, Telefono) VALUES (?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, telefono.getIdEmpleado());
            ps.setString(2, telefono.getTelefono());
           
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int ExistenciaDeEmpleado (String empleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConnectionDB(); 
        
        //Solo valida que el usuario no sea el mismo.
        String sql = "SELECT count(idEmpleado) FROM Empleado WHERE idEmpleado = ? ";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, empleado);
            
            rs = ps.executeQuery();
            
            if(rs.next())
                return rs.getInt(1);
            
            return 1;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } 
    }
    
    public boolean BorrarEmpleado (String id) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "DELETE FROM Empleado WHERE idEmpleado = ?";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean ModificarEmpleado(Empleado empleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ConexionBaseDeDatos con = new ConexionBaseDeDatos();
        int res = 0;
        
        String sql = "UPDATE Empleado "
                + "SET Nombre = ?, ApellidoPaterno = ?, ApellidoMaterno= ?, Calle = ?, Colonia = ? "
                + "WHERE idEmpleado = ?";
        
        try {
            ps = con.getConnectionDB().prepareStatement(sql);
            
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidoPaterno());
            ps.setString(3, empleado.getApellidoMaterno());
            ps.setString(4, empleado.getCalle());
            ps.setString(5, empleado.getColonia());
            ps.setString(6, empleado.getIdEmpleado());
            
            res = ps.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQLException:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        return false;
    }
    
    public boolean BuscarEmpleado(Empleado empleado, TelefonoEmpleado telefono, EmpleadoCURP curp, EmpleadoRFC rfc, EmpleadoRepartidor repartidor) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT Empleado.idEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno, Calle, Colonia, Telefono, CURP, RFC, Comision  FROM Empleado, TelefonoEmpleado, CURP, RFC, Repartidor WHERE Empleado.idEmpleado = TelefonoEmpleado.idEmpleado  AND Empleado.idEmpleado = CURP.idEmpleado AND Empleado.idEmpleado = RFC.idEmpleado AND Empleado.idEmpleado = Repartidor.idEmpleado AND Empleado.idEmpleado = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, empleado.getIdEmpleado());
            rs = ps.executeQuery();
            
            if(rs.next()){
                empleado.setIdEmpleado(rs.getString("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidoPaterno(rs.getString("apellidoPaterno"));
                empleado.setApellidoMaterno(rs.getString("apellidoMaterno"));
                empleado.setCalle(rs.getString("calle"));
                empleado.setColonia(rs.getString("colonia"));
                
                telefono.setTelefono(rs.getString("Telefono"));
                
                curp.setCURP(rs.getString("CURP"));
                rfc.setRFC(rs.getString("RFC"));
                
                //planta.setSueldo(rs.getFloat("Sueldo"));
                repartidor.setComision(rs.getFloat("Comision"));
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
    
    public boolean BuscarEmpleadoPlanta(Empleado empleado, TelefonoEmpleado telefono, EmpleadoCURP curp, EmpleadoRFC rfc, EmpleadoPlanta planta) {
        PreparedStatement ps = null;
        Connection con = getConnectionDB();
        ResultSet rs = null;
        
        String sql = "SELECT Empleado.idEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno, Calle, Colonia, Telefono, CURP, RFC, Sueldo  FROM Empleado, TelefonoEmpleado, CURP, RFC, EmpleadoPlanta WHERE Empleado.idEmpleado = TelefonoEmpleado.idEmpleado AND Empleado.idEmpleado = CURP.idEmpleado AND Empleado.idEmpleado = RFC.idEmpleado AND Empleado.idEmpleado = EmpleadoPlanta.idEmpleado AND Empleado.idEmpleado = ?";
        
        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, empleado.getIdEmpleado());
            rs = ps.executeQuery();
            
            if(rs.next()){
                empleado.setIdEmpleado(rs.getString("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidoPaterno(rs.getString("apellidoPaterno"));
                empleado.setApellidoMaterno(rs.getString("apellidoMaterno"));
                empleado.setCalle(rs.getString("calle"));
                empleado.setColonia(rs.getString("colonia"));
                
                telefono.setTelefono(rs.getString("Telefono"));
                
                curp.setCURP(rs.getString("CURP"));
                rfc.setRFC(rs.getString("RFC"));
                
                planta.setSueldo(rs.getFloat("Sueldo"));
                //repartidor.setComision(rs.getFloat("Comision"));
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
    
    
    
    
    
    public List MostrarEmpleados () {
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        ConexionBaseDeDatos conectar = new ConexionBaseDeDatos();
        //Productos p = new Productos();
    
        List<Empleado> datos = new ArrayList<>();
        try {
            con = conectar.getConnectionDB();
            ps = con.prepareStatement("select * from empleado");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado p = new Empleado();
                p.setIdEmpleado(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setApellidoPaterno(rs.getString(3));
                p.setApellidoMaterno(rs.getString(4));
                p.setCalle(rs.getString(5));
                
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    
    
    
    // REPARTIDORES Y EMPLEADOS DE PLANTA
    public boolean RegistroEmpleadoPlanta (EmpleadoPlanta planta) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO EmpleadoPlanta (idEmpleado, sueldo, password) VALUES (?, ?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, planta.getIdEmpleado());
            ps.setFloat(2, planta.getSueldo());
            ps.setString(3, planta.getPassword());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean RegistroEmpleadoRepartidor (EmpleadoRepartidor repartidor) {
        PreparedStatement ps = null;
        
        Connection conexion = getConnectionDB(); 
        
        String sql = "INSERT INTO Repartidor (idEmpleado, comision) VALUES (?, ?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, repartidor.getIdEmpleado());
            ps.setFloat(2, repartidor.getComision());
      
            ps.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

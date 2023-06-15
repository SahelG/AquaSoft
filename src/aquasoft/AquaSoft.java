package aquasoft;

import Controlador.CtrlLogin;
import Controlador.EmpleadoPlanta;
import Modelo.ConexionBaseDeDatos;
import Modelo.ConsultaEmpleadoPlanta;
import Vistas.VistaLoginV2;

public class AquaSoft {

    public static void main(String[] args) {
        //PRUEBA DE CONEXION A LA BASE, SI JALA
        
        //ConexionBaseDeDatos conexion = new ConexionBaseDeDatos();
        //conexion.getConnectionDB();
        
        VistaLoginV2 frmLogin = new VistaLoginV2();
        EmpleadoPlanta modEmpleado = new EmpleadoPlanta();
        ConsultaEmpleadoPlanta modCEPlanta = new ConsultaEmpleadoPlanta();
            
        CtrlLogin controlLogin = new CtrlLogin(frmLogin, modEmpleado, modCEPlanta);
        controlLogin.Iniciar();
        
        
        /*VistaMenu frmMenu = new VistaMenu();
        CtrlMenu controlador = new CtrlMenu(frmMenu);
        controlador.Iniciar();
        
        frmMenu.setVisible(true);
        frmMenu.setSize(776, 373);
        frmMenu.setLocationRelativeTo(null);*/
    }
    
}

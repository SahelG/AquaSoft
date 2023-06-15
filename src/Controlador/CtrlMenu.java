package Controlador;

import Modelo.ConsultaEmpleado;
import Modelo.ConsultaInsumo;
import Modelo.ConsultaMayorista;
import Modelo.ConsultaProducto;
import Modelo.ConsultaVenta;
import Vistas.VistaClientesV2;
import Vistas.VistaEmpleados;
import Vistas.VistaInsumos;
import Vistas.VistaMenu;
import Vistas.VistaProducto;
import Vistas.VistaVenta;
import java.awt.BorderLayout;
import static java.awt.Frame.ICONIFIED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class CtrlMenu implements ActionListener {
    private VistaMenu frmMenu;
    
    public CtrlMenu (VistaMenu frmMenu) {
        this.frmMenu = frmMenu;
        
        this.frmMenu.btnVentas.addActionListener(this);
        this.frmMenu.btnClientes.addActionListener(this);
        this.frmMenu.btnEmpleados.addActionListener(this);
        this.frmMenu.btnInsumos.addActionListener(this);
        this.frmMenu.btnProductos.addActionListener(this);
        
        this.frmMenu.btnSalir.addActionListener(this);
        this.frmMenu.btnMinimizar.addActionListener(this);
    }
    
    public void Iniciar () {
        frmMenu.setLocationRelativeTo(null);
        frmMenu.setVisible(true);
        
        frmMenu.lblFecha.setText(fechaActual());
    }
    
    public static String fechaActual(){
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == frmMenu.btnVentas) {
            VistaVenta frmVenta = new VistaVenta();
        //VistaProductosSeleccion frmProductoSeleccion = new VistaProductosSeleccion();
            Venta modVenta = new Venta();
            ConsultaVenta modCVenta = new ConsultaVenta();
            Producto modProductos = new Producto();
            ConsultaProducto modCProducto = new ConsultaProducto();

            CtrlVenta control = new CtrlVenta(frmVenta, modVenta, modCVenta, modProductos, modCProducto);
            control.Iniciar();
            
            frmVenta.txtIdEmpleado.setText(frmMenu.id.getText());

            frmVenta.setVisible(true);

            //VistaVenta obj = new VistaVenta();
            frmVenta.setSize(976, 732);
            frmVenta.setLocationRelativeTo(null);
            
        }else if (e.getSource() == frmMenu.btnClientes) {
            Mayorista modMayorista = new Mayorista();
            TelefonoMayorista modTelefonoMayorista = new TelefonoMayorista();
            ConsultaMayorista modCMayorista = new ConsultaMayorista();

            VistaClientesV2 frmMayorista = new VistaClientesV2();
            CtrMayoristaV2 controlador = new CtrMayoristaV2(modMayorista, modTelefonoMayorista, modCMayorista, frmMayorista);
            controlador.Iniciar();
            frmMayorista.setVisible(true);

            frmMayorista.setSize(887, 492);
            frmMayorista.setLocationRelativeTo(null);
            
        }else if (e.getSource() == frmMenu.btnEmpleados) {
            Empleado modEmpleado = new Empleado();
            TelefonoEmpleado modTelefonoEmpleado = new TelefonoEmpleado();
            EmpleadoCURP modEmpleadoCURP = new EmpleadoCURP();
            EmpleadoRFC modEmpleadoRFC = new EmpleadoRFC();
            EmpleadoPlanta modPlanta = new EmpleadoPlanta();
            EmpleadoRepartidor modRepartidor = new EmpleadoRepartidor();
            
            ConsultaEmpleado modCEmpleado = new ConsultaEmpleado();
            VistaEmpleados frmEmpleado = new VistaEmpleados();

            CtrEmpleado controlador = new CtrEmpleado(modEmpleado, modTelefonoEmpleado, modEmpleadoCURP, modEmpleadoRFC, modPlanta, modRepartidor, modCEmpleado, frmEmpleado);
            controlador.Iniciar();
            frmEmpleado.setVisible(true);

            frmEmpleado.setSize(887, 613);
            frmEmpleado.setLocationRelativeTo(null);
            
        }else if (e.getSource() == frmMenu.btnInsumos) {
            Insumo modInsumo = new Insumo();
    
            ConsultaInsumo modCInsumo = new ConsultaInsumo();
            
            VistaInsumos frmInsumo = new VistaInsumos();
            
            CtrInsumo controlador = new CtrInsumo(modInsumo, modCInsumo, frmInsumo);
            controlador.Iniciar();
            
            frmInsumo.setVisible(true);
            frmInsumo.setSize(792, 456);
            frmInsumo.setLocationRelativeTo(null); 
            
        }else if (e.getSource() == frmMenu.btnProductos) {
            Producto modProducto = new Producto();
            ProductoExterno modExterno = new ProductoExterno();
            ProductoInterno modInterno = new ProductoInterno();
            
            ConsultaProducto modCProducto = new ConsultaProducto();

            VistaProducto frmProducto = new VistaProducto();
            CtrProducto controlador = new CtrProducto(modProducto, modExterno, modInterno, modCProducto, frmProducto);
            controlador.Iniciar();
            frmProducto.setVisible(true);

            frmProducto.setSize(887, 503);
            frmProducto.setLocationRelativeTo(null); 
        }else if (e.getSource() == frmMenu.btnMinimizar) {
            frmMenu.setExtendedState(ICONIFIED);
        }else if (e.getSource() == frmMenu.btnSalir) {
            int uno=JOptionPane.showConfirmDialog(frmMenu, "¿Deseas salir?", "Sistema",JOptionPane.INFORMATION_MESSAGE);
            if(uno == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}

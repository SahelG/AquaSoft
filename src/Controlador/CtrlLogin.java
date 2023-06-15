package Controlador;

import Modelo.ConsultaEmpleadoPlanta;
import Vistas.VistaLoginV2;
import Vistas.VistaMenu;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlLogin implements ActionListener {
    private VistaLoginV2 frmLogin;
    private Empleado modEmpleado = new Empleado();
    private EmpleadoPlanta modPlanta;
    private ConsultaEmpleadoPlanta modCEPlanta;
    
    private VistaMenu menu = new VistaMenu();
    private CtrlMenu control = new CtrlMenu(menu);
    
    public String usuario;
    
    CardLayout cardLayout;
    
    public CtrlLogin (VistaLoginV2 frmLogin, EmpleadoPlanta modPlanta, ConsultaEmpleadoPlanta modCEPlanta) {
        this.frmLogin = frmLogin;
        this.modPlanta = modPlanta;
        this.modCEPlanta = modCEPlanta;
        
        cardLayout = (CardLayout)(frmLogin.panelCards.getLayout());
        this.frmLogin.btnEntrarP1.addActionListener(this);
        this.frmLogin.btnRegistrarseP1.addActionListener(this);
        this.frmLogin.btnEntrarP2.addActionListener(this);
        this.frmLogin.btnRegistrarseP2.addActionListener(this);
        
        //Boton principal
        this.frmLogin.btnRegistrar.addActionListener(this);
        this.frmLogin.btnIngresar.addActionListener(this);
        
        //menu
        this.menu.btnEmpleados.addActionListener(this);
    }
    
    public void Iniciar () {
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
    }
    
    public void Limpiar () {
        frmLogin.pdwUsuario.setText(null);
        frmLogin.pdwUsuarioP2.setText(null);
        frmLogin.txtUsuario.setText(null);
        frmLogin.txtUsuario1.setText(null);
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == frmLogin.btnEntrarP1) {
            frmLogin.panelCards.removeAll();
            frmLogin.panelCards.add(frmLogin.PanelIngreso);
            frmLogin.panelCards.repaint();
            frmLogin.panelCards.revalidate();
        }else if (e.getSource() == frmLogin.btnRegistrarseP1) {
            frmLogin.panelCards.removeAll();
            frmLogin.panelCards.add(frmLogin.PanelRegistro);
            frmLogin.panelCards.repaint();
            frmLogin.panelCards.revalidate();
        }else if (e.getSource() == frmLogin.btnEntrarP2) {
            frmLogin.panelCards.removeAll();
            frmLogin.panelCards.add(frmLogin.PanelIngreso);
            frmLogin.panelCards.repaint();
            frmLogin.panelCards.revalidate();
        }else if (e.getSource() == frmLogin.btnRegistrarseP2) {
            frmLogin.panelCards.removeAll();
            frmLogin.panelCards.add(frmLogin.PanelRegistro);
            frmLogin.panelCards.repaint();
            frmLogin.panelCards.revalidate();
            
        }else if(e.getSource() == frmLogin.btnIngresar) {
            if(frmLogin.txtUsuario.getText().equals("") || frmLogin.pdwUsuario.getPassword().equals("")){
                JOptionPane.showMessageDialog(null, "Favor de llenar los campos vacios.", "Error.", JOptionPane.WARNING_MESSAGE);
            }else {
                String userAdmin = "admin";
                String pswAdmin = "1234";
                String pp = new String(frmLogin.pdwUsuario.getPassword());
                
                usuario = frmLogin.txtUsuario.getText();
                modPlanta.setIdEmpleado(frmLogin.txtUsuario.getText());
                modPlanta.setPassword(String.valueOf(frmLogin.pdwUsuario.getPassword()));
            
                menu.id.setText(usuario);
                menu.lblBienvenidaUsuario.setText(usuario);
                
                
                if (modCEPlanta.Login(modPlanta)) {
                    //JOptionPane.showMessageDialog(null, "Bienvenido " + usuario);
                    //menu.setVisible(true);
                    
                    modEmpleado.setIdEmpleado(frmLogin.txtUsuario.getText());
                    
                    if (modCEPlanta.BuscarNombre(modEmpleado)) {
                        menu.lblBienvenidaUsuario.setText(modEmpleado.getNombre());
                    }
                    
                    control.Iniciar();
                    frmLogin.dispose();
                    menu.btnEmpleados.setEnabled(false);
                    
                } else if (frmLogin.txtUsuario.getText().equals(userAdmin) && pp.equals(pswAdmin)) {
                    control.Iniciar();
                    frmLogin.dispose();
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "Datos incorrectos.", "Error.", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == frmLogin.btnRegistrar) {
            if(frmLogin.txtUsuario1.getText().equals("") || frmLogin.pdwUsuarioP2.getPassword().equals("")){
                JOptionPane.showMessageDialog(null, "Favor de llenar los campos vacios.", "Error.", JOptionPane.WARNING_MESSAGE);
            }else {
                modPlanta.setIdEmpleado(frmLogin.txtUsuario1.getText());
                modPlanta.setPassword(String.valueOf(frmLogin.pdwUsuarioP2.getPassword()));  
                    
                if (modCEPlanta.RegistrarContraseñaEmpleadoPlanta(modPlanta)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado.", "Guardado", JOptionPane.PLAIN_MESSAGE);
                    Limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error.");
                    Limpiar();
                }
            }
        }
    }
}

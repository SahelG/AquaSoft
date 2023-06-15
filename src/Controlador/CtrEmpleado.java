package Controlador;

import Modelo.ConsultaEmpleado;
import PlaceHolder.TextPrompt;
import Vistas.VistaEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrEmpleado implements ActionListener {
    private Empleado modEmpleado;
    private TelefonoEmpleado modTelefonoEmpleado;
    private EmpleadoCURP modEmpleadoCURP;
    private EmpleadoRFC modEmpleadoRFC;
    private EmpleadoPlanta modPlanta;
    private EmpleadoRepartidor modRepartidor;
    
    private ConsultaEmpleado modCEmpleado;
    private VistaEmpleados frmEmpleado;
    private int incremento;
    
    private DefaultTableModel modeloEmpleado = new DefaultTableModel();

    public CtrEmpleado (Empleado modEmpleado, TelefonoEmpleado modTelefonoEmpleado, EmpleadoCURP modEmpleadoCURP, EmpleadoRFC modEmpleadoRFC, EmpleadoPlanta modPlanta, EmpleadoRepartidor modRepartidor, ConsultaEmpleado modCEmpleado, VistaEmpleados frmEmpleado) {
        this.modEmpleado = modEmpleado;
        this.modTelefonoEmpleado = modTelefonoEmpleado;
        this.modEmpleadoCURP = modEmpleadoCURP;
        this.modEmpleadoRFC = modEmpleadoRFC;
        this.modPlanta = modPlanta;
        this.modRepartidor = modRepartidor;
        
        
        this.modCEmpleado = modCEmpleado;
        this.frmEmpleado = frmEmpleado;
        this.frmEmpleado.btnAgregar.addActionListener(this);
        this.frmEmpleado.btnEditar.addActionListener(this);
        this.frmEmpleado.btnBuscar.addActionListener(this);
        this.frmEmpleado.btnEliminar.addActionListener(this);
        
        this.frmEmpleado.checkPlanta.addActionListener(this);
        this.frmEmpleado.checkRepartidor.addActionListener(this);
        this.frmEmpleado.btnSalir.addActionListener(this);
    }
    
    public void Iniciar () {
        //frmMayorista.setVisible(true);
        //frmMayorista.setLocationRelativeTo(null);
        LlenadoDeId(incremento);
        Listar(frmEmpleado.tableEmpleados);
        PlaceHolder();
        
        
        
        //BloquearCampos();
    }
    
   
    public void PlaceHolder () {
        TextPrompt placeHolder = new TextPrompt("Nombre", frmEmpleado.txtNombreEmpleado);
        TextPrompt placeHolder2 = new TextPrompt("Apellido paterno", frmEmpleado.txtApellidoPaternoEmpleado);
        TextPrompt placeHolder3 = new TextPrompt("Apellido materno", frmEmpleado.txtApellidoMaternoEmpleado);
        TextPrompt placeHolder4 = new TextPrompt("Calle", frmEmpleado.txtCalle);
        TextPrompt placeHolder5 = new TextPrompt("Colonia", frmEmpleado.txtColonia);
        TextPrompt placeHolder6 = new TextPrompt("Numero telefonico", frmEmpleado.txtNumeroTelefonicoEmpleado);
        TextPrompt placeHolder7 = new TextPrompt("RFC", frmEmpleado.txtRFCEmpleado);
        TextPrompt placeHolder8 = new TextPrompt("CURP", frmEmpleado.txtCURPEmpleado);
        TextPrompt placeHolder9 = new TextPrompt("Sueldo", frmEmpleado.txtSueldo);
        TextPrompt placeHolder10 = new TextPrompt("Comision", frmEmpleado.txtComision);
        TextPrompt placeHolder11 = new TextPrompt("Password", frmEmpleado.pswEmpleado);
    }
    
    public void LlenadoDeId(int incremento){
        //String folio=modCR.mostrarFolio();   
        //int incremento=0;//=Integer.parseInt(folio);
        incremento=incremento+1;
        frmEmpleado.txtIdEmpleado.setText("0"+incremento);
    }
    
    public void Listar (JTable tabla) {
        modeloEmpleado = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloEmpleado);
        
        List<Empleado>lista = modCEmpleado.MostrarEmpleados();
        
        Object [] obj = new Object[5];
        
        for (int i = 0; i < lista.size(); i++) {
            obj[0] = lista.get(i).getIdEmpleado();
            obj[1] = lista.get(i).getNombre();
            obj[2] = lista.get(i).getApellidoPaterno();
            obj[3] = lista.get(i).getApellidoMaterno();
            obj[4] = lista.get(i).getCalle();
            
            modeloEmpleado.addRow(obj);
        }
        
        frmEmpleado.tableEmpleados.setModel(modeloEmpleado);
    }
    
    public static boolean validarNumeros(String datos){
        return datos.matches("[0.00-9.99]*");
    }
    
    public static boolean validarNumerosEnteros(String datos){
        return datos.matches("[0-9]{1,11}");
    }
    
    public void LimpiarCampos () {
        frmEmpleado.txtNombreEmpleado.setText(null);
        frmEmpleado.txtApellidoPaternoEmpleado.setText(null);
        frmEmpleado.txtApellidoMaternoEmpleado.setText(null);
        frmEmpleado.txtCalle.setText(null);
        frmEmpleado.txtColonia.setText(null);
        frmEmpleado.txtRFCEmpleado.setText(null);
        frmEmpleado.txtCURPEmpleado.setText(null);
        frmEmpleado.txtComision.setText(null);
        
        frmEmpleado.txtSueldo.setText(null);
        frmEmpleado.txtComision.setText(null);
        frmEmpleado.pswEmpleado.setText(null);
        
        frmEmpleado.txtNumeroTelefonicoEmpleado.setText(null);
    }
    
    public void LimpiarTabla () {
        int i;
        
        for (i = modeloEmpleado .getRowCount() - 1; i >= 0; i--) {
            modeloEmpleado.removeRow(i);
        }
    }
    
    public void BloquearCampos () {
        frmEmpleado.txtSueldo.setEditable(false);
        frmEmpleado.txtComision.setEditable(false);
        frmEmpleado.pswEmpleado.setEditable(false);
    }
    
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource() == frmEmpleado.checkPlanta) {
            frmEmpleado.txtSueldo.setEditable(true);
            frmEmpleado.txtComision.setEditable(false);
            
        }else if (e.getSource() == frmEmpleado.checkRepartidor) {
            frmEmpleado.txtComision.setEditable(true);
            frmEmpleado.txtSueldo.setEditable(false);
            frmEmpleado.pswEmpleado.setEditable(false);
            
        }else if(e.getSource() == frmEmpleado.btnAgregar) {
            /*if (!frmEmpleado.checkPlanta.isSelected() || !frmEmpleado.checkRepartidor.isSelected()) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione el tipo de empleado");
            } else*/ if (frmEmpleado.txtNombreEmpleado.getText().equals("") || frmEmpleado.txtApellidoPaternoEmpleado.getText().equals("") || frmEmpleado.txtCalle.getText().equals("") || frmEmpleado.txtColonia.getText().equals("") ||
            frmEmpleado.txtNumeroTelefonicoEmpleado.getText().equals("") || frmEmpleado.txtCURPEmpleado.getText().equals("") || frmEmpleado.txtRFCEmpleado.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
            }else {
                if (!validarNumerosEnteros(frmEmpleado.txtNumeroTelefonicoEmpleado.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese numeros enteros");
                }else {
                    
                    if (frmEmpleado.checkPlanta.isSelected()) {
                        if (!validarNumeros(frmEmpleado.txtSueldo.getText().trim())) {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
                        }
                    }else if (frmEmpleado.checkRepartidor.isSelected()) {
                        if (!validarNumeros(frmEmpleado.txtComision.getText().trim())) {
                            JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
                        }
                    }
                    
                    modEmpleado.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                    modEmpleado.setNombre(frmEmpleado.txtNombreEmpleado.getText());
                    modEmpleado.setApellidoPaterno(frmEmpleado.txtApellidoPaternoEmpleado.getText());
                    modEmpleado.setApellidoMaterno(frmEmpleado.txtApellidoMaternoEmpleado.getText());
                    modEmpleado.setCalle(frmEmpleado.txtCalle.getText());
                    modEmpleado.setColonia(frmEmpleado.txtColonia.getText());

                    modTelefonoEmpleado.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                    modTelefonoEmpleado.setTelefono(frmEmpleado.txtNumeroTelefonicoEmpleado.getText());

                    modEmpleadoCURP.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                    modEmpleadoCURP.setCURP((frmEmpleado.txtCURPEmpleado.getText()));

                    modEmpleadoRFC.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                    modEmpleadoRFC.setRFC(frmEmpleado.txtRFCEmpleado.getText());


                    if (modCEmpleado.RegistroEmpleado(modEmpleado) && modCEmpleado.RegistrarTelefono(modTelefonoEmpleado) && modCEmpleado.CURPEmpleado(modEmpleadoCURP) && modCEmpleado.RFCEmpleado(modEmpleadoRFC)){

                        if (frmEmpleado.checkPlanta.isSelected()) {
                            
                            modPlanta.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                            modPlanta.setPassword(frmEmpleado.pswEmpleado.getText());
                            modPlanta.setSueldo(Float.parseFloat(frmEmpleado.txtSueldo.getText()));

                            if(modCEmpleado.RegistroEmpleadoPlanta(modPlanta)) {}
                            
                        }else if (frmEmpleado.checkRepartidor.isSelected()) {
                            
                            modRepartidor.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                            modRepartidor.setComision(Float.parseFloat(frmEmpleado.txtComision.getText()));
                            //modRepartidor.setPassword(frmEmpleado.pswEmpleado.getText());

                            if(modCEmpleado.RegistroEmpleadoRepartidor(modRepartidor)){}
                        }else {
                            JOptionPane.showMessageDialog(null, "El registro no pudo ser guardado.");
                        }
                        
                        JOptionPane.showMessageDialog(null, "Registro guardado");
                        LimpiarCampos();
                        incremento++;
                        LlenadoDeId(incremento);
                        LimpiarTabla();
                        frmEmpleado.buttonGroup1.clearSelection();
                        Listar(frmEmpleado.tableEmpleados);
                        BloquearCampos();
                    }else {
                        JOptionPane.showMessageDialog(null, "El registro no pudo ser guardado.");
                    }
                    
                    
                }
            }
        }else if(e.getSource() == frmEmpleado.btnEditar) {
            if (!validarNumerosEnteros(frmEmpleado.txtNumeroTelefonicoEmpleado.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese numeros enteros");
            }else {
                modEmpleado.setIdEmpleado(frmEmpleado.txtIdEmpleado.getText());
                modEmpleado.setNombre(frmEmpleado.txtNombreEmpleado.getText());
                modEmpleado.setApellidoPaterno(frmEmpleado.txtApellidoPaternoEmpleado.getText());
                modEmpleado.setApellidoMaterno(frmEmpleado.txtApellidoMaternoEmpleado.getText());
                modEmpleado.setCalle(frmEmpleado.txtCalle.getText());
                modEmpleado.setColonia(frmEmpleado.txtColonia.getText());
                
                //modTelefonoEmpleado.setTelefono(frmEmpleado.txtNumeroTelefonicoEmpleado.getText());
                    
               

                if (modCEmpleado.ModificarEmpleado(modEmpleado)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado");
                    LimpiarCampos();
                    LlenadoDeId(incremento);
                    LimpiarTabla();
                    Listar(frmEmpleado.tableEmpleados);
                    //BloquearCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "El registro no se pudo modificar");
                }
            }
        
        }else if (e.getSource() == frmEmpleado.btnEliminar) {
            int uno = JOptionPane.showConfirmDialog(frmEmpleado, "¿Deseas eliminar el empleado?", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            
            int fila = frmEmpleado.tableEmpleados.getSelectedRow();
            
            if (uno == JOptionPane.YES_OPTION) {
                if (fila == -1) {
                    //JOptionPane.showMessageDialog(frmMayorista, "Cancelacion de eliminacion exitosa","Sistema",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario.");
                }else {
                    String id = (String) frmEmpleado.tableEmpleados.getValueAt(fila, 0).toString();
                    modCEmpleado.BorrarEmpleado(id);
                    JOptionPane.showMessageDialog(null, "Empleado eliminado");

                    LimpiarCampos();
                    LimpiarTabla();
                    Listar(frmEmpleado.tableEmpleados);
                }
            }
            
            
        }else if (e.getSource() == frmEmpleado.btnBuscar) {
            String idJOptionEmpleado = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
            modEmpleado.setIdEmpleado(idJOptionEmpleado);
                
            if (modCEmpleado.BuscarEmpleado(modEmpleado, modTelefonoEmpleado, modEmpleadoCURP, modEmpleadoRFC, modRepartidor)) {
                frmEmpleado.txtIdEmpleado.setText(modEmpleado.getIdEmpleado());
                frmEmpleado.txtNombreEmpleado.setText(modEmpleado.getNombre());
                frmEmpleado.txtApellidoPaternoEmpleado.setText(modEmpleado.getApellidoPaterno());
                frmEmpleado.txtApellidoMaternoEmpleado.setText(modEmpleado.getApellidoMaterno());
                frmEmpleado.txtCalle.setText(modEmpleado.getCalle());
                frmEmpleado.txtColonia.setText(modEmpleado.getColonia());

                frmEmpleado.txtNumeroTelefonicoEmpleado.setText(modTelefonoEmpleado.getTelefono());
                
                frmEmpleado.txtCURPEmpleado.setText(modEmpleadoCURP.getCURP());
                frmEmpleado.txtRFCEmpleado.setText(modEmpleadoRFC.getRFC());
                
                //frmEmpleado.txtSueldo.setText(String.valueOf(modPlanta.getSueldo()));
                frmEmpleado.txtComision.setText(String.valueOf(modRepartidor.getComision()));
                frmEmpleado.txtSueldo.setText("");

                //BloquearCampos();
                JOptionPane.showMessageDialog(null, "Registro encontrado");
                    
            }else if (modCEmpleado.BuscarEmpleadoPlanta(modEmpleado, modTelefonoEmpleado, modEmpleadoCURP, modEmpleadoRFC, modPlanta)) {
                frmEmpleado.txtIdEmpleado.setText(modEmpleado.getIdEmpleado());
                frmEmpleado.txtNombreEmpleado.setText(modEmpleado.getNombre());
                frmEmpleado.txtApellidoPaternoEmpleado.setText(modEmpleado.getApellidoPaterno());
                frmEmpleado.txtApellidoMaternoEmpleado.setText(modEmpleado.getApellidoMaterno());
                frmEmpleado.txtCalle.setText(modEmpleado.getCalle());
                frmEmpleado.txtColonia.setText(modEmpleado.getColonia());

                frmEmpleado.txtNumeroTelefonicoEmpleado.setText(modTelefonoEmpleado.getTelefono());
                
                frmEmpleado.txtCURPEmpleado.setText(modEmpleadoCURP.getCURP());
                frmEmpleado.txtRFCEmpleado.setText(modEmpleadoRFC.getRFC());
                
                frmEmpleado.txtSueldo.setText(String.valueOf(modPlanta.getSueldo()));
                //frmEmpleado.txtComision.setText(String.valueOf(modRepartidor.getComision()));
                frmEmpleado.txtComision.setText("");
                //BloquearCampos();
                JOptionPane.showMessageDialog(null, "Registro encontrado");
            }else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
            }
        } else if (e.getSource() == frmEmpleado.btnSalir) {
            frmEmpleado.dispose();
        }
    }       
}

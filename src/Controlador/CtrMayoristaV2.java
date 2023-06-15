package Controlador;

import Modelo.ConsultaEmpleado;
import Modelo.ConsultaMayorista;
import PlaceHolder.TextPrompt;
import Vistas.VistaClientesV2;
import Vistas.VistaEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrMayoristaV2 implements ActionListener {
    private Mayorista modMayorista;
    private TelefonoMayorista modTelefonoMayorista;
    
    private ConsultaMayorista modCMayorista;
    private VistaClientesV2 frmMayorista;
    private int incremento;
    
    private DefaultTableModel modeloMayorista = new DefaultTableModel();

    public CtrMayoristaV2 (Mayorista modMayorista, TelefonoMayorista modTelefonoMayorista, ConsultaMayorista modCMayorista, VistaClientesV2 frmMayorista) {
        this.modMayorista = modMayorista;
        this.modTelefonoMayorista = modTelefonoMayorista;
        
        
        this.modCMayorista = modCMayorista;
        this.frmMayorista = frmMayorista;
        this.frmMayorista.btnAgregarV.addActionListener(this);
        this.frmMayorista.btnEditar.addActionListener(this);
        this.frmMayorista.btnBuscar.addActionListener(this);
        this.frmMayorista.btnEliminar.addActionListener(this);
        this.frmMayorista.btnSalir.addActionListener(this);
        
       
    }
    
    public void Iniciar () {
        //frmMayorista.setVisible(true);
        //frmMayorista.setLocationRelativeTo(null);
        LlenadoDeId(incremento);
        Listar(frmMayorista.tableMayorista);
        PlaceHolder();
    }
    
   
    public void PlaceHolder () {
        TextPrompt placeHolder = new TextPrompt("Nombre", frmMayorista.txtNombreEmpleado);
        TextPrompt placeHolder2 = new TextPrompt("Apellido paterno", frmMayorista.txtApellidoPaternoEmpleado);
        TextPrompt placeHolder3 = new TextPrompt("Apellido materno", frmMayorista.txtApellidoMaternoEmpleado);
        TextPrompt placeHolder4 = new TextPrompt("Calle", frmMayorista.txtCalle);
        TextPrompt placeHolder5 = new TextPrompt("Colonia", frmMayorista.txtColonia);
        TextPrompt placeHolder6 = new TextPrompt("Numero telefonico", frmMayorista.txtNumeroTelefonicoEmpleado);
        TextPrompt placeHolder7 = new TextPrompt("Precio", frmMayorista.txtPrecio);
    }
    
    public void LlenadoDeId(int incremento){
        //String folio=modCR.mostrarFolio();   
        //int incremento=0;//=Integer.parseInt(folio);
        incremento=incremento+1;
        frmMayorista.txtIdEmpleado.setText("0"+incremento);
    }
    
    public void Listar (JTable tabla) {
        modeloMayorista = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloMayorista);
        
        List<Mayorista>lista = modCMayorista.MostrarMayoristas();
        
        Object [] obj = new Object[5];
        
        for (int i = 0; i < lista.size(); i++) {
            obj[0] = lista.get(i).getIdMayorista();
            obj[1] = lista.get(i).getNombre();
            obj[2] = lista.get(i).getApellidoPaterno();
            obj[3] = lista.get(i).getApellidoMaterno();
            obj[4] = lista.get(i).getPrecio();
            
            modeloMayorista.addRow(obj);
        }
        
        frmMayorista.tableMayorista.setModel(modeloMayorista);
    }
    
    public static boolean validarNumeros(String datos){
        return datos.matches("[0.00-9.99]*");
    }
    
    public static boolean validarNumerosEnteros(String datos){
        return datos.matches("[0-9]{1,11}");
    }
    
    public void LimpiarCampos () {
        frmMayorista.txtNombreEmpleado.setText(null);
        frmMayorista.txtApellidoPaternoEmpleado.setText(null);
        frmMayorista.txtApellidoMaternoEmpleado.setText(null);
        frmMayorista.txtCalle.setText(null);
        frmMayorista.txtColonia.setText(null);
        
        frmMayorista.txtNumeroTelefonicoEmpleado.setText(null);
        frmMayorista.txtPrecio.setText(null);
    }
    
    public void LimpiarTabla () {
        int i;
        
        for (i = modeloMayorista .getRowCount() - 1; i >= 0; i--) {
            modeloMayorista.removeRow(i);
        }
    }
    
   
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource() == frmMayorista.btnAgregarV) {
            /*if (!frmEmpleado.checkPlanta.isSelected() || !frmEmpleado.checkRepartidor.isSelected()) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione el tipo de empleado");
            } else*/ if (frmMayorista.txtNombreEmpleado.getText().equals("") || frmMayorista.txtApellidoPaternoEmpleado.getText().equals("") || frmMayorista.txtCalle.getText().equals("") || frmMayorista.txtColonia.getText().equals("") ||
            frmMayorista.txtNumeroTelefonicoEmpleado.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
            }else {
                if (!validarNumerosEnteros(frmMayorista.txtNumeroTelefonicoEmpleado.getText().trim()) || !validarNumeros(frmMayorista.txtPrecio.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
                }else {
                    modMayorista.setIdMayorista(frmMayorista.txtIdEmpleado.getText());
                    modMayorista.setNombre(frmMayorista.txtNombreEmpleado.getText());
                    modMayorista.setApellidoPaterno(frmMayorista.txtApellidoPaternoEmpleado.getText());
                    modMayorista.setApellidoMaterno(frmMayorista.txtApellidoMaternoEmpleado.getText());
                    modMayorista.setCalle(frmMayorista.txtCalle.getText());
                    modMayorista.setColonia(frmMayorista.txtColonia.getText());
                    modMayorista.setPrecio(Float.parseFloat(frmMayorista.txtPrecio.getText()));
                    
                    modTelefonoMayorista.setIdMayorista(frmMayorista.txtIdEmpleado.getText());
                    modTelefonoMayorista.setTelefono(frmMayorista.txtNumeroTelefonicoEmpleado.getText());
                    
                    if (modCMayorista.RegistroMayorista(modMayorista) && modCMayorista.RegistrarTelefono(modTelefonoMayorista)){
                           
                        JOptionPane.showMessageDialog(null, "Registro guardado");
                        LimpiarCampos();
                        incremento++;
                        LlenadoDeId(incremento);
                        LimpiarTabla();
                        Listar(frmMayorista.tableMayorista);
                        //BloquearCampos();
                    }else {
                        JOptionPane.showMessageDialog(null, "El registro no pudo ser guardado.");
                    }
                }
            }
        }else if(e.getSource() == frmMayorista.btnEditar) {
            if (!validarNumerosEnteros(frmMayorista.txtNumeroTelefonicoEmpleado.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese numeros enteros");
            }else {
                modMayorista.setIdMayorista(frmMayorista.txtIdEmpleado.getText());
                modMayorista.setNombre(frmMayorista.txtNombreEmpleado.getText());
                modMayorista.setApellidoPaterno(frmMayorista.txtApellidoPaternoEmpleado.getText());
                modMayorista.setApellidoMaterno(frmMayorista.txtApellidoMaternoEmpleado.getText());
                modMayorista.setCalle(frmMayorista.txtCalle.getText());
                modMayorista.setColonia(frmMayorista.txtColonia.getText());
                modMayorista.setPrecio(Float.parseFloat(frmMayorista.txtPrecio.getText()));
                //modTelefonoEmpleado.setTelefono(frmEmpleado.txtNumeroTelefonicoEmpleado.getText());
                    
                if (modCMayorista.ModificarMayorista(modMayorista)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado");
                    LimpiarCampos();
                    LlenadoDeId(incremento);
                    LimpiarTabla();
                    Listar(frmMayorista.tableMayorista);
                    //BloquearCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "El registro no se pudo modificar");
                }
            }
        
        }else if (e.getSource() == frmMayorista.btnEliminar) {
            int uno = JOptionPane.showConfirmDialog(frmMayorista, "¿Deseas eliminar el cliente?", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            
            int fila = frmMayorista.tableMayorista.getSelectedRow();
            
            if (uno == JOptionPane.YES_OPTION) {
                if (fila == -1) {
                    //JOptionPane.showMessageDialog(frmMayorista, "Cancelacion de eliminacion exitosa","Sistema",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario.");
                }else {
                    String id = (String) frmMayorista.tableMayorista.getValueAt(fila, 0).toString();
                    modCMayorista.BorrarMayorista(id);
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");

                    LimpiarCampos();
                    LimpiarTabla();
                    Listar(frmMayorista.tableMayorista);
                }
            }
            
            
        }else if (e.getSource() == frmMayorista.btnBuscar) {
            String idJOptionEmpleado = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
            modMayorista.setIdMayorista(idJOptionEmpleado);
                
            if (modCMayorista.BuscarMayorista(modMayorista, modTelefonoMayorista)) {
                frmMayorista.txtIdEmpleado.setText(modMayorista.getIdMayorista());
                frmMayorista.txtNombreEmpleado.setText(modMayorista.getNombre());
                frmMayorista.txtApellidoPaternoEmpleado.setText(modMayorista.getApellidoPaterno());
                frmMayorista.txtApellidoMaternoEmpleado.setText(modMayorista.getApellidoMaterno());
                frmMayorista.txtCalle.setText(modMayorista.getCalle());
                frmMayorista.txtColonia.setText(modMayorista.getColonia());
                frmMayorista.txtPrecio.setText(String.valueOf(modMayorista.getPrecio()));


                frmMayorista.txtNumeroTelefonicoEmpleado.setText(modTelefonoMayorista.getTelefono());
                

                //BloquearCampos();
                JOptionPane.showMessageDialog(null, "Registro encontrado");
                    
            
            }else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
            }
        } else if (e.getSource() == frmMayorista.btnSalir) {
            frmMayorista.dispose();
        }
    }       
}

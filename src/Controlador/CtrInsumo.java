package Controlador;

import Modelo.ConsultaInsumo;
import PlaceHolder.TextPrompt;
import Vistas.VistaInsumos;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CtrInsumo implements ActionListener {
    private Insumo modInsumo;
    
    private ConsultaInsumo modCInsumo;
    private VistaInsumos frmInsumo;
    
    private int incremento;
    
    private DefaultTableModel modeloInsumo = new DefaultTableModel();
    
    public CtrInsumo (Insumo modInsumo, ConsultaInsumo modCInsumo, VistaInsumos frmInsumo) {
        this.modInsumo = modInsumo;
        this.modCInsumo = modCInsumo;
        this.frmInsumo = frmInsumo;
        
        this.frmInsumo.btnAgregar.addActionListener(this);
        this.frmInsumo.btnBuscar.addActionListener(this);
        this.frmInsumo.btnEditar.addActionListener(this);
        this.frmInsumo.btnEliminar.addActionListener(this);
        this.frmInsumo.btnSalir.addActionListener(this);
    }
    
    public void Iniciar () {
        //frmMayorista.setVisible(true);
        //frmMayorista.setLocationRelativeTo(null);
        //LlenadoDeId(incremento);
        Listar(frmInsumo.tableInsumos);
        PlaceHolder();
    }
    
    public void PlaceHolder () {
        TextPrompt placeHolder = new TextPrompt("Nombre", frmInsumo.txtNombreInsumo);
        TextPrompt placeHolder2 = new TextPrompt("Cantidad", frmInsumo.txtCantidadInsumo);
        TextPrompt placeHolder3 = new TextPrompt("Descripcion", frmInsumo.txtDescripcionInsumo);
        TextPrompt placeHolder4 = new TextPrompt("Precio", frmInsumo.txtPrecioInsumo);
    }
    
    /*public void LlenadoDeId(int incremento){
        //String folio=modCR.mostrarFolio();   
        //int incremento=0;//=Integer.parseInt(folio);
        incremento=incremento+1;
        frmInsumo.txtIdInsumo.setText("in"+incremento);
    }*/
    
    public void Listar (JTable tabla) {
        modeloInsumo = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloInsumo);
        
        List<Insumo>lista = modCInsumo.MostrarInsumo();
        
        Object [] obj = new Object[6];
        
        for (int i = 0; i < lista.size(); i++) {
            obj[0] = lista.get(i).getIdInsumo();
            obj[1] = lista.get(i).getNombre();
            obj[2] = lista.get(i).getCantidad();
            obj[3] = lista.get(i).getDescripcion();
            obj[4] = lista.get(i).getPrecio();
            
            modeloInsumo.addRow(obj);
        }
        frmInsumo.tableInsumos.setModel(modeloInsumo);
    }
    
    public static boolean validarNumeros(String datos){
        return datos.matches("[0.00-9.99]*");
    }
    
    public static boolean validarNumerosEnteros(String datos){
        return datos.matches("[0-9]{1,11}");
    }
    
    public void LimpiarCampos () {
        frmInsumo.txtNombreInsumo.setText(null);
        frmInsumo.txtCantidadInsumo.setText(null);
        frmInsumo.txtDescripcionInsumo.setText(null);
        frmInsumo.txtPrecioInsumo.setText(null);
    }
    
    public void LimpiarTabla () {
        int i;
        
        for (i = modeloInsumo .getRowCount() - 1; i >= 0; i--) {
            modeloInsumo.removeRow(i);
        }
    }
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource() == frmInsumo.btnAgregar) {
            if (frmInsumo.txtNombreInsumo.getText().equals("") || frmInsumo.txtCantidadInsumo.getText().equals("") || frmInsumo.txtDescripcionInsumo.getText().equals("") || frmInsumo.txtPrecioInsumo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
            }else {
                if (!validarNumeros(frmInsumo.txtPrecioInsumo.getText().trim()) || !validarNumerosEnteros(frmInsumo.txtCantidadInsumo.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
                }else {
                    //modInsumo.setIdInsumo(frmInsumo.txtIdInsumo.getText());
                    modInsumo.setNombre(frmInsumo.txtNombreInsumo.getText());
                    modInsumo.setCantidad(Integer.parseInt(frmInsumo.txtCantidadInsumo.getText()));
                    modInsumo.setDescripcion(frmInsumo.txtDescripcionInsumo.getText());
                    modInsumo.setPrecio(Float.parseFloat(frmInsumo.txtPrecioInsumo.getText()));
                    
                    if (modCInsumo.RegistroInsumo(modInsumo)){
                            
                        JOptionPane.showMessageDialog(null, "Registro guardado");
                        LimpiarCampos();
                        incremento++;
                        //LlenadoDeId(incremento);
                        LimpiarTabla();
                        Listar(frmInsumo.tableInsumos);
                        
                    }else {
                        JOptionPane.showMessageDialog(null, "El registro no pudo ser guardado.");
                    }
                }
            }
        } else if(e.getSource() == frmInsumo.btnEditar) {
            if (!validarNumeros(frmInsumo.txtPrecioInsumo.getText().trim()) || !validarNumerosEnteros(frmInsumo.txtCantidadInsumo.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
            }else {
                //modInsumo.setIdInsumo(frmInsumo.txtIdProducto.getText());
                modInsumo.setNombre(frmInsumo.txtNombreInsumo.getText());
                modInsumo.setCantidad(Integer.parseInt(frmInsumo.txtCantidadInsumo.getText()));
                modInsumo.setDescripcion(frmInsumo.txtDescripcionInsumo.getText());
                modInsumo.setPrecio(Float.parseFloat(frmInsumo.txtPrecioInsumo.getText()));
                

                if (modCInsumo.ModificarInsumo(modInsumo)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado");
                    LimpiarCampos();
                    //LlenadoDeId(incremento);
                    LimpiarTabla();
                    Listar(frmInsumo.tableInsumos);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "El registro no se pudo modificar");
                }
            }
        
        }else if (e.getSource() == frmInsumo.btnEliminar) {
            int uno = JOptionPane.showConfirmDialog(frmInsumo, "¿Deseas eliminar el insumo?", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            
            int fila = frmInsumo.tableInsumos.getSelectedRow();
            
            if (uno == JOptionPane.YES_OPTION) {
                if (fila == -1) {
                    //JOptionPane.showMessageDialog(frmMayorista, "Cancelacion de eliminacion exitosa","Sistema",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un insumo.");
                }else {
                    String id = (String) frmInsumo.tableInsumos.getValueAt(fila, 0).toString();
                    modCInsumo.BorrarInsumo(id);
                    JOptionPane.showMessageDialog(null, "Insumo eliminado");

                    LimpiarCampos();
                    LimpiarTabla();
                    Listar(frmInsumo.tableInsumos);
                }
            }
            
            
        }else if (e.getSource() == frmInsumo.btnBuscar) {
            String idJOptionEmpleado = JOptionPane.showInputDialog("Ingrese el ID del insumo:");
            modInsumo.setIdInsumo(idJOptionEmpleado);
                
            if (modCInsumo.BuscarInsumo(modInsumo)) {
                //frmProducto.txtIdProducto.setText(modProducto.getIdProducto());
                frmInsumo.txtNombreInsumo.setText(modInsumo.getNombre());
                frmInsumo.txtCantidadInsumo.setText(String.valueOf(modInsumo.getCantidad()));
                frmInsumo.txtDescripcionInsumo.setText(modInsumo.getDescripcion());
                frmInsumo.txtPrecioInsumo.setText(String.valueOf(modInsumo.getPrecio()));
                

                JOptionPane.showMessageDialog(null, "Registro encontrado");
                    
            }else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
            }
        } else if (e.getSource() == frmInsumo.btnSalir) {
            frmInsumo.dispose();
        }
        
    }
    
    
    
    
}

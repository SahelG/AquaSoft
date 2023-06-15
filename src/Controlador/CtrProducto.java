package Controlador;

import Modelo.ConsultaProducto;
import PlaceHolder.TextPrompt;
import Vistas.VistaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrProducto implements ActionListener {
    private Producto modProducto;
    private ProductoExterno modExterno;
    private ProductoInterno modInterno;
    
    private ConsultaProducto modCProducto;
    private VistaProducto frmProducto;
    private int incremento;
    
    private DefaultTableModel modeloEmpleado = new DefaultTableModel();
    
    public CtrProducto (Producto modProducto, ProductoExterno modExterno, ProductoInterno modInterno, ConsultaProducto modCProducto, VistaProducto frmProducto) {
        this.modProducto = modProducto;
        this.modExterno = modExterno;
        this.modInterno = modInterno;
        
        
        this.modCProducto = modCProducto;
        this.frmProducto = frmProducto;
        
        this.frmProducto.btnAgregar.addActionListener(this);
        this.frmProducto.btnBuscar.addActionListener(this);
        this.frmProducto.btnEditar.addActionListener(this);
        this.frmProducto.btnEliminar.addActionListener(this);
        
        this.frmProducto.rbtnExternos.addActionListener(this);
        this.frmProducto.rbtnInternos.addActionListener(this);
        this.frmProducto.btnSalir.addActionListener(this);
    }
    
    public void Iniciar () {
        //frmMayorista.setVisible(true);
        //frmMayorista.setLocationRelativeTo(null);
        LlenadoDeId(incremento);
        Listar(frmProducto.tableProductos);
        PlaceHolder();
    }
    
    public void PlaceHolder () {
        TextPrompt placeHolder = new TextPrompt("Nombre", frmProducto.txtNombreProducto);
        TextPrompt placeHolder2 = new TextPrompt("Descripcion", frmProducto.txtDescripcionProducto);
        TextPrompt placeHolder3 = new TextPrompt("Precio", frmProducto.txtPrecioProducto);
        
        TextPrompt placeHolder4 = new TextPrompt("Nombre", frmProducto.txtNombreProducto);
        TextPrompt placeHolder5 = new TextPrompt("Descripcion", frmProducto.txtDescripcionProducto);
        TextPrompt placeHolder6 = new TextPrompt("Precio", frmProducto.txtPrecioProducto);
    }
    
    public void LlenadoDeId(int incremento){
        //String folio=modCR.mostrarFolio();   
        //int incremento=0;//=Integer.parseInt(folio);
        incremento=incremento+1;
        frmProducto.txtIdProducto.setText("p"+incremento);
    }
    
    public void Listar (JTable tabla) {
        modeloEmpleado = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloEmpleado);
        
        List<Producto>lista = modCProducto.MostrarProductos();
        
        Object [] obj = new Object[5];
        
        for (int i = 0; i < lista.size(); i++) {
            obj[0] = lista.get(i).getIdProducto();
            obj[1] = lista.get(i).getNombre();
            obj[2] = lista.get(i).getDescripcion();
            obj[3] = lista.get(i).getPrecio();
            
            modeloEmpleado.addRow(obj);
        }
        frmProducto.tableProductos.setModel(modeloEmpleado);
    }
    
    public static boolean validarNumeros(String datos){
        return datos.matches("[0.00-9.99]*");
    }
    
    public static boolean validarNumerosEnteros(String datos){
        return datos.matches("[0-9]{1,11}");
    }
    
    public void LimpiarCampos () {
        frmProducto.txtNombreProducto.setText(null);
        frmProducto.txtDescripcionProducto.setText(null);
        frmProducto.txtPrecioProducto.setText(null);
        
        frmProducto.txtNombreProductoExterno.setText(null);
        frmProducto.txtDescripcionProductoExterno.setText(null);
        frmProducto.txtPrecioExterno.setText(null);
        
    }
    
    public void LimpiarTabla () {
        int i;
        
        for (i = modeloEmpleado .getRowCount() - 1; i >= 0; i--) {
            modeloEmpleado.removeRow(i);
        }
    }
    
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource() == frmProducto.btnAgregar) {
            if (frmProducto.txtNombreProducto.getText().equals("") || frmProducto.txtDescripcionProducto.getText().equals("") || frmProducto.txtPrecioProducto.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
            }else {
                if (!validarNumeros(frmProducto.txtPrecioProducto.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
                }else {
                    modProducto.setIdProducto(frmProducto.txtIdProducto.getText());
                    modProducto.setNombre(frmProducto.txtNombreProducto.getText());
                    modProducto.setDescripcion(frmProducto.txtDescripcionProducto.getText());
                    modProducto.setPrecio(Float.parseFloat(frmProducto.txtPrecioProducto.getText()));
                    
                    if (modCProducto.RegistroProducto(modProducto)){
                            
                        if (frmProducto.rbtnInternos.isSelected()) {
                            modInterno.setIdProducto(frmProducto.txtIdProducto.getText());
                                
                            if (modCProducto.RegistroProductoInterno(modInterno)) {}
                            
                        }else if (frmProducto.rbtnExternos.isSelected()) {
                            modExterno.setIdProducto(frmProducto.txtIdProducto.getText());
                                
                            if (modCProducto.RegistroProductoExterno(modExterno)) {}
                        }
                        
                        JOptionPane.showMessageDialog(null, "Registro guardado");
                        LimpiarCampos();
                        incremento++;
                        LlenadoDeId(incremento);
                        LimpiarTabla();
                        Listar(frmProducto.tableProductos);
                        frmProducto.buttonGroup1.clearSelection();
                    }else {
                        JOptionPane.showMessageDialog(null, "El registro no pudo ser guardado.");
                    }
                }
            }
        } else if(e.getSource() == frmProducto.btnEditar) {
            if (!validarNumeros(frmProducto.txtPrecioProducto.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese numeros");
            }else {
                modProducto.setIdProducto(frmProducto.txtIdProducto.getText());
                modProducto.setNombre(frmProducto.txtNombreProducto.getText());
                modProducto.setDescripcion(frmProducto.txtDescripcionProducto.getText());
                modProducto.setPrecio(Float.parseFloat(frmProducto.txtPrecioProducto.getText()));
                

                if (modCProducto.ModificarProducto(modProducto)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado");
                    LimpiarCampos();
                    LlenadoDeId(incremento);
                    LimpiarTabla();
                    Listar(frmProducto.tableProductos);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "El registro no se pudo modificar");
                }
            }
        
        }else if (e.getSource() == frmProducto.btnEliminar) {
            int uno = JOptionPane.showConfirmDialog(frmProducto, "¿Deseas eliminar el producto?", "Sistema", JOptionPane.INFORMATION_MESSAGE);
            
            int fila = frmProducto.tableProductos.getSelectedRow();
            
            if (uno == JOptionPane.YES_OPTION) {
                if (fila == -1) {
                    //JOptionPane.showMessageDialog(frmMayorista, "Cancelacion de eliminacion exitosa","Sistema",JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un producto.");
                }else {
                    String id = (String) frmProducto.tableProductos.getValueAt(fila, 0).toString();
                    modCProducto.BorrarProducto(id);
                    JOptionPane.showMessageDialog(null, "Producto eliminado");

                    LimpiarCampos();
                    LimpiarTabla();
                    Listar(frmProducto.tableProductos);
                }
            }
            
            
        }else if (e.getSource() == frmProducto.btnBuscar) {
            String idJOptionEmpleado = JOptionPane.showInputDialog("Ingrese el ID del producto:");
            modProducto.setIdProducto(idJOptionEmpleado);
                
            if (modCProducto.BuscarProducto(modProducto)) {
                frmProducto.txtIdProducto.setText(modProducto.getIdProducto());
                frmProducto.txtNombreProducto.setText(modProducto.getNombre());
                frmProducto.txtDescripcionProducto.setText(modProducto.getDescripcion());
                frmProducto.txtPrecioProducto.setText(String.valueOf(modProducto.getPrecio()));
                

                JOptionPane.showMessageDialog(null, "Registro encontrado");
                    
            }else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
            }
        } else if (e.getSource() == frmProducto.btnSalir) {
            frmProducto.dispose();
        }
        
    }
    
}

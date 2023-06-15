package Controlador;

import Modelo.ConsultaMayorista;
import Modelo.ConsultaProducto;
import Modelo.ConsultaVenta;
import PlaceHolder.TextPrompt;
import Vistas.VistaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CtrlVenta implements ActionListener {
    private VistaVenta frmVenta;
    
    private Venta modVenta;
    private Producto modProductos;
    private ConsultaProducto modCProducto;
    private ConsultaVenta modCVenta;
    
    private Mayorista modMayorista = new Mayorista();
    private ConsultaMayorista modCMayorista = new ConsultaMayorista();
    private TelefonoEmpleado modTelefono = new TelefonoEmpleado();
    
    private DefaultTableModel modeloProducto = new DefaultTableModel();
    private DefaultTableModel modeloVenta = new DefaultTableModel();
    private DefaultTableModel modeloDetalles = new DefaultTableModel();
    private DefaultTableModel modeloMayorista = new DefaultTableModel();
    private DefaultTableModel modeloVentaRepartidor = new DefaultTableModel();
    
    private Empleado modEmpleado = new Empleado();
    
    private float total = 0;
    private int incremento;
    
    private VentaProducto modProductoVenta = new VentaProducto();
    
    
    public CtrlVenta (VistaVenta frmVenta, Venta modVenta, ConsultaVenta modCVenta, Producto modProductos, ConsultaProducto modCProducto) {
        this.frmVenta = frmVenta;
        this.modProductos = modProductos;
        this.modVenta = modVenta;
        this.modCVenta = modCVenta;
        this.modCProducto = modCProducto;
        
        this.frmVenta.btnAgregar.addActionListener(this);
        this.frmVenta.btnAgregarProductos.addActionListener(this);
        //this.frmVenta.btnBuscar.addActionListener(this);
        //this.frmVenta.btnBorrar.addActionListener(this);
        //this.frmVenta.btnEditar.addActionListener(this);
        this.frmVenta.btnSalirProductos2.addActionListener(this);
        this.frmVenta.btnProducto.addActionListener(this);
        this.frmVenta.btnRepartidor.addActionListener(this);
        
        this.frmVenta.btnAgregarID.addActionListener(this);
        
        
        this.frmVenta.btnCalcularTotal.addActionListener(this);
        this.frmVenta.btnBuscadorClientes.addActionListener(this);
        
        this.frmVenta.btnAgregarSubVentas.addActionListener(this);
        this.frmVenta.btnAgregarVenta.addActionListener(this);
        this.frmVenta.btnBuscadorEmpleado.addActionListener(this);
        this.frmVenta.btnCalcularTotalGarrafones.addActionListener(this);
        this.frmVenta.btnVolver.addActionListener(this);
        this.frmVenta.btnSalir.addActionListener(this);
        
    }
    
    public void Iniciar () {
        Listar(frmVenta.tablaProductos2);
        Listar2(frmVenta.tableVenta);
        Listar3(frmVenta.tablaMayoristas);
        
        frmVenta.lblFechaVenta.setText(fechaActual());
        
        LlenadoDeId(incremento);
        BloquearCampos();
        PlaceHolder();
    }
    
    public void PlaceHolder () {
        TextPrompt placeHolder = new TextPrompt("Nombre", frmVenta.txtNombreCliente);
        TextPrompt placeHolder2 = new TextPrompt("Apellido paterno", frmVenta.txtApellidoPaternoCliente);
        TextPrompt placeHolder3 = new TextPrompt("Precio especial", frmVenta.txtPrecioEspecial);
        TextPrompt placeHolder4 = new TextPrompt("Nombre", frmVenta.txtNombreEmpleado);
        TextPrompt placeHolder5 = new TextPrompt("Numero telefonico", frmVenta.txtTelefonoRepartidor);
        TextPrompt placeHolder6 = new TextPrompt("Apellido paterno", frmVenta.txtApellidoPaternoRepartidor);
        TextPrompt placeHolder7 = new TextPrompt("Apellido materno", frmVenta.txtApellidoMaternoRepartidor);
        TextPrompt placeHolder8 = new TextPrompt("Numero de garrafones solicitados", frmVenta.txtCantidadGarrafonesSolicitados);
        TextPrompt placeHolder9 = new TextPrompt("Numero de garrafones devueltos", frmVenta.txtCantidadGarrafonesDevueltos);
        TextPrompt placeHolder10 = new TextPrompt("Garrafones vendidos", frmVenta.txtCantidadGarrafonesVendidos);
        TextPrompt placeHolder11 = new TextPrompt("Cantidad garrafones", frmVenta.txtCantidadGarrafones);
        TextPrompt placeHolder12 = new TextPrompt("Total venta", frmVenta.txtTotalVenta);
    }
    
    public static String fechaActual(){
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
        
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/YYYY");
        return formatoFecha.format(fecha);
        //return dtf.format(LocalDateTime.now());
    }
    
    public void LlenadoDeId(int incremento){
        //String folio=modCR.mostrarFolio();   
        //int incremento=0;//=Integer.parseInt(folio);
        incremento=incremento+1;
        frmVenta.txtIdVenta.setText("ve-" + incremento);
    }
    
    public void Listar (JTable tabla) {
        modeloProducto = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloProducto);
        
        List<Producto>lista = modCProducto.MostrarProductos();
        Object [] obj = new Object[4];
        
        for (int i = 0; i < lista.size(); i++) {
            obj[0] = lista.get(i).getIdProducto();
            obj[1] = lista.get(i).getNombre();
            obj[2] = lista.get(i).getDescripcion();
            obj[3] = lista.get(i).getPrecio();
            
            modeloProducto.addRow(obj);
        }
        frmVenta.tablaProductos2.setModel(modeloProducto);
        
    }
    
    public void Listar2 (JTable tabla) {
        modeloDetalles = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloDetalles);
        
        List<Venta>lista2 = modCVenta.MostrarVentas();
        Object [] obj2 = new Object[4];
        
        for (int i = 0; i < lista2.size(); i++) {
            obj2[0] = lista2.get(i).getIdVenta();
            obj2[1] = lista2.get(i).getTotal();
            obj2[2] = lista2.get(i).getFecha();
            
            modeloDetalles.addRow(obj2);
        }
        frmVenta.tableVenta.setModel(modeloDetalles);
    }
    
    public void Listar3 (JTable tabla) {
        modeloMayorista = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modeloMayorista);
        
        List<Mayorista>lista2 = modCMayorista.MostrarMayoristas();
        Object [] obj2 = new Object[7];
        
        for (int i = 0; i < lista2.size(); i++) {
            obj2[0] = lista2.get(i).getIdMayorista();
            obj2[1] = lista2.get(i).getNombre();
            obj2[2] = lista2.get(i).getApellidoPaterno();
            obj2[3] = lista2.get(i).getApellidoMaterno();
            obj2[4] = lista2.get(i).getPrecio();
            
            modeloMayorista.addRow(obj2);
        }
        frmVenta.tablaMayoristas.setModel(modeloMayorista);
    }
    
    public static boolean validarNumeros(String datos){
        return datos.matches("[0.00-9.99]*");
    }
    
    public static boolean validarNumerosEnteros(String datos){
        return datos.matches("[0-9]{1,11}");
    }
    
    public void LimpiarCampos () {
        int i, j;
        //frmPedido.txtIdPedido.setText(null);
        frmVenta.txtIdMayorista.setText(null);
        frmVenta.txtApellidoPaternoCliente.setText(null);
        frmVenta.txtNombreCliente.setText(null);
        frmVenta.txtPrecioEspecial.setText(null);
        frmVenta.txtTotal.setText(null);
        frmVenta.txtCambio.setText(null);
        
        for (i = modeloVenta.getRowCount() - 1; i >= 0; i--) {
            modeloVenta.removeRow(i);
        }
        
        frmVenta.txtNombreEmpleado.setText(null);
        frmVenta.txtApellidoPaternoRepartidor.setText(null);
        frmVenta.txtApellidoMaternoRepartidor.setText(null);
        frmVenta.txtCantidadGarrafonesSolicitados.setText(null);
        frmVenta.txtCantidadGarrafonesDevueltos.setText(null);
        frmVenta.txtCantidadGarrafonesVendidos.setText(null);
        frmVenta.txtCantidadGarrafones.setText(null);
        
        frmVenta.txtTotalVenta.setText(null);
        
        
        for (j = modeloVentaRepartidor.getRowCount() - 1; j >= 0; j--) {
            modeloVentaRepartidor.removeRow(j);
        }
    }
    
    public void PasarDatos (int fila) {
        modeloProducto = (DefaultTableModel)frmVenta.tablaProductos2.getModel();
        String idProducto, nombre, cantidad, precio, subtotal;
        float calculoSubTotal;
        
        if(!validarNumerosEnteros(frmVenta.txtCantidad2.getText().trim())) {
            JOptionPane.showMessageDialog(null, "Ingrese numeros enteros");
        }
                
        idProducto = frmVenta.tablaProductos2.getValueAt(fila, 0).toString();
        nombre = frmVenta.tablaProductos2.getValueAt(fila, 1).toString();
        cantidad = frmVenta.txtCantidad2.getText();
        precio = frmVenta.tablaProductos2.getValueAt(fila, 3).toString();        
        
        if (fila == 0) {
            precio = String.valueOf(PrecioEspecialMayorista());
        }
        
        calculoSubTotal = (Float.parseFloat(precio) * Integer.parseInt(cantidad));
        subtotal = String.valueOf(calculoSubTotal);
        total = total + calculoSubTotal;
        frmVenta.txtTotal.setText("$" + total);
        
        modeloVenta = (DefaultTableModel)frmVenta.tablaRegistroProductos.getModel();     
        String datos[] = {idProducto, nombre, cantidad, precio, subtotal};
        modeloVenta.addRow(datos);
        
    }
    
    public void PasarIDMayorista (int fila) {
        modeloMayorista = (DefaultTableModel)frmVenta.tablaMayoristas.getModel();
        String idMayorista;
        
        idMayorista = frmVenta.tablaMayoristas.getValueAt(fila, 0).toString();
        
        frmVenta.txtIdMayorista.setText(idMayorista);
    }
    
    public double PrecioEspecialMayorista () {
        modMayorista.setIdMayorista(frmVenta.txtIdMayorista.getText());
        double precioCliente = 0;
        
        if (modCVenta.BuscarPrecioMayorista(modMayorista)) {
            frmVenta.txtNombreCliente.setText(modMayorista.getNombre());
            frmVenta.txtApellidoPaternoCliente.setText(modMayorista.getApellidoPaterno());
            frmVenta.txtPrecioEspecial.setText(String.valueOf(modMayorista.getPrecio()));
            
            precioCliente = modMayorista.getPrecio();
        }
        
        return precioCliente;
    }
    
    public void BloquearCampos () {
        //frmVenta.lblFechaVenta.setEditable(false);
        frmVenta.txtIdVenta.setEditable(false);
        frmVenta.txtIdEmpleado.setEditable(false);
        frmVenta.txtTotal.setEditable(false);
        frmVenta.txtCambio.setEditable(false);
    }
    
    public void BloquerCampos2 () {
        frmVenta.txtIdMayorista.setEditable(false);
        frmVenta.txtNombreCliente.setEditable(false);
        frmVenta.txtApellidoPaternoCliente.setEditable(false);
        frmVenta.txtPrecioEspecial.setEditable(false);
        
        
    }
    
    public float CalcularCambio () {
        float dineroRecibido, cambio=0;
        
        dineroRecibido = Float.parseFloat(JOptionPane.showInputDialog(null, "Dinero recibido: "));
        
        while (dineroRecibido < total) {
            JOptionPane.showMessageDialog(null, "Dinero insuficiente, favor de ingresar otra cantidad.");
            dineroRecibido = Float.parseFloat(JOptionPane.showInputDialog(null, "Dinero recibido: "));
        }
        return cambio = dineroRecibido - total;
    }
    
    //--------------------------------------------------------------------------------------------------------
    public void CalcularSubtotales () {
        String cantidad, precio, subtotal;
        float calcularSubTotal;
        
        cantidad = frmVenta.txtCantidadGarrafones.getText();
        precio = frmVenta.cbPrecios.getSelectedItem().toString();
        
        calcularSubTotal = (Float.parseFloat(precio) * Integer.parseInt(cantidad));
        
        subtotal = String.valueOf(calcularSubTotal);
        
        total = total + calcularSubTotal;
        frmVenta.txtTotalVenta.setText("$" + total);
        
        modeloVentaRepartidor = (DefaultTableModel)frmVenta.tablaRegistroProductosRepartidor.getModel();     
        String datos[] = {precio, cantidad, subtotal};
        modeloVentaRepartidor.addRow(datos);
    }
    
    public void CalcularResta () {
        String cantidadGarrafonesSolicitados, cantidadGarrafonesDevueltos;
        int cantidadGarrafonesVendidos;
        
        cantidadGarrafonesSolicitados = frmVenta.txtCantidadGarrafonesSolicitados.getText();
        cantidadGarrafonesDevueltos = frmVenta.txtCantidadGarrafonesDevueltos.getText();
        cantidadGarrafonesVendidos = Integer.parseInt(cantidadGarrafonesSolicitados) - Integer.parseInt(cantidadGarrafonesDevueltos);
        frmVenta.txtCantidadGarrafonesVendidos.setText("" + cantidadGarrafonesVendidos);
        
        frmVenta.txtCantidadGarrafonesSolicitados.setEditable(false);
        frmVenta.txtCantidadGarrafonesDevueltos.setEditable(false);
        frmVenta.txtCantidadGarrafonesVendidos.setEditable(false);
    }
    
    public float CalcularCambioRepartidor () {
        float dineroRecibido, cambio=0;
        
        dineroRecibido = Float.parseFloat(JOptionPane.showInputDialog(null, "Dinero recibido: "));
        
        while (dineroRecibido < total) {
            JOptionPane.showMessageDialog(null, "Dinero insuficiente, favor de ingresar otra cantidad.");
            dineroRecibido = Float.parseFloat(JOptionPane.showInputDialog(null, "Dinero recibido: "));
        }
        return cambio = dineroRecibido - total;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------
    
    
    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == frmVenta.btnProducto) {
            frmVenta.jDialog3.setSize(750, 460);
            frmVenta.jDialog3.setVisible(true);
            
        }else if (e.getSource() == frmVenta.btnAgregarProductos) {
            int fila = frmVenta.tablaProductos2.getSelectedRow();
            
            if (frmVenta.txtCantidad2.getText().equals("") || fila == -1) {
                JOptionPane.showMessageDialog(null, "Faltan campos por llenar.", "Error", JOptionPane.WARNING_MESSAGE);
            }else {
                PasarDatos(fila);
            }
            frmVenta.txtCantidad2.setText(null);
        }else if (e.getSource() == frmVenta.btnAgregar) {
            
            if (frmVenta.txtPrecioEspecial.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, presione el icono de buscar para encontrar el precio especial");
            } else {
                modVenta.setIdVenta(frmVenta.txtIdVenta.getText());
                modVenta.setIdMayorista(frmVenta.txtIdMayorista.getText());
                modVenta.setIdEmpleado(frmVenta.txtIdEmpleado.getText());

                modVenta.setTotal(total);
                modVenta.setFecha(frmVenta.lblFechaVenta.getText());
                
                if(modCVenta.RegistroVenta(modVenta)) {
                    
                    JOptionPane.showMessageDialog(null, "Venta guardado");
                    Listar2(frmVenta.tableVenta);
                    incremento++;
                    LlenadoDeId(incremento);
                    LimpiarCampos();
                    total = 0;
                
                }else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            }            
        }else if (e.getSource() == frmVenta.btnSalirProductos2) {
            frmVenta.jDialog3.dispose();
        }else if (e.getSource() == frmVenta.btnCalcularTotal) {
            frmVenta.txtCambio.setText("$ " + CalcularCambio());
        }else if (e.getSource() == frmVenta.btnBuscadorClientes) {
            
            if (frmVenta.txtIdMayorista.getText().equals("")) {
                int yes = JOptionPane.showConfirmDialog(frmVenta, "¿Deseas ver la tabla con todos los clientes??", "Sistema", JOptionPane.INFORMATION_MESSAGE);
                
                if(yes == JOptionPane.YES_OPTION){
                    frmVenta.jDialog1.setSize(750, 460);
                    frmVenta.jDialog1.setVisible(true);
                }
            }else {
                PrecioEspecialMayorista();
                JOptionPane.showMessageDialog(null, "Cliente encontrado.");
                BloquerCampos2();
            }
        } else if (e.getSource() == frmVenta.btnRepartidor) {
            frmVenta.jDialog2.setSize(964, 706);
            frmVenta.jDialog2.setVisible(true);
        } else if (e.getSource() == frmVenta.btnAgregarID) {
            int fila = frmVenta.tablaMayoristas.getSelectedRow();
            
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione una fila", "Error", JOptionPane.WARNING_MESSAGE);
            }else {
                PasarIDMayorista(fila);
                PrecioEspecialMayorista();
                JOptionPane.showMessageDialog(null, "Cliente encontrado.");
                BloquerCampos2();
            }
        } else if (e.getSource() == frmVenta.btnAgregarSubVentas) {
            if (frmVenta.txtCantidadGarrafones.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese una cantidad");
            } else {
                if (!validarNumerosEnteros(frmVenta.txtCantidadGarrafones.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una cantidad entera");
                } else {
                    CalcularSubtotales();
                    frmVenta.txtCantidadGarrafones.setText(null);
                }
            }
            
        }else if (e.getSource() == frmVenta.btnCalcularTotalGarrafones) {
            if (Integer.parseInt(frmVenta.txtCantidadGarrafonesSolicitados.getText()) < Integer.parseInt(frmVenta.txtCantidadGarrafonesDevueltos.getText())) {
                JOptionPane.showMessageDialog(null, "La cantidad de garrafones devueltos no puede ser mayor a la de los garrafones solicitados");
                frmVenta.txtCantidadGarrafonesSolicitados.setText(null);
                frmVenta.txtCantidadGarrafonesDevueltos.setText(null);
            } else {
                CalcularResta();
            }
        } else if (e.getSource() == frmVenta.btnBuscadorEmpleado) {
            if (frmVenta.txtIdRepartidor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese el id del empleado repartidor");
            } else {
                
                modEmpleado.setIdEmpleado(frmVenta.txtIdRepartidor.getText());

                if (modCVenta.BuscarDatosEmpleados(modEmpleado, modTelefono)) {
                    frmVenta.txtNombreEmpleado.setText(modEmpleado.getNombre());
                    frmVenta.txtApellidoPaternoRepartidor.setText(modEmpleado.getApellidoPaterno());
                    frmVenta.txtApellidoMaternoRepartidor.setText(modEmpleado.getApellidoMaterno());
                    frmVenta.txtTelefonoRepartidor.setText(modTelefono.getTelefono());
                    
                    JOptionPane.showMessageDialog(null, "ID de repartidor encontrado");
                    frmVenta.txtIdRepartidor.setEditable(false);
                    
                }else {
                    JOptionPane.showMessageDialog(null, "ID de repartidor no encontrado");
                }
            }
        } else if (e.getSource() == frmVenta.btnAgregarVenta) {
            
            if (frmVenta.txtIdRepartidor.getText().equals("") || frmVenta.txtTotalVenta.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Necesario llenar los campos");
            } else {
                
                modVenta.setIdVenta(frmVenta.txtIdVenta.getText());
                modVenta.setIdMayorista(frmVenta.txtIdMayorista.getText());
                modVenta.setIdEmpleado(frmVenta.txtIdRepartidor.getText());

                modVenta.setTotal(total);
                modVenta.setFecha(frmVenta.lblFechaVenta.getText());


                if(modCVenta.RegistroVenta(modVenta)) {
                    JOptionPane.showMessageDialog(null, "Venta guardado");
                    Listar2(frmVenta.tableVenta);
                    incremento++;
                    LlenadoDeId(incremento);
                    LimpiarCampos();

                }else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            }
            total = 0;
        }else if (e.getSource() == frmVenta.btnVolver) {
            frmVenta.jDialog2.dispose();
        } else if (e.getSource() == frmVenta.btnSalir) {
            frmVenta.dispose();
        }
    }
    
}

package Proyect.View;

import Proyect.Model.Producto;
import javax.swing.*;
import java.awt.*;

public class FormProducto extends JPanel {
    private boolean modoEdicion = false;
    private Producto productoExistente = null;

    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtPrecioC;
    private JComboBox<String> cmbCategoria;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel7;

    public FormProducto() {
        initComponents();
    }

    public FormProducto(Producto producto) {
        this.modoEdicion = true;
        this.productoExistente = producto;
        initComponents();
        cargarDatosProducto();
    }

    private void cargarDatosProducto() {
        txtNombre.setText(productoExistente.nombre);
        txtCantidad.setText(String.valueOf(productoExistente.cantidad));
        txtPrecio.setText(String.valueOf(productoExistente.precio));
        txtPrecioC.setText(String.valueOf(productoExistente.precioC));
        cmbCategoria.setSelectedItem(productoExistente.categoria);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(15);
        txtCantidad = new JTextField(15);
        txtPrecio = new JTextField(15);
        txtPrecioC = new JTextField(15);
        cmbCategoria = new JComboBox<>(new String[]{"General", "Galletas", "Golosinas", "Helader�a", "Miscelanea", "Panader�a", "Supermercado", "Ferreter�a"});
        jButton1 = new JButton("Guardar");
        jButton2 = new JButton("Cancelar");
        jLabel7 = new JLabel(modoEdicion ? "Editar Producto" : "Producto Nuevo", SwingConstants.CENTER);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(jLabel7, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("Nombre"), gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Categor�a"), gbc);
        gbc.gridx = 1;
        add(cmbCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cantidad"), gbc);
        gbc.gridx = 1;
        add(txtCantidad, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Precio Venta"), gbc);
        gbc.gridx = 1;
        add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Precio Compra"), gbc);
        gbc.gridx = 1;
        add(txtPrecioC, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(jButton1, gbc);
        gbc.gridx = 1;
        add(jButton2, gbc);

        jButton1.addActionListener(e -> guardarProducto());
        jButton2.addActionListener(e -> cancelar());
    }

    private void guardarProducto() {
        String nombre = txtNombre.getText().trim();
        String categoria = cmbCategoria.getSelectedItem().toString();
        String cantidadTexto = txtCantidad.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String precioCTexto = txtPrecioC.getText().trim();

        if (nombre.isEmpty() || cantidadTexto.isEmpty() || precioTexto.isEmpty() || precioCTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        int cantidad;
        double precio, precioC;

        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Cantidad debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un n�mero entero v�lido.");
            return;
        }

        try {
            precio = Double.parseDouble(precioTexto);
            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio de venta debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio de venta debe ser un n�mero v�lido.");
            return;
        }

        try {
            precioC = Double.parseDouble(precioCTexto);
            if (precioC < 0) {
                JOptionPane.showMessageDialog(this, "El precio de compra no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio de compra debe ser un n�mero v�lido.");
            return;
        }

        if (!modoEdicion) {
            for (Producto p : Proyect.Model.Inventario.getInstancia().obtenerProductos()) {
                if (p.nombre.equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(this, "Ya existe un producto con ese nombre.");
                    return;
                }
            }
            Producto nuevoProducto = new Producto(nombre, categoria, precio, precioC, cantidad);
            JOptionPane.showMessageDialog(this, "Producto registrado exitosamente.");
        } else {
            productoExistente.nombre = nombre;
            productoExistente.categoria = categoria;
            productoExistente.precio = precio;
            productoExistente.precioC = precioC;
            productoExistente.cantidad = cantidad;
            JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
        }

        javax.swing.JInternalFrame internalFrame = (javax.swing.JInternalFrame) SwingUtilities.getAncestorOfClass(javax.swing.JInternalFrame.class, this);
        if (internalFrame != null) {
            internalFrame.dispose();
        } // cerrar ventana que contiene el JPanel
    }

    private void cancelar() {
        javax.swing.JInternalFrame internalFrame = (javax.swing.JInternalFrame) SwingUtilities.getAncestorOfClass(javax.swing.JInternalFrame.class, this);
        if (internalFrame != null) {
            internalFrame.dispose();
        } // cerrar ventana que contiene el JPanel
    }
}
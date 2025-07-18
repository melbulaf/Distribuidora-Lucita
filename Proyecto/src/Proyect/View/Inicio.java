/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package Proyect.View;

/**
 *
 * @author Samuel
 */
import Proyect.Model.Producto;
import Proyect.View.FormInventario;
import Proyect.View.FormRutas;
import Proyect.View.FormHistoriaCompra;
import Proyect.View.FormProducto;
import Proyect.Model.Ruta;
import java.util.ArrayList;
import Proyect.Model.RegistrarPedido;
import Proyect.Model.ResumenDeGanancias;


public class Inicio extends javax.swing.JFrame {
    // === VARIABLES GLOBALES ===
    public static ArrayList<Ruta> rutas = Ruta.rutasPorDefecto();
    private FormInventario objFormInventario;
    private FormHistoriaCompra objFormHCompras;
    private FormProducto objFormNProducto;
    private FormEditarProducto objFormEditar;
    private FormRegistrarPedido objFormPedido;
    private javax.swing.JInternalFrame jifFormRutas;
    private FormRutas objFormRutas;
    private FormRegistroCliente objFormRegistroCliente;
    private javax.swing.JInternalFrame jifFormRegistroCliente;
    private javax.swing.JInternalFrame jifFormEditarCliente;
    private EditarCliente objFormEditarCliente;

    
    
    // === CONSTRUCTOR ===
    public Inicio() {
        initComponents(); // NetBeans GUI
        Producto.cargarProductos();
        Ruta.inicializarPedidosEjemplo();
        inicializarFormularios();
        
    }

    // === INICIALIZA TUS FORMULARIOS ===
    private void inicializarFormularios() {
        // Inventario
        
        jifFormInventario.setClosable(true);
        jifFormInventario.setIconifiable(true);
        jifFormInventario.setMaximizable(true);
        jifFormInventario.setResizable(true);
        objFormInventario = new FormInventario();
        jifFormInventario.setContentPane(objFormInventario);
        objFormInventario.setSize(700,500);
        jifFormInventario.setSize(700,500);
        jifFormInventario.setVisible(false);

        // Ruta del Día
        objFormRutas = new FormRutas(rutas); // <-- usa la lista global rutas
        jifFormRutas = new javax.swing.JInternalFrame("Ruta del Día", true, true, true, true);
        jifFormRutas.setContentPane(objFormRutas);
        objFormRutas.setSize(700,500);
        jifFormRutas.setSize(700,500);
        jifFormRutas.setVisible(false);
        desktopPane.add(jifFormRutas);
        jifFormRutas.setBounds(10, 10, 700, 500);
        
        // Compras
        jifFormHCompras.setClosable(true);
        jifFormHCompras.setIconifiable(true);
        jifFormHCompras.setMaximizable(true);
        jifFormHCompras.setResizable(true);
        objFormHCompras = new FormHistoriaCompra();
        jifFormHCompras.setContentPane(objFormHCompras);
        objFormHCompras.setSize(700,500);
        jifFormHCompras.setSize(700,500);
        jifFormHCompras.setVisible(false);
        
        //Nuevo Producto
        jifFormNProducto.setClosable(true);
        jifFormNProducto.setIconifiable(true);
        jifFormNProducto.setMaximizable(true);
        jifFormNProducto.setResizable(true);
        objFormNProducto = new FormProducto();
        jifFormNProducto.setContentPane(objFormNProducto);
        jifFormNProducto.setSize(700,500);
        jifFormNProducto.setVisible(false);
        
        //Editar Producto
        jifFormEditar.setClosable(true);
        jifFormEditar.setIconifiable(true);
        jifFormEditar.setMaximizable(true);
        jifFormEditar.setResizable(true);
        objFormEditar = new FormEditarProducto();
        jifFormEditar.setContentPane(objFormEditar);
        jifFormEditar.setSize(700,500);
        jifFormEditar.setVisible(false);
        
        //Registrar Pedido
        jifFormPedido.setClosable(true);
        jifFormPedido.setIconifiable(true);
        jifFormPedido.setMaximizable(true);
        jifFormPedido.setResizable(true);
        objFormPedido = new FormRegistrarPedido();
        jifFormPedido.setContentPane(objFormPedido);
        jifFormPedido.setSize(870,500);
        jifFormPedido.setVisible(false);
        
        //Registrar Cliente
        jifFormRegistroCliente = new javax.swing.JInternalFrame("Registro de Cliente", true, true, true, true);
        objFormRegistroCliente = new FormRegistroCliente();
        jifFormRegistroCliente.setContentPane(objFormRegistroCliente);
        jifFormRegistroCliente.setSize(800, 480);
        jifFormRegistroCliente.setVisible(false);
        desktopPane.add(jifFormRegistroCliente);

        // Editar Cliente
        jifFormEditarCliente = new javax.swing.JInternalFrame("Editar Cliente", true, true, true, true);
        objFormEditarCliente = new EditarCliente();
        jifFormEditarCliente.setContentPane(objFormEditarCliente);
        jifFormEditarCliente.setSize(800, 500);
        jifFormEditarCliente.setVisible(false);
        desktopPane.add(jifFormEditarCliente);
        
    }
    //Metodo para centrar los Internal FrameAdd commentMore actions
    private void centrarInternalFrame(javax.swing.JInternalFrame frame) {
        int desktopWidth = desktopPane.getWidth();
        int desktopHeight = desktopPane.getHeight();
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int x = (desktopWidth - frameWidth) / 2;
        int y = (desktopHeight - frameHeight) / 2;
        frame.setLocation(x, y);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jifFormPedido = new javax.swing.JInternalFrame();
        jifFormInventario = new javax.swing.JInternalFrame();
        jifFormHCompras = new javax.swing.JInternalFrame();
        jifFormEditar = new javax.swing.JInternalFrame();
        jifFormNProducto = new javax.swing.JInternalFrame();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        InventarioMenuItem = new javax.swing.JMenuItem();
        Compras = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        RegistrarCliente = new javax.swing.JMenuItem();
        jMenuEditarC = new javax.swing.JMenuItem();
        menuItemNuevoProducto = new javax.swing.JMenuItem();
        MEditarP = new javax.swing.JMenuItem();
        menuItemRutaDelDia = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        salirMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jifFormPedido.setVisible(true);

        javax.swing.GroupLayout jifFormPedidoLayout = new javax.swing.GroupLayout(jifFormPedido.getContentPane());
        jifFormPedido.getContentPane().setLayout(jifFormPedidoLayout);
        jifFormPedidoLayout.setHorizontalGroup(
            jifFormPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jifFormPedidoLayout.setVerticalGroup(
            jifFormPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desktopPane.add(jifFormPedido);
        jifFormPedido.setBounds(0, 0, 630, 500);

        jifFormInventario.setClosable(true);
        jifFormInventario.setVisible(false);

        javax.swing.GroupLayout jifFormInventarioLayout = new javax.swing.GroupLayout(jifFormInventario.getContentPane());
        jifFormInventario.getContentPane().setLayout(jifFormInventarioLayout);
        jifFormInventarioLayout.setHorizontalGroup(
            jifFormInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jifFormInventarioLayout.setVerticalGroup(
            jifFormInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desktopPane.add(jifFormInventario);
        jifFormInventario.setBounds(0, 0, 770, 510);
        jifFormInventario.getAccessibleContext().setAccessibleDescription("");

        jifFormHCompras.setClosable(true);
        jifFormHCompras.setIconifiable(true);
        jifFormHCompras.setMaximizable(true);
        jifFormHCompras.setVisible(true);

        javax.swing.GroupLayout jifFormHComprasLayout = new javax.swing.GroupLayout(jifFormHCompras.getContentPane());
        jifFormHCompras.getContentPane().setLayout(jifFormHComprasLayout);
        jifFormHComprasLayout.setHorizontalGroup(
            jifFormHComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jifFormHComprasLayout.setVerticalGroup(
            jifFormHComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desktopPane.add(jifFormHCompras);
        jifFormHCompras.setBounds(50, 0, 550, 530);

        jifFormEditar.setVisible(true);

        javax.swing.GroupLayout jifFormEditarLayout = new javax.swing.GroupLayout(jifFormEditar.getContentPane());
        jifFormEditar.getContentPane().setLayout(jifFormEditarLayout);
        jifFormEditarLayout.setHorizontalGroup(
            jifFormEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jifFormEditarLayout.setVerticalGroup(
            jifFormEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desktopPane.add(jifFormEditar);
        jifFormEditar.setBounds(0, 0, 590, 500);

        jifFormNProducto.setVisible(true);

        javax.swing.GroupLayout jifFormNProductoLayout = new javax.swing.GroupLayout(jifFormNProducto.getContentPane());
        jifFormNProducto.getContentPane().setLayout(jifFormNProductoLayout);
        jifFormNProductoLayout.setHorizontalGroup(
            jifFormNProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jifFormNProductoLayout.setVerticalGroup(
            jifFormNProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        desktopPane.add(jifFormNProducto);
        jifFormNProducto.setBounds(50, 0, 630, 510);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Archivo");
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        InventarioMenuItem.setMnemonic('o');
        InventarioMenuItem.setText("Inventario");
        InventarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventarioMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(InventarioMenuItem);

        Compras.setText("Compras");
        Compras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComprasActionPerformed(evt);
            }
        });
        fileMenu.add(Compras);

        jMenuItem1.setText("Registrar pedido");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        RegistrarCliente.setText("Registro cliente");
        RegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarClienteActionPerformed(evt);
            }
        });
        fileMenu.add(RegistrarCliente);

        jMenuEditarC.setText("Editar cliente");
        jMenuEditarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEditarCActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuEditarC);

        menuItemNuevoProducto.setText("Nuevo producto");
        menuItemNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevoProductoActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemNuevoProducto);

        MEditarP.setText("Editar producto");
        MEditarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MEditarPActionPerformed(evt);
            }
        });
        fileMenu.add(MEditarP);

        menuItemRutaDelDia.setText("Ruta del Dia");
        menuItemRutaDelDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRutaDelDiaActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemRutaDelDia);

        jMenuItem2.setText("Resumen de ganancias");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        salirMenuItem.setMnemonic('x');
        salirMenuItem.setText("Salir");
        salirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(salirMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirMenuItemActionPerformed

    private void InventarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventarioMenuItemActionPerformed
        if (jifFormInventario == null || jifFormInventario.isClosed()) {
            objFormInventario = new FormInventario();
            jifFormInventario = new javax.swing.JInternalFrame("Inventario", true, true, true, true);
            jifFormInventario.setContentPane(objFormInventario);
            jifFormInventario.setSize(700, 500);
            desktopPane.add(jifFormInventario);
            jifFormInventario.setVisible(true);
            centrarInternalFrame(jifFormInventario);
        } else {
            jifFormInventario.setVisible(true);
            centrarInternalFrame(jifFormInventario);
            jifFormInventario.toFront();
            try {
                jifFormInventario.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {}
        }
    }//GEN-LAST:event_InventarioMenuItemActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileMenuActionPerformed

    private void ComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComprasActionPerformed
        if (jifFormHCompras == null || jifFormHCompras.isClosed()) {
            objFormHCompras = new FormHistoriaCompra();
            jifFormHCompras = new javax.swing.JInternalFrame("Historial de Compras", true, true, true, true);
            jifFormHCompras.setContentPane(objFormHCompras);
            jifFormHCompras.setSize(700, 500);
            desktopPane.add(jifFormHCompras);
            jifFormHCompras.setVisible(true);
            centrarInternalFrame(jifFormHCompras);
        } else {
            jifFormHCompras.setVisible(true);
            centrarInternalFrame(jifFormHCompras);
            jifFormHCompras.toFront();
            try {
                jifFormHCompras.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {}
        }
    }//GEN-LAST:event_ComprasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (jifFormPedido == null || jifFormPedido.isClosed()) {
            objFormPedido = new FormRegistrarPedido();
            jifFormPedido = new javax.swing.JInternalFrame("Registrar Pedido", true, true, true, true);
            jifFormPedido.setContentPane(objFormPedido);
            jifFormPedido.setSize(850, 500);
            desktopPane.add(jifFormPedido);
            jifFormPedido.setVisible(true);
            centrarInternalFrame(jifFormPedido);
        } else {
            jifFormPedido.setVisible(true);
            centrarInternalFrame(jifFormPedido);
            jifFormPedido.toFront();
            try {
                jifFormPedido.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {}
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuItemNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNuevoProductoActionPerformed
         if (jifFormNProducto == null || jifFormNProducto.isClosed()) {
            objFormNProducto = new FormProducto();
            jifFormNProducto = new javax.swing.JInternalFrame("Agregar Nuevo Producto", true, true, true, true);
            jifFormNProducto.setContentPane(objFormNProducto);
            jifFormNProducto.setSize(800, 500);
            desktopPane.add(jifFormNProducto);
            jifFormNProducto.setVisible(true);
            centrarInternalFrame(jifFormNProducto);
        } else {
            jifFormNProducto.setVisible(true);
            centrarInternalFrame(jifFormNProducto);
            jifFormNProducto.toFront();
            try {
                jifFormNProducto.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {}
        }
    }//GEN-LAST:event_menuItemNuevoProductoActionPerformed

    private void MEditarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MEditarPActionPerformed
        if (jifFormEditar == null || jifFormEditar.isClosed()) {
            objFormEditar = new FormEditarProducto();
            jifFormEditar = new javax.swing.JInternalFrame("Editar Producto", true, true, true, true);
            jifFormEditar.setContentPane(objFormEditar);
            jifFormEditar.setSize(700,500);
            desktopPane.add(jifFormEditar);
            jifFormEditar.setVisible(true);
            centrarInternalFrame(jifFormEditar);
        } else {
            jifFormEditar.setVisible(true);
            centrarInternalFrame(jifFormEditar);
            jifFormEditar.toFront();
            try {
                jifFormEditar.setSelected(true);
            } catch (java.beans.PropertyVetoException ex) {}
        }
    }//GEN-LAST:event_MEditarPActionPerformed

    private void menuItemRutaDelDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRutaDelDiaActionPerformed
        if (jifFormRutas == null || jifFormRutas.isClosed()) {
                objFormRutas = new FormRutas(rutas);
                jifFormRutas = new javax.swing.JInternalFrame("Ruta del Día", true, true, true, true);
                jifFormRutas.setContentPane(objFormRutas);
                jifFormRutas.setSize(700, 500);
                desktopPane.add(jifFormRutas);
                jifFormRutas.setVisible(true);
                centrarInternalFrame(jifFormRutas);
            } else {
                jifFormRutas.setVisible(true);
                centrarInternalFrame(jifFormRutas);
                jifFormRutas.toFront();
                try {
                    jifFormRutas.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {}
            }
    }//GEN-LAST:event_menuItemRutaDelDiaActionPerformed

    private void RegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarClienteActionPerformed
    if (jifFormRegistroCliente == null || jifFormRegistroCliente.isClosed()) {
        objFormRegistroCliente = new FormRegistroCliente();
        jifFormRegistroCliente = new javax.swing.JInternalFrame("Registro de Cliente", true, true, true, true);
        jifFormRegistroCliente.setContentPane(objFormRegistroCliente);
        jifFormRegistroCliente.setSize(800, 480);
        objFormRegistroCliente.setSize(800, 480);
        desktopPane.add(jifFormRegistroCliente);
        jifFormRegistroCliente.setVisible(true);
        centrarInternalFrame(jifFormRegistroCliente);
    } else {
        jifFormRegistroCliente.setVisible(true);
        centrarInternalFrame(jifFormRegistroCliente);
        jifFormRegistroCliente.toFront();
        try {
            jifFormRegistroCliente.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {}
    }
    }//GEN-LAST:event_RegistrarClienteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        FromResumenDeGanancias a = new FromResumenDeGanancias();
        desktopPane.add(a);
        a.setVisible(true);
      
       
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuEditarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEditarCActionPerformed
        if (jifFormEditarCliente == null || jifFormEditarCliente.isClosed()) {
        objFormEditarCliente = new EditarCliente();
        jifFormEditarCliente = new javax.swing.JInternalFrame("Editar Cliente", true, true, true, true);
        jifFormEditarCliente.setContentPane(objFormEditarCliente);
        jifFormEditarCliente.setSize(800, 500);
        desktopPane.add(jifFormEditarCliente);
        jifFormEditarCliente.setVisible(true);
        centrarInternalFrame(jifFormEditarCliente);
    } else {
        jifFormEditarCliente.setVisible(true);
        centrarInternalFrame(jifFormEditarCliente);
        jifFormEditarCliente.toFront();
        try {
            jifFormEditarCliente.setSelected(true);
        } catch (java.beans.PropertyVetoException ex) {}
    }
    }//GEN-LAST:event_jMenuEditarCActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Compras;
    private javax.swing.JMenuItem InventarioMenuItem;
    private javax.swing.JMenuItem MEditarP;
    private javax.swing.JMenuItem RegistrarCliente;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMenuEditarC;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JInternalFrame jifFormEditar;
    private javax.swing.JInternalFrame jifFormHCompras;
    private javax.swing.JInternalFrame jifFormInventario;
    private javax.swing.JInternalFrame jifFormNProducto;
    private javax.swing.JInternalFrame jifFormPedido;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemNuevoProducto;
    private javax.swing.JMenuItem menuItemRutaDelDia;
    private javax.swing.JMenuItem salirMenuItem;
    // End of variables declaration//GEN-END:variables

}

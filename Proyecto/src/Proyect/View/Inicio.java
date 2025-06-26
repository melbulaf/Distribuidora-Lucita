/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package Proyect.View;

/**
 *
 * @author Samuel
 */


import Proyect.Model.MisDatosDeEjemplo;
import Proyect.Model.Producto;
import Proyect.View.FormInventario;
import Proyect.View.FormRutas;
import Proyect.View.FormHistoriaCompra;
import Proyect.View.FormProducto;
import Proyect.Model.Ruta;
import java.util.ArrayList;

public class Inicio extends javax.swing.JFrame {
    // === VARIABLES GLOBALES ===
    private FormInventario objFormInventario;
    private FormHistoriaCompra objFormHCompras;
    private FormProducto objFormNProducto;
    private FormEditar objFormEditar;
    private FormRegistrarPedido objFormPedido;
    private javax.swing.JMenuItem menuItemRutaDelDia;
    private javax.swing.JInternalFrame jifFormRutas;
    private FormRutas objFormRutas;

    
    // === CONSTRUCTOR ===
    public Inicio() {
        initComponents(); // NetBeans GUI
        Producto.cargarProductos();
        inicializarFormularios();
        inicializarMenuRutaDelDia();
        
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
        ArrayList<Ruta> rutas = MisDatosDeEjemplo.crearRutas(); 
        objFormRutas = new FormRutas(rutas);
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
        
        //Editar
        jifFormEditar.setClosable(true);
        jifFormEditar.setIconifiable(true);
        jifFormEditar.setMaximizable(true);
        jifFormEditar.setResizable(true);
        objFormEditar = new FormEditar();
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
        
    }

    // === AGREGA EL MENÚ Y SU ACCIÓN ===
    private void inicializarMenuRutaDelDia() {
    menuItemRutaDelDia = new javax.swing.JMenuItem("Ruta del Día");
    fileMenu.add(menuItemRutaDelDia);
    menuItemRutaDelDia.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (jifFormRutas == null || jifFormRutas.isClosed()) {
                ArrayList<Ruta> rutas = MisDatosDeEjemplo.crearRutas();
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
        }
    });
}
    
    //Metodo para centrar los Internal Frame
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
        jifFormInventario = new javax.swing.JInternalFrame();
        jifFormHCompras = new javax.swing.JInternalFrame();
        jifFormEditar = new javax.swing.JInternalFrame();
        jifFormNProducto = new javax.swing.JInternalFrame();
        jifFormPedido = new javax.swing.JInternalFrame();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        InventarioMenuItem = new javax.swing.JMenuItem();
        salirMenuItem = new javax.swing.JMenuItem();
        Compras = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuItemNuevoProducto = new javax.swing.JMenuItem();
        MEditar = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jifFormNProducto.setBounds(0, 0, 630, 510);

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
        jifFormPedido.setBounds(0, 0, 650, 500);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");
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

        salirMenuItem.setMnemonic('x');
        salirMenuItem.setText("Salir");
        salirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(salirMenuItem);

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

        menuItemNuevoProducto.setText("Nuevo producto");
        menuItemNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNuevoProductoActionPerformed(evt);
            }
        });
        fileMenu.add(menuItemNuevoProducto);

        MEditar.setText("Editar");
        MEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MEditarActionPerformed(evt);
            }
        });
        fileMenu.add(MEditar);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

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
            jifFormPedido.setSize(700, 500);
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
            jifFormNProducto.setSize(700, 500);
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

    private void MEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MEditarActionPerformed
        if (jifFormEditar == null || jifFormEditar.isClosed()) {
            objFormEditar = new FormEditar();
            jifFormEditar = new javax.swing.JInternalFrame("Editar Producto", true, true, true, true);
            jifFormEditar.setContentPane(objFormEditar);
            jifFormEditar.setSize(700,700);
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
    }//GEN-LAST:event_MEditarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Compras;
    private javax.swing.JMenuItem InventarioMenuItem;
    private javax.swing.JMenuItem MEditar;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JInternalFrame jifFormEditar;
    private javax.swing.JInternalFrame jifFormHCompras;
    private javax.swing.JInternalFrame jifFormInventario;
    private javax.swing.JInternalFrame jifFormNProducto;
    private javax.swing.JInternalFrame jifFormPedido;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuItemNuevoProducto;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem salirMenuItem;
    // End of variables declaration//GEN-END:variables

}

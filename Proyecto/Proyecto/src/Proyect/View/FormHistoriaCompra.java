/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Proyect.View;

import Proyect.Model.Inventario;
import Proyect.Model.Producto;
import Proyect.Model.Compra;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rifter
 */
public class FormHistoriaCompra extends javax.swing.JPanel {

    /**
     * Creates new form FormCompra
     */
    public FormHistoriaCompra() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) TablaHistorial.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (int i = Compra.compras.size() - 1; i >= 0; i--) {
                Compra p = Compra.compras.get(i);
                modelo.addRow(new Object[]{
                    p.producto.getCodigo(),
                    p.producto.nombre,
                    p.cantidad,
                    p.producto.precioC,
                    p.total
                });
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaHistorial = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        agregarCompra = new javax.swing.JButton();

        TablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaHistorial);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Compras");

        agregarCompra.setText("Agregar Compra");
        agregarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(agregarCompra)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(agregarCompra)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCompraActionPerformed
        FormCompra formCompra = new FormCompra(this);
        formCompra.setSize(460,360);
        formCompra.setVisible(true);
    }//GEN-LAST:event_agregarCompraActionPerformed
    
    public void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) TablaHistorial.getModel();
        modelo.setRowCount(0); // Limpia la tabla
        for (int i = Compra.compras.size() - 1; i >= 0; i--) {
            Compra p = Compra.compras.get(i);
            modelo.addRow(new Object[]{
                p.producto.getCodigo(),
                p.producto.nombre,
                p.cantidad,
                p.producto.precioC,
                p.total
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaHistorial;
    private javax.swing.JButton agregarCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

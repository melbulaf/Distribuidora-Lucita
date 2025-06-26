/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Controler;

import Proyect.View.Inicio;

/**
 *
 * @author MELANIE BULA FUENTES
 */
public class Main {
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Mostrar la ventana
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            Inicio inicio = new Inicio();
            inicio.setSize(1000,700);
            inicio.setVisible(true);
        }
    });
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import java.util.ArrayList;
import Proyect.Model.RegistrarPedido;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import Proyect.Model.Inventario;
import javax.swing.JTable;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author pc
 */

public class ResumenDeGanancias {
    private Producto nombreDelProducto;
    private Producto precioDeVenta;
    private Producto precioDeCompra;
    private Producto ganancia;

    public static List<String> nombresDePedidos() {
    List<String> datosPedidosN = new ArrayList<>();
    

    try (BufferedReader br = new BufferedReader(new FileReader("src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String nombre = partes[0].trim();
                String fecha = partes[partes.length - 1].trim(); // Último campo como fecha
                datosPedidosN.add(nombre);
                
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    for(String x : datosPedidosN){
        
    }

    return datosPedidosN;
}
    
    public static List<String> fechasDePedidos(){
    List<String> datosPedidosF = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String fecha = partes[partes.length - 1].trim(); // Último campo como fecha
                datosPedidosF.add(fecha);
                
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return datosPedidosF;
}
    
    
    public static void agregarProductosDeHoyATabla(DefaultTableModel dtm) {
    String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();

    for (int i = 0; i < fechas.size(); i++) {
        if (fechas.get(i).equals(fechaHoy)) {
            Object[] fila = new Object[4];
            fila[0] = nombres.get(i);
            fila[1] = 100; // precio venta
            fila[2] = 100; // precio compra
            fila[3] = 100; // ganancia
            dtm.insertRow(0, fila); // Insertar al inicio
        }
    }
    
    
}
    
    
    
    
    public static void calcularTotales(JTable tablaVentas, JTable tablaTotales, JTable tablaUtilidad) {
    DefaultTableModel modeloVentas = (DefaultTableModel) tablaVentas.getModel();
    int totalFilas = modeloVentas.getRowCount();
    int totalIngresos = 0;

    for (int i = 0; i < totalFilas; i++) {
        Object precioObj = modeloVentas.getValueAt(i, 1);  // columna precio
        Object cantidadObj = modeloVentas.getValueAt(i, 2); // columna cantidad

        if (precioObj != null && cantidadObj != null) {
            try {
                int precio = Integer.parseInt(precioObj.toString());
                int cantidad = Integer.parseInt(cantidadObj.toString());
                totalIngresos += precio * cantidad;
            } catch (NumberFormatException e) {
                System.out.println("Error de conversión en fila " + i);
            }
        }
    }

    // Mostrar total de ingresos
    DefaultTableModel modeloTotales = (DefaultTableModel) tablaTotales.getModel();
    modeloTotales.setRowCount(0); // limpia antes
    modeloTotales.addRow(new Object[]{totalIngresos});

    // Calcular 15% utilidad
    double utilidad = totalIngresos * 0.15;

    // Mostrar utilidad neta
    DefaultTableModel modeloUtilidad = (DefaultTableModel) tablaUtilidad.getModel();
    modeloUtilidad.setRowCount(0); // limpia antes
    modeloUtilidad.addRow(new Object[]{utilidad});
}
    
    
    
    
    public void guardarVentasEnArchivoTxt(JTable tablaVentas, JTable tablaTotales, JTable tablaUtilidad, String nombreArchivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
        DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();

        // Guardar las filas de ventas
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String linea = "";
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                Object valor = modelo.getValueAt(i, j);
                linea += (valor != null ? valor.toString() : "");
                if (j < modelo.getColumnCount() - 1) {
                    linea += ",";
                }
            }
            writer.write(linea);
            writer.newLine();
        }

        // Escribir una línea vacía como separador
        writer.newLine();

        // Guardar el valor del total de ingresos (asume solo una fila, una columna)
        DefaultTableModel modeloTotales = (DefaultTableModel) tablaTotales.getModel();
        if (modeloTotales.getRowCount() > 0 && modeloTotales.getColumnCount() > 0) {
            Object totalIngresos = modeloTotales.getValueAt(0, 0);
            writer.write("TOTAL_INGRESOS=" + (totalIngresos != null ? totalIngresos.toString() : "0"));
            writer.newLine();
        }

        // Guardar el valor de utilidad neta
        DefaultTableModel modeloUtilidad = (DefaultTableModel) tablaUtilidad.getModel();
        if (modeloUtilidad.getRowCount() > 0 && modeloUtilidad.getColumnCount() > 0) {
            Object utilidad = modeloUtilidad.getValueAt(0, 0);
            writer.write("UTILIDAD_NETA=" + (utilidad != null ? utilidad.toString() : "0"));
            writer.newLine();
        }

    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar archivo: " + e.getMessage());
    }
}

    
    
    public void cargarVentasDesdeArchivoTxt(JTable tablaVentas, JTable tablaTotales, JTable tablaUtilidad, String nombreArchivo) {
    DefaultTableModel modeloVentas = (DefaultTableModel) tablaVentas.getModel();
    DefaultTableModel modeloTotales = (DefaultTableModel) tablaTotales.getModel();
    DefaultTableModel modeloUtilidad = (DefaultTableModel) tablaUtilidad.getModel();

    // Limpiar todas las tablas
    modeloVentas.setRowCount(0);
    modeloTotales.setRowCount(0);
    modeloUtilidad.setRowCount(0);

    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
        String linea;
        boolean leyendoVentas = true;

        while ((linea = reader.readLine()) != null) {
            if (linea.trim().isEmpty()) {
                // Línea vacía: indica que vienen totales y utilidad
                leyendoVentas = false;
                continue;
            }

            if (leyendoVentas) {
                // Leer y agregar a tabla de ventas
                String[] datos = linea.split(",");
                modeloVentas.addRow(datos);
            } else {
                // Leer totales y utilidad
                if (linea.startsWith("TOTAL_INGRESOS=")) {
                    String valor = linea.substring("TOTAL_INGRESOS=".length());
                    modeloTotales.addRow(new Object[]{valor});
                } else if (linea.startsWith("UTILIDAD_NETA=")) {
                    String valor = linea.substring("UTILIDAD_NETA=".length());
                    modeloUtilidad.addRow(new Object[]{valor});
                }
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar archivo: " + e.getMessage());
    }
}









}


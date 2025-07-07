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
    

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
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

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
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
    
    
    
    public static List<String> clientesDePedidos() {
    List<String> datosClientes = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String cliente = partes[2].trim(); // Tercer campo como cliente
                datosClientes.add(cliente);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return datosClientes;
}
    
    public static List<Double> preciosCompraProductos(List<String> nombresProductos) {
    List<Double> preciosCompra = new ArrayList<>();

    for (String nombre : nombresProductos) {
        Producto productoEncontrado = buscarProductoPorNombre(nombre);
        if (productoEncontrado != null) {
            preciosCompra.add(productoEncontrado.precioC);
        } else {
            preciosCompra.add(0.0); // o cualquier valor por defecto si no se encuentra
        }
    }

    return preciosCompra;
}
    
    public static List<Double> preciosVentaProductos(List<String> nombresProductos) {
    List<Double> preciosVenta = new ArrayList<>();

    for (String nombre : nombresProductos) {
        Producto producto = buscarProductoPorNombre(nombre);

        if (producto != null) {
            preciosVenta.add(producto.precio);
        } else {
            preciosVenta.add(0.0); // Valor por defecto si no se encuentra
        }
    }

    return preciosVenta;
}
    
    
    public static List<Double> calcularGanancias(List<Double> preciosVenta, List<Double> preciosCompra) {
    List<Double> ganancias = new ArrayList<>();

    for (int i = 0; i < preciosVenta.size(); i++) {
        double venta = preciosVenta.get(i);
        double compra = preciosCompra.get(i);
        double ganancia = venta - compra;
        ganancias.add(ganancia);
    }

    return ganancias;
}
    
    public static void agregarProductosDeHoyATabla(DefaultTableModel dtm) {
    String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();
    List<String> clientes = clientesDePedidos();
    List<Double> compra = preciosCompraProductos(nombres);
    List<Double> venta = preciosVentaProductos(nombres);
    List<Double> ganancia = calcularGanancias(venta,compra);
    

    for (int i = 0; i < fechas.size(); i++) {
        if (fechas.get(i).equals(fechaHoy)) {
            Object[] fila = new Object[6];
            fila[0] = nombres.get(i);
            fila[1] = clientes.get(i); 
            fila[2] =  fechas.get(i);
            fila[3] = compra.get(i);
            fila[4] = venta.get(i);
            fila[5] = ganancia.get(i);
            dtm.insertRow(0, fila); 
        }
    }
    
    
}
    
    public static Producto buscarProductoPorNombre(String nombreProducto) {
    for (Producto producto : Inventario.productos) {
        if (producto.nombre.equalsIgnoreCase(nombreProducto.trim())) {
            return producto;
        }
    }
    return null;
}

    
    
    

}


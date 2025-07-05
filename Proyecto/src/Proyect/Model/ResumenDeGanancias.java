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
    List<String> datosPedidosF = new ArrayList<>();

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
}



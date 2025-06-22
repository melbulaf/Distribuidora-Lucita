/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import static Proyect.Model.Inventario.productos;
import Proyect.Model.Producto;
import java.util.ArrayList;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Rifter
 */
public class Compra {
    public Producto producto;
    public int cantidad;
    public double total;
    public static ArrayList<Compra> compras = new ArrayList<>();
     static {
        cargarC();
    }
    
    public Compra(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = (producto.precioC * cantidad);
        compras.add(this);
    }
    
    public static void guardarC() {
        File archivoCompras = new File("src\\Proyect\\Controler\\BD\\compras.txt");
        try {
            PrintWriter salida = new PrintWriter(archivoCompras);
            for (int i = Compra.compras.size() - 1; i >= 0; i--) {
                Compra c = Compra.compras.get(i);
                salida.println(
                c.producto.getCodigo() + "," +
                c.producto.precioC + "," +
                c.cantidad + "," +
                c.total
            );
            }
            salida.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void cargarC() {
        File archivoCompras = new File("src\\Proyect\\Controler\\BD\\compras.txt");
        try {
            BufferedReader leer = new BufferedReader(new FileReader(archivoCompras));
            compras.clear();
            ArrayList<String> lineas = new ArrayList<>();
            String lectura;
            while ((lectura = leer.readLine()) != null) {
                lineas.add(lectura);
            }
            for (int i = lineas.size() - 1; i >= 0; i--) {
                String[] partes = lineas.get(i).split(",");
                int id = Integer.parseInt(partes[0]);
                double precioC = Double.parseDouble(partes[1]);
                int cantidad = Integer.parseInt(partes[2]);
                Producto encontrado = null;
                for (Producto p : Inventario.productos){
                    if (p.getCodigo() == id) {
                        encontrado = p;
                        break;
                    }
                }
                if (encontrado != null) {
                    new Compra(encontrado, cantidad);
                } else {
                    System.out.println("Producto con código " + id + " no encontrado.");
                }
            }
            leer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
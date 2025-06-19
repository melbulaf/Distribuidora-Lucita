
/**
 *
 * @author Jorkman
 */

package Proyect.Controler;

import Proyect.Model.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String FILE_PATH = "productos.txt";

    public static void guardarProducto(Producto producto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(producto.getNombre() + "," +
                     producto.getCategoria() + "," +
                     producto.getCantidad() + "," +
                     producto.getPrecio());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String nombre = datos[0];
                    String categoria = datos[1];
                    int cantidad = Integer.parseInt(datos[2]);
                    double precio = Double.parseDouble(datos[3]);
                    productos.add(new Producto(nombre, categoria, cantidad, precio));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }
}

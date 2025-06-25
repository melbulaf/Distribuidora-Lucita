/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Jorkman
 */
package Proyect.Model;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase de utilidades para buscar, actualizar y eliminar Productos
 * en el Inventario.
 */
public class Editar {

    /**
     * Busca un producto por código (String) o por nombre (ignora mayúsculas).
     * @param entrada texto con el código o el nombre
     * @return la instancia de Producto si la encuentra, o null si no
     */
    public static Producto buscarProducto(String entrada) {
        for (Producto p : Inventario.getInstancia().obtenerProductos()) {
            // Compara con el código convertido a String o con el nombre
            if (String.valueOf(p.getCodigo()).equals(entrada)
             || p.nombre.equalsIgnoreCase(entrada)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Actualiza uno de los campos del producto según el atributo elegido.
     * Sólo actualiza uno a la vez, y persiste en el archivo de texto.
     *
     * @param p         producto a modificar
     * @param atributo  nombre del atributo ("nombre", "cantidad", "precio de compra", "precio de venta")
     * @param nuevoValor valor en texto que se convertirá al tipo adecuado
     * @return true si la actualización fue exitosa, false en caso de error
     */
    public static boolean actualizarProducto(Producto p, String atributo, String nuevoValor) {
        try {
            switch (atributo.toLowerCase()) {
                case "nombre":
                    p.nombre = nuevoValor;
                    break;
                case "cantidad":
                    p.cantidad = Integer.parseInt(nuevoValor);
                    break;
                case "precio de compra":
                    p.precioC = Double.parseDouble(nuevoValor);
                    break;
                case "precio de venta":
                    p.precio = Double.parseDouble(nuevoValor);
                    break;
                default:
                    // Atributo no reconocido
                    return false;
            }
            // Guardar todos los productos en el archivo
            Producto.guardarProductos();
            return true;
        } catch (NumberFormatException ex) {
            // Valor numérico inválido
            JOptionPane.showMessageDialog(null,
                "Valor numérico inválido para " + atributo + ": " + nuevoValor,
                "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un producto del inventario y persiste el cambio.
     * @param p producto a eliminar
     * @return true si se eliminó, false si no se encontró o no pudo eliminarse
     */
    public static boolean eliminarProducto(Producto p) {
        boolean eliminado = Inventario.getInstancia().obtenerProductos().remove(p);
        if (eliminado) {
            // Guardar tras eliminar
            Producto.guardarProductos();
        }
        return eliminado;
    }
}

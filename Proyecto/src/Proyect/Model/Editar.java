/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Jorkman
 */

package Proyect.Model;
import javax.swing.JOptionPane;

public class Editar {

    // Busca un producto según su código (como texto) o su nombre (ignorando mayúsculas)
    public static Producto buscarProducto(String entrada) {
        for (Producto p : Inventario.getInstancia().obtenerProductos()) {
            if (String.valueOf(p.getCodigo()).equals(entrada)
             || p.nombre.equalsIgnoreCase(entrada)) {
                return p;
            }
        }
        return null;
    }

    // Actualiza un único atributo de un producto según el nombre del campo
    // Valida los valores antes de aplicar los cambios y guarda los productos en el archivo
    public static boolean actualizarProducto(Producto p, String atributo, String nuevoValor) {
        try {
            switch (atributo.trim().toLowerCase()) {
                case "nombre":
                    p.nombre = nuevoValor;
                    break;

                case "cantidad":
                    int cantidad = Integer.parseInt(nuevoValor);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null,
                            "La cantidad debe ser mayor que cero.",
                            "Valor inválido", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    p.cantidad = cantidad;
                    break;

                case "categoría":
                    if (!nuevoValor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        JOptionPane.showMessageDialog(null,
                            "La categoría debe contener solo letras (sin números ni símbolos).",
                            "Categoría inválida", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    p.categoria = nuevoValor;
                    break;

                case "precio de compra":
                    double precioC = Double.parseDouble(nuevoValor);
                    if (precioC <= 0) {
                        JOptionPane.showMessageDialog(null,
                            "El precio de compra debe ser mayor que cero.",
                            "Valor inválido", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    p.precioC = precioC;
                    break;

                case "precio de venta":
                    double precioV = Double.parseDouble(nuevoValor);
                    if (precioV <= 0) {
                        JOptionPane.showMessageDialog(null,
                            "El precio de venta debe ser mayor que cero.",
                            "Valor inválido", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    p.precio = precioV;
                    break;

                default:
                    System.out.println("Atributo no reconocido: '" + atributo + "'");
                    return false;
            }

            // Guarda los cambios en el archivo de productos
            Producto.guardarProductos();
            return true;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                "Valor numérico inválido para " + atributo + ": " + nuevoValor,
                "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Elimina un producto del inventario y guarda los cambios en el archivo
    public static boolean eliminarProducto(Producto p) {
        boolean eliminado = Inventario.getInstancia().obtenerProductos().remove(p);
        if (eliminado) {
            Producto.guardarProductos();
        }
        return eliminado;
    }
}

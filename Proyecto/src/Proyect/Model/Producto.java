
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jorkman
 */
package Proyect.Model;

import java.io.*;
import java.util.List;
import javax.swing.JOptionPane;

public class Producto {

    // Contador que genera códigos únicos para cada producto
    public static int contador = 1000;

    // Atributos del producto
    private final int codigo;
    public String nombre;
    public String categoria;
    public double precio;   // Precio de venta
    public double precioC;  // Precio de compra
    public int cantidad;

    // Constructor para crear productos nuevos desde la interfaz (sin cantidad inicial)
    public Producto(String nombre, String categoria, double precio, double precioC) {
        this.codigo = contador++; // Asigna código único
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = 0; // Por defecto el producto inicia sin existencias
        Inventario.getInstancia().agregarProducto(this);
        guardarProductos();
    }

    // Constructor para crear productos nuevos desde la interfaz con cantidad inicial
    public Producto(String nombre, String categoria, double precio, double precioC, int cantidad) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = cantidad;
        Inventario.getInstancia().agregarProducto(this);
        guardarProductos();
    }

    // Constructor auxiliar para búsqueda
    public Producto(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    // Constructor privado para carga desde archivo
    private Producto(int codigo, String nombre, String categoria, double precio, double precioC, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCantidad(int c) {
        this.cantidad = c;
    }

    // === Funciones heredadas de la antigua clase Editar ===

    // Buscar producto por código o nombre (ignorando mayúsculas)
    public static Producto buscarProducto(String entrada) {
        for (Producto p : Inventario.getInstancia().obtenerProductos()) {
            if (String.valueOf(p.getCodigo()).equals(entrada)
             || p.nombre.equalsIgnoreCase(entrada)) {
                return p;
            }
        }
        return null;
    }

    // Actualiza un único atributo del producto
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

            guardarProductos(); // Guarda cambios
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

    // Elimina un producto de la lista
    public static boolean eliminarProducto(Producto p) {
        boolean eliminado = Inventario.getInstancia().obtenerProductos().remove(p);
        if (eliminado) {
            guardarProductos();
        }
        return eliminado;
    }

    // === Persistencia ===

    public static void guardarProductos() {
        try {
            File archivo = new File("src\\Proyect\\Controler\\BD\\Nproductos.txt");
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }

            PrintWriter salida = new PrintWriter(new FileWriter(archivo));
            for (Producto p : Inventario.getInstancia().obtenerProductos()) {
                salida.println(p.codigo + "%%" + p.nombre + "%%" + p.categoria + "%%" + p.precio + "%%" + p.precioC + "%%" + p.cantidad);
            }
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void cargarProductos() {
        try {
            File archivo = new File("src\\Proyect\\Controler\\BD\\Nproductos.txt");
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            List<Producto> productos = Inventario.getInstancia().obtenerProductos();
            productos.clear(); // Limpia antes de cargar

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("%%");

                if (partes.length == 6) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String categoria = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    double precioC = Double.parseDouble(partes[4]);
                    int cantidad = Integer.parseInt(partes[5]);

                    if (existeProducto(nombre)) {
                        System.out.println("Producto " + nombre + " duplicado");
                    }

                    Producto p = new Producto(codigo, nombre, categoria, precio, precioC, cantidad);
                    productos.add(p);
                }
            }

            lector.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static boolean existeProducto(String nombre) {
        for (Producto p : Inventario.getInstancia().obtenerProductos()) {
            if (p.nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
}

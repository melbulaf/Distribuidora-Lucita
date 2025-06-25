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
import java.util.ArrayList;
import java.util.List;

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
        // Agrega el producto a la lista
        Inventario.getInstancia().agregarProducto(this);
        // Guarda los productos en el archivo
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

    // Constructor auxiliar usado para buscar o comparar productos por nombre o código
    public Producto(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = contador++;
    }
    
    private Producto(int codigo, String nombre, String categoria, double precio, double precioC, int cantidad) {
    this.codigo = contador++;
    this.nombre = nombre;
    this.categoria = categoria;
    this.precio = precio;
    this.precioC = precioC;
    this.cantidad = cantidad;
}

    // Devuelve el código del producto
    public int getCodigo() {
        return this.codigo;
    }

    // Guarda todos los productos actuales en el archivo de texto
    public static void guardarProductos() {
        try {
            
            // Archivo de texto que actúa como base de datos local
            File archivoProductos = new File("src\\Proyect\\Controler\\BD\\Nproductos.txt");
            // Si el archivo no existe, se crea junto a sus carpetas padre
            if (!archivoProductos.exists()) {
                archivoProductos.getParentFile().mkdirs();
                archivoProductos.createNewFile();
            }

            // Escribe cada producto en el archivo en formato CSV
            PrintWriter salida = new PrintWriter(new FileWriter(archivoProductos));
            for (Producto p : Inventario.getInstancia().obtenerProductos()) {
                salida.println(p.codigo + "," + p.nombre + "," + p.categoria + "," + p.precio + "," + p.precioC + "," + p.cantidad);
            }
            salida.close();

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void setCantidad(int c) {
        this.cantidad = c;
    }

    // Carga los productos desde el archivo y los agrega a la lista y al inventario
    public static void cargarProductos() {
        try {
            
            // Archivo de texto que actúa como base de datos local
            File archivoProductos = new File("src\\Proyect\\Controler\\BD\\Nproductos.txt");
            // Si el archivo no existe, se crea vacío
            if (!archivoProductos.exists()) {
                archivoProductos.getParentFile().mkdirs();
                archivoProductos.createNewFile();
                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
            String linea;
            Inventario.getInstancia().obtenerProductos().clear(); // Limpia la lista antes de cargar

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 6) { // Asegura que la línea tiene todos los datos
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String categoria = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    double precioC = Double.parseDouble(partes[4]);
                    int cantidad = Integer.parseInt(partes[5]);

                    // Valida si el producto ya existe para evitar duplicados
                    if (existeProducto(nombre)) {
                        System.out.println("Producto" + nombre + "duplicado");
                    }
                    // Crea el producto usando el constructor especial para carga
                    Producto p = new Producto(codigo, nombre, categoria, precio, precioC, cantidad);
                    Inventario.getInstancia().agregarProducto(p);
                }
            }

            // Asegura que el próximo producto tenga un código único
            lector.close();

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Verifica si un producto con el mismo nombre ya existe (ignorando mayúsculas)
    public static boolean existeProducto(String nombre) {
        for (Producto p : Inventario.getInstancia().obtenerProductos()) {
            if (p.nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    
}
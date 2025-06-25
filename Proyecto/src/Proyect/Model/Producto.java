/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package Proyect.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    // Contador que genera códigos únicos para cada producto
    public static int contador = 1000;

    // Atributos del producto
    public int codigo;
    public String nombre;
    public String categoria;
    public double precio;   // Precio de venta
    public double precioC;  // Precio de compra
    public int cantidad;

    // Lista estática que contiene todos los productos registrados
    public static List<Producto> productos = new ArrayList<>();

    // Archivo de texto que actúa como base de datos local
    private static final File archivoProductos = new File("src\\Proyect\\Controler\\BD\\Nproductos.txt");

    // Bloque estático: se ejecuta cuando la clase se carga
    // Carga los productos desde el archivo y los asigna al inventario
    static {
        cargarProductos();
        Inventario.productos = new ArrayList<>(productos);
    }

    // Constructor para crear productos nuevos desde la interfaz (sin cantidad inicial)
    public Producto(String nombre, String categoria, double precio, double precioC) {
        this.codigo = contador++; // Asigna código único
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = 0; // Por defecto el producto inicia sin existencias

        // Agrega el producto a la lista
        productos.add(this);
        // Actualiza el inventario
        Inventario.productos = new ArrayList<>(productos);
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

        productos.add(this);
        Inventario.productos = new ArrayList<>(productos);
        guardarProductos();
    }

    // Constructor auxiliar usado para buscar o comparar productos por nombre o código
    public Producto(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    // Constructor especial para cargar productos desde el archivo (NO guarda)
    public Producto(int codigo, String nombre, String categoria, double precio, double precioC, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = cantidad;

        productos.add(this);
        Inventario.productos = new ArrayList<>(productos);
    }

    // Devuelve el código del producto
    public int getCodigo() {
        return codigo;
    }

    // Guarda todos los productos actuales en el archivo de texto
    public static void guardarProductos() {
        try {
            // Si el archivo no existe, se crea junto a sus carpetas padre
            if (!archivoProductos.exists()) {
                archivoProductos.getParentFile().mkdirs();
                archivoProductos.createNewFile();
            }

            // Escribe cada producto en el archivo en formato CSV
            PrintWriter salida = new PrintWriter(new FileWriter(archivoProductos));
            for (Producto p : productos) {
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
            // Si el archivo no existe, se crea vacío
            if (!archivoProductos.exists()) {
                archivoProductos.getParentFile().mkdirs();
                archivoProductos.createNewFile();
                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivoProductos));
            String linea;
            productos.clear(); // Limpia la lista antes de cargar
            int maxCodigo = 0; // Para actualizar el contador con el mayor código encontrado

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");

                if (partes.length == 6) { // Asegura que la línea tiene todos los datos
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String categoria = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    double precioC = Double.parseDouble(partes[4]);
                    int cantidad = Integer.parseInt(partes[5]);

                    try {
                        // Valida si el producto ya existe para evitar duplicados
                        if (existeProducto(nombre)) {
                            throw new ProductoDuplicadoException("Producto duplicado: " + nombre);
                        }

                        // Crea el producto usando el constructor especial para carga
                        Producto p = new Producto(codigo, nombre, categoria, precio, precioC, cantidad);

                        // Actualiza el código más alto encontrado
                        if (codigo > maxCodigo) {
                            maxCodigo = codigo;
                        }
                    } catch (ProductoDuplicadoException ex) {
                        System.out.println(ex.getMessage());
                        // Aquí se podría guardar en un log si se desea
                    }
                }
            }

            // Asegura que el próximo producto tenga un código único
            contador = maxCodigo + 1;
            lector.close();

            // Actualiza el inventario
            Inventario.productos = new ArrayList<>(productos);

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    // Verifica si un producto con el mismo nombre ya existe (ignorando mayúsculas)
    public static boolean existeProducto(String nombre) {
        for (Producto p : productos) {
            if (p.nombre.equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    // Excepción personalizada para manejar productos duplicados al cargar desde el archivo
    public static class ProductoDuplicadoException extends Exception {
        public ProductoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }
}

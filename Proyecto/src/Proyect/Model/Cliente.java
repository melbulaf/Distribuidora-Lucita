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

public class Cliente {

    private String nombre;
    private String apellido;
    private String telefono;  
    private String correo;
    private String direccion;
    private boolean urgente;

    private static final List<Cliente> clientes = new ArrayList<>();
    private static final File ARCHIVO = new File("src/Proyect/Controler/BD/clientes.txt");

    // Constructor
    public Cliente(String nombre, String apellido, String telefono, String correo, String direccion, boolean urgente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.urgente = urgente;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public boolean esUrgente() { return urgente; }

    // Agregar cliente con validación de duplicados
    public static void agregarCliente(Cliente nuevo) {
        if (!existeCliente(nuevo.telefono, nuevo.correo, nuevo.nombre, nuevo.apellido)) {
            clientes.add(nuevo);
        }
    }

    // Obtener lista
    public static List<Cliente> getClientes() {
        return clientes;
    }

    // Cargar clientes desde archivo
    public static void cargarClientes() {
        clientes.clear();

        try {
            if (!ARCHIVO.exists()) {
                ARCHIVO.getParentFile().mkdirs();
                ARCHIVO.createNewFile();
                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("%%");
                if (partes.length == 6) {
                    String nombre = partes[0];
                    String apellido = partes[1];
                    String telefono = partes[2];
                    String correo = partes[3];
                    String direccion = partes[4];
                    boolean urgente = Boolean.parseBoolean(partes[5]);

                    Cliente c = new Cliente(nombre, apellido, telefono, correo, direccion, urgente);

                    // Validación al cargar: no añadir si ya existe
                    if (!existeCliente(c.telefono, c.correo, c.nombre, c.apellido)) {
                        clientes.add(c);
                    }
                } else {
                    System.err.println("Línea mal formateada: " + linea);
                }
            }

            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar clientes en el archivo
    public static void guardarClientes() {
        try {
            if (!ARCHIVO.exists()) {
                ARCHIVO.getParentFile().mkdirs();
                ARCHIVO.createNewFile();
            }

            PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO));

            for (Cliente c : clientes) {
                escritor.println(c.nombre + "%%" + c.apellido + "%%" + c.telefono + "%%" + c.correo + "%%" + c.direccion + "%%" + c.urgente);
            }

            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Validar duplicados por teléfono, correo o nombre+apellido
    public static boolean existeCliente(String telefono, String correo, String nombre, String apellido) {
        for (Cliente c : clientes) {
            if ((c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) || c.getTelefono().equals(telefono) || c.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }
}

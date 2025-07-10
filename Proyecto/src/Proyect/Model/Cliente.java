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

    // Atributos del cliente
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private boolean esUrgente;

    // Ruta del archivo donde se guardan los clientes
    private static final String ARCHIVO = "src/Proyect/Controler/BD/clientes.txt";

    // Lista estática que contiene todos los clientes registrados en memoria
    private static List<Cliente> listaClientes = new ArrayList<>();

    // Constructor principal
    public Cliente(String nombre, String apellido, String telefono, String correo, String direccion, boolean esUrgente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.esUrgente = esUrgente;
    }

    // Métodos getters usados en los formularios
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public boolean esUrgente() { return esUrgente; }

    // Devuelve la lista completa de clientes cargados en memoria
    public static List<Cliente> getClientes() {
        return listaClientes;
    }

    // Agrega un nuevo cliente a la lista y guarda los cambios en el archivo
    public static void agregarCliente(Cliente c) {
        listaClientes.add(c);
        guardarClientes();
    }

    // Guarda todos los clientes en el archivo
    public static void guardarClientes() {
        try {
            File archivo = new File(ARCHIVO);
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }

            PrintWriter writer = new PrintWriter(new FileWriter(archivo));
            for (Cliente c : listaClientes) {
                writer.println(c.nombre + "%%" + c.apellido + "%%" + c.telefono + "%%" +
                               c.correo + "%%" + c.direccion + "%%" + c.esUrgente);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // Carga los clientes desde el archivo al iniciar el programa
    public static void cargarClientes() {
        listaClientes.clear();
        File archivo = new File(ARCHIVO);

        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("%%");
                if (partes.length == 6) {
                    Cliente c = new Cliente(
                        partes[0], partes[1], partes[2],
                        partes[3], partes[4],
                        Boolean.parseBoolean(partes[5])
                    );
                    listaClientes.add(c);
                }
            }

            lector.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    // Verifica si ya existe un cliente con el mismo teléfono, correo o nombre+apellido
    public static boolean existeCliente(String telefono, String correo, String nombre, String apellido) {
        for (Cliente c : listaClientes) {
            if (c.telefono.equals(telefono) || c.correo.equals(correo) ||
                (c.nombre.equalsIgnoreCase(nombre) && c.apellido.equalsIgnoreCase(apellido))) {
                return true;
            }
        }
        return false;
    }

    // Busca un cliente exacto por nombre y apellido
    public static Cliente buscarCliente(String nombre, String apellido) {
        for (Cliente c : listaClientes) {
            if (c.nombre.equalsIgnoreCase(nombre) && c.apellido.equalsIgnoreCase(apellido)) {
                return c;
            }
        }
        return null;
    }

    // Verifica si existe otro cliente con los mismos datos al editar, ignorando al actual
    public static boolean existeClienteEditando(Cliente actual, String telefono, String correo, String nombre, String apellido) {
        for (Cliente c : listaClientes) {
            if (c != actual &&
                (c.telefono.equals(telefono) || c.correo.equals(correo) ||
                 (c.nombre.equalsIgnoreCase(nombre) && c.apellido.equalsIgnoreCase(apellido)))) {
                return true;
            }
        }
        return false;
    }

    // Edita el valor de un atributo de un cliente específico
    public static boolean editarCliente(Cliente cliente, String parametro, String nuevoValor) {
        if (cliente == null || parametro == null || nuevoValor == null) return false;

        switch (parametro.toLowerCase()) {
            case "nombre":
                cliente.nombre = nuevoValor;
                break;
            case "apellido":
                cliente.apellido = nuevoValor;
                break;
            case "teléfono":
                cliente.telefono = nuevoValor;
                break;
            case "correo":
                cliente.correo = nuevoValor;
                break;
            case "direccion":
                cliente.direccion = nuevoValor;
                break;
            case "urgencia":
                cliente.esUrgente = nuevoValor.equalsIgnoreCase("sí");
                break;
            default:
                return false;
        }

        guardarClientes();
        return true;
    }

    // Elimina un cliente si se encuentra por nombre y apellido
    public static boolean eliminarCliente(String nombre, String apellido) {
        Cliente cliente = buscarCliente(nombre, apellido);
        if (cliente != null) {
            listaClientes.remove(cliente);
            guardarClientes();
            return true;
        }
        return false;
    }

    // Permite forzar la recarga de clientes desde el archivo manualmente
    public static void cargarClientesDesdeArchivo() {
        cargarClientes();
    }
}

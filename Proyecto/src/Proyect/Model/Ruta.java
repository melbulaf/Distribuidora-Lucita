package Proyect.Model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;

public class Ruta {
    public String nombre;
    public String dia;

    public Ruta(String nombre, String dia) {
        this.nombre = nombre;
        this.dia = dia;
    }

    public static ArrayList<Ruta> rutasPorDefecto() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        rutas.add(new Ruta("Centro", "Lunes"));
        rutas.add(new Ruta("Norte", "Martes"));
        rutas.add(new Ruta("Sur", "Miércoles"));
        rutas.add(new Ruta("Oeste", "Viernes"));
        return rutas;
    }

    public static Ruta rutaDeHoy(ArrayList<Ruta> rutas) {
        String hoy = LocalDate.now().getDayOfWeek()
            .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
        hoy = normalizaDia(hoy);
        for (Ruta r : rutas) {
            if (normalizaDia(r.dia).equals(hoy)) return r;
        }
        return null;
    }

    public static String mensajeCabecera(ArrayList<Ruta> rutas) {
        String hoy = LocalDate.now().getDayOfWeek()
            .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
        hoy = hoy.substring(0,1).toUpperCase() + hoy.substring(1).toLowerCase();
        if (!esHoyLaboral()) {
            return "NO HAY ENTREGAS HOY (" + hoy + ")";
        }
        Ruta r = rutaDeHoy(rutas);
        if (r == null) {
            return "No hay ruta programada para hoy.";
        }
        return "Ruta del día: " + r.nombre + " (" + r.dia + ")";
    }

    public static String normalizaDia(String dia) {
        return Normalizer.normalize(dia, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

    public static boolean esHoyLaboral() {
        String hoy = LocalDate.now().getDayOfWeek()
            .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
        hoy = normalizaDia(hoy);
        return hoy.equals("lunes") || hoy.equals("martes") || hoy.equals("miercoles") || hoy.equals("miércoles") || hoy.equals("viernes");
    }

    // SOLO AGREGA EJEMPLOS PARA LA RUTA DE HOY Y FECHA DE HOY
    public static void inicializarPedidosEjemplo() {
        String[] dias = {"Lunes", "Martes", "Miércoles", "Viernes"};
        String[] rutas = {"Centro", "Norte", "Sur", "Oeste"};
        String[][] clientes = {
            {"Ana López", "Calle 12 #45-23"},
            {"Carlos Méndez", "Av. Libertad 100"},
            {"María Torres", "Cra 20 #10-15"},
            {"Luis Gómez", "Barrio Nuevo, Casa 8"}
        };
        String[][] clientesUrgente = {
            {"Pedro Ruiz", "Edificio Oasis, apt 303"},
            {"Lucía Vargas", "Residencial El Lago, Torre B"},
            {"Sofía Herrera", "Barrio Jardines, Calle 8"},
            {"Miguel Pardo", "Carrera 7 #55-11"}
        };
        String[] productos = {"Pan", "Leche", "Queso", "Huevos"};
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String fechaHoy = sdf.format(new java.util.Date());

        // Determina el día de hoy (ej: "jueves")
        String diaHoy = new java.text.SimpleDateFormat("EEEE", new java.util.Locale("es", "ES")).format(new java.util.Date());
        diaHoy = diaHoy.substring(0, 1).toUpperCase() + diaHoy.substring(1).toLowerCase();

        // Busca el índice de la ruta de hoy
        int indiceHoy = -1;
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(diaHoy)) {
                indiceHoy = i;
                break;
            }
        }
        // NO crear ejemplos si hoy es martes
        if (indiceHoy == 1) return; // 1 es Martes

        if (indiceHoy == -1) return; // Hoy no hay ruta de ejemplo

        // Crea productos de ejemplo en inventario si no existen
        for (String prod : productos) {
            boolean existe = false;
            for (Proyect.Model.Producto p : Proyect.Model.Inventario.getInstancia().obtenerProductos()) {
                if (p.nombre.equalsIgnoreCase(prod)) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                new Proyect.Model.Producto(prod, "Alimentos", 1000, 800, 50);
            }
        }

        // Pedido normal
        if (!existePedidoEjemplo(productos[indiceHoy], clientes[indiceHoy][0], fechaHoy)) {
            Proyect.Model.RegistrarPedido.listaDePedidos.add(new Proyect.Model.RegistrarPedido(
                buscarProductoPorNombre(productos[indiceHoy]), 10, clientes[indiceHoy][0] + " - " + clientes[indiceHoy][1], fechaHoy
            ));
        }
        // Pedido urgente
        if (!existePedidoEjemplo(productos[indiceHoy], clientesUrgente[indiceHoy][0], fechaHoy)) {
            Proyect.Model.RegistrarPedido p = new Proyect.Model.RegistrarPedido(
                buscarProductoPorNombre(productos[indiceHoy]), 5, clientesUrgente[indiceHoy][0] + " - " + clientesUrgente[indiceHoy][1], fechaHoy
            );
            Proyect.Model.RegistrarPedido.listaDePedidos.add(p);
        }
    }

    private static boolean existePedidoEjemplo(String producto, String nombreCliente, String fecha) {
        for (Proyect.Model.RegistrarPedido p : Proyect.Model.RegistrarPedido.listaDePedidos) {
            if (p.getProducto().nombre.equalsIgnoreCase(producto)
                && p.getcliente().startsWith(nombreCliente)
                && p.getFecha().equals(fecha)) {
                return true;
            }
        }
        return false;
    }

    private static Proyect.Model.Producto buscarProductoPorNombre(String nombre) {
        for (Proyect.Model.Producto p : Proyect.Model.Inventario.getInstancia().obtenerProductos()) {
            if (p.nombre.equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }
}
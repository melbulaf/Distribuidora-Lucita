/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author pc
 */

public class ResumenDeGanancias {
    private Producto nombreDelProducto;
    private Producto precioDeVenta;
    private Producto precioDeCompra;
    private Producto ganancia;

    public static List<String> nombresDePedidos() {
    List<String> datosPedidosN = new ArrayList<>();
    

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String nombre = partes[0].trim();
                String fecha = partes[partes.length - 1].trim(); // Último campo como fecha
                datosPedidosN.add(nombre);
                
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    for(String x : datosPedidosN){
        
    }

    return datosPedidosN;
}
    
    public static List<String> fechasDePedidos(){
    List<String> datosPedidosF = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String fecha = partes[partes.length - 1].trim(); // Último campo como fecha
                datosPedidosF.add(fecha);
                
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return datosPedidosF;
}
    
    public static List<String> clientesDePedidos() {
    List<String> datosClientes = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 3) {
                String cliente = partes[2].trim(); // Tercer campo como cliente
                datosClientes.add(cliente);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return datosClientes;
}
    
    public static List<Double> preciosCompraProductos(List<String> nombresProductos) {
    List<Double> preciosCompra = new ArrayList<>();

    for (String nombre : nombresProductos) {
        Producto productoEncontrado = buscarProductoPorNombre(nombre);
        if (productoEncontrado != null) {
            preciosCompra.add(productoEncontrado.precioC);
        } else {
            preciosCompra.add(0.0); // o cualquier valor por defecto si no se encuentra
        }
    }

    return preciosCompra;
}
    
    public static List<Double> preciosVentaProductos(List<String> nombresProductos) {
    List<Double> preciosVenta = new ArrayList<>();

    for (String nombre : nombresProductos) {
        Producto producto = buscarProductoPorNombre(nombre);

        if (producto != null) {
            preciosVenta.add(producto.precio);
        } else {
            preciosVenta.add(0.0); // Valor por defecto si no se encuentra
        }
    }

    return preciosVenta;
}
    
    
    public static List<Integer> cantidadTotalDeProductosVendidos() {
    List<Integer> cantidades = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\pc\\Documents\\Distribuidora-Lucita\\Proyecto\\src\\Proyect\\Controler\\BD\\RegistrarPedido.txt"))) {
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            if (partes.length >= 2) {
                try {
                    int cantidad = Integer.parseInt(partes[1].trim());
                    cantidades.add(cantidad);
                } catch (NumberFormatException e) {
                    // Maneja la línea con formato incorrecto (opcional)
                    System.err.println("Cantidad inválida en línea: " + linea);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return cantidades;
}
    
    
    public static List<Double> ingresosPorProducto() {
    List<String> nombresProductos = nombresDePedidos();
    List<Double> preciosVenta = preciosVentaProductos(nombresProductos); 
    List<Integer> cantidades = cantidadTotalDeProductosVendidos(); 

    List<Double> ingresos = new ArrayList<>();

    for (int i = 0; i < nombresProductos.size(); i++) {
        double ingreso = preciosVenta.get(i) * cantidades.get(i);
        ingresos.add(ingreso);
    }

    return ingresos;
}

    
    
    public static void agregarProductosDeHoyATabla(DefaultTableModel dtm) {
    String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();
    List<String> clientes = clientesDePedidos();
    List<Double> compra = preciosCompraProductos(nombres);
    List<Double> venta = preciosVentaProductos(nombres);
    List<Integer> cantidad = cantidadTotalDeProductosVendidos();
    List<Double> ingresosPorProducto = ingresosPorProducto();
    

    for (int i = 0; i < fechas.size(); i++) {
        if (fechas.get(i).equals(fechaHoy)) {
            Object[] fila = new Object[6];
            fila[0] = nombres.get(i);
            fila[1] = clientes.get(i); 
            fila[2] = cantidad.get(i);
            fila[3] = compra.get(i);
            fila[4] = venta.get(i);
            fila[5] = ingresosPorProducto.get(i);
            dtm.insertRow(0, fila); 
        }
    }
    
    
}
    
    public static Producto buscarProductoPorNombre(String nombreProducto) {
    for (Producto producto : Inventario.productos) {
        if (producto.nombre.equalsIgnoreCase(nombreProducto.trim())) {
            return producto;
        }
    }
    return null;
}
    
    
    //actualizar tabla para un dia
    
    public static List<Double> calcularTotalIngresosDelDia() {
    List<Double> ingresosIndividuales = ingresosPorProducto(); // método existente
    List<String> fechas = fechasDePedidos(); // método que obtiene fechas
    
    String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    double total = 0.0;

    for (int i = 0; i < fechas.size(); i++) {
        if (fechas.get(i).equals(fechaHoy)) {
            total += ingresosIndividuales.get(i);
        }
    }

    List<Double> resultado = new ArrayList<>();
    resultado.add(total);
    return resultado;
    }

    public static void agregarProductostablaTotal(DefaultTableModel dtm2) {
    List<Double> Total = calcularTotalIngresosDelDia();
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm2.insertRow(0, fila); 
    }
           
    public static List<Double> utilidadNetaComoLista(List<String> nombresProductos) {
    List<Double> totalVentas = calcularTotalIngresosDelDia();
    double total = totalVentas.get(0); // Obtenemos el total de ventas
    double utilidad = total * 0.15;    // Calculamos el 15%

    List<Double> utilidadLista = new ArrayList<>();
    utilidadLista.add(utilidad);
    return utilidadLista;
    }

    public static void agregarProductostablaUtilidadNeta(DefaultTableModel dtm3) {
    List<Double> Total = utilidadNetaComoLista(nombresDePedidos());
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm3.insertRow(0, fila); 
    }
    
    
    //actualizar tabla para cuatro dias
    
    public static void agregarProductosDe4(DefaultTableModel dtm) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    
    // Generar lista con hoy + los próximos 3 días
    List<String> fechasValidas = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
        fechasValidas.add(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1); // avanzar un día
    }

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();
    List<String> clientes = clientesDePedidos();
    List<Double> compra = preciosCompraProductos(nombres);
    List<Double> venta = preciosVentaProductos(nombres);
    List<Integer> cantidad = cantidadTotalDeProductosVendidos();
    List<Double> ingresosPorProducto = ingresosPorProducto();

    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            Object[] fila = new Object[6];
            fila[0] = nombres.get(i);
            fila[1] = clientes.get(i);
            fila[2] = cantidad.get(i);
            fila[3] = compra.get(i);
            fila[4] = venta.get(i);
            fila[5] = ingresosPorProducto.get(i);
            dtm.insertRow(0, fila);
        }
    }
}
    
    public static List<Double> calcularTotalIngresosProximosCuatroDias() {
    List<Double> ingresosIndividuales = ingresosPorProducto(); // método ya existente
    List<String> fechas = fechasDePedidos(); // lista de fechas de los pedidos

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calHoy = Calendar.getInstance();
    
    // Generar las 4 fechas: hoy + 3 días siguientes
    Set<String> fechasValidas = new HashSet<>();
    for (int i = 0; i < 4; i++) {
        fechasValidas.add(sdf.format(calHoy.getTime()));
        calHoy.add(Calendar.DAY_OF_MONTH, 1);
    }

    double total = 0.0;
    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            total += ingresosIndividuales.get(i);
        }
    }

    List<Double> resultado = new ArrayList<>();
    resultado.add(total);
    return resultado;
    }
    
    public static void agregarProductostablaTotalDe4(DefaultTableModel dtm2) {
    List<Double> Total = calcularTotalIngresosProximosCuatroDias();
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm2.insertRow(0, fila); 
    }
    
    public static List<Double> utilidadNetaComoListaDe4(List<String> nombresProductos) {
    List<Double> totalVentas = calcularTotalIngresosProximosCuatroDias();
    double total = totalVentas.get(0); // Obtenemos el total de ventas
    double utilidad = total * 0.15;    // Calculamos el 15%

    List<Double> utilidadLista = new ArrayList<>();
    utilidadLista.add(utilidad);
    return utilidadLista;
    }
    
    public static void agregarProductostablaUtilidadNetaDe4(DefaultTableModel dtm3) {
    List<Double> Total = utilidadNetaComoListaDe4(nombresDePedidos());
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm3.insertRow(0, fila); 
    }
    
    
    
    //actualizar tabla para 7 dias
    
    public static void agregarProductosDe7(DefaultTableModel dtm) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    
    // Generar lista con hoy + los próximos 3 días
    List<String> fechasValidas = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
        fechasValidas.add(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1); // avanzar un día
    }

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();
    List<String> clientes = clientesDePedidos();
    List<Double> compra = preciosCompraProductos(nombres);
    List<Double> venta = preciosVentaProductos(nombres);
    List<Integer> cantidad = cantidadTotalDeProductosVendidos();
    List<Double> ingresosPorProducto = ingresosPorProducto();

    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            Object[] fila = new Object[6];
            fila[0] = nombres.get(i);
            fila[1] = clientes.get(i);
            fila[2] = cantidad.get(i);
            fila[3] = compra.get(i);
            fila[4] = venta.get(i);
            fila[5] = ingresosPorProducto.get(i);
            dtm.insertRow(0, fila);
        }
    }
}
    
    public static List<Double> calcularTotalIngresosProximos7Dias() {
    List<Double> ingresosIndividuales = ingresosPorProducto(); // método ya existente
    List<String> fechas = fechasDePedidos(); // lista de fechas de los pedidos

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calHoy = Calendar.getInstance();
    
    // Generar las 4 fechas: hoy + 3 días siguientes
    Set<String> fechasValidas = new HashSet<>();
    for (int i = 0; i < 7; i++) {
        fechasValidas.add(sdf.format(calHoy.getTime()));
        calHoy.add(Calendar.DAY_OF_MONTH, 1);
    }

    double total = 0.0;
    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            total += ingresosIndividuales.get(i);
        }
    }

    List<Double> resultado = new ArrayList<>();
    resultado.add(total);
    return resultado;
    }
    
    public static void agregarProductostablaTotalDe7(DefaultTableModel dtm2) {
    List<Double> Total = calcularTotalIngresosProximos7Dias();
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm2.insertRow(0, fila); 
    }
    
    public static List<Double> utilidadNetaComoListaDe7(List<String> nombresProductos) {
    List<Double> totalVentas = calcularTotalIngresosProximos7Dias();
    double total = totalVentas.get(0); // Obtenemos el total de ventas
    double utilidad = total * 0.15;    // Calculamos el 15%

    List<Double> utilidadLista = new ArrayList<>();
    utilidadLista.add(utilidad);
    return utilidadLista;
    }
    
    public static void agregarProductostablaUtilidadNetaDe7(DefaultTableModel dtm3) {
    List<Double> Total = utilidadNetaComoListaDe7(nombresDePedidos());
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm3.insertRow(0, fila); 
    }
    
    
    //actualizar tabla para 15 dias
    
    
    public static void agregarProductosDe15(DefaultTableModel dtm) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    
    // Generar lista con 15 días
    List<String> fechasValidas = new ArrayList<>();
    for (int i = 0; i < 15; i++) {
        fechasValidas.add(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1); // avanzar un día
    }

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();
    List<String> clientes = clientesDePedidos();
    List<Double> compra = preciosCompraProductos(nombres);
    List<Double> venta = preciosVentaProductos(nombres);
    List<Integer> cantidad = cantidadTotalDeProductosVendidos();
    List<Double> ingresosPorProducto = ingresosPorProducto();

    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            Object[] fila = new Object[6];
            fila[0] = nombres.get(i);
            fila[1] = clientes.get(i);
            fila[2] = cantidad.get(i);
            fila[3] = compra.get(i);
            fila[4] = venta.get(i);
            fila[5] = ingresosPorProducto.get(i);
            dtm.insertRow(0, fila);
        }
    }
}
    
    public static List<Double> calcularTotalIngresosProximos15Dias() {
    List<Double> ingresosIndividuales = ingresosPorProducto(); 
    List<String> fechas = fechasDePedidos(); 

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calHoy = Calendar.getInstance();
    
    // Generar las 4 fechas: hoy + 3 días siguientes
    Set<String> fechasValidas = new HashSet<>();
    for (int i = 0; i < 15; i++) {
        fechasValidas.add(sdf.format(calHoy.getTime()));
        calHoy.add(Calendar.DAY_OF_MONTH, 1);
    }

    double total = 0.0;
    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            total += ingresosIndividuales.get(i);
        }
    }

    List<Double> resultado = new ArrayList<>();
    resultado.add(total);
    return resultado;
    }
    
    public static void agregarProductostablaTotalDe15(DefaultTableModel dtm2) {
    List<Double> Total = calcularTotalIngresosProximos15Dias();
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm2.insertRow(0, fila); 
    }
    
    public static List<Double> utilidadNetaComoListaDe15(List<String> nombresProductos) {
    List<Double> totalVentas = calcularTotalIngresosProximos15Dias();
    double total = totalVentas.get(0); // Obtenemos el total de ventas
    double utilidad = total * 0.15;    // Calculamos el 15%

    List<Double> utilidadLista = new ArrayList<>();
    utilidadLista.add(utilidad);
    return utilidadLista;
    }
    
    public static void agregarProductostablaUtilidadNetaDe15(DefaultTableModel dtm3) {
    List<Double> Total = utilidadNetaComoListaDe15(nombresDePedidos());
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm3.insertRow(0, fila); 
    }
    
    
    //actualizar tabla para 30 dias
    
    public static void agregarProductosDe30(DefaultTableModel dtm) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    
    // Generar lista próximos 30 días
    List<String> fechasValidas = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
        fechasValidas.add(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, 1); // avanzar un día
    }

    List<String> nombres = nombresDePedidos();
    List<String> fechas = fechasDePedidos();
    List<String> clientes = clientesDePedidos();
    List<Double> compra = preciosCompraProductos(nombres);
    List<Double> venta = preciosVentaProductos(nombres);
    List<Integer> cantidad = cantidadTotalDeProductosVendidos();
    List<Double> ingresosPorProducto = ingresosPorProducto();

    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            Object[] fila = new Object[6];
            fila[0] = nombres.get(i);
            fila[1] = clientes.get(i);
            fila[2] = cantidad.get(i);
            fila[3] = compra.get(i);
            fila[4] = venta.get(i);
            fila[5] = ingresosPorProducto.get(i);
            dtm.insertRow(0, fila);
        }
    }
}
    
    public static List<Double> calcularTotalIngresosProximos30Dias() {
    List<Double> ingresosIndividuales = ingresosPorProducto(); 
    List<String> fechas = fechasDePedidos(); 

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calHoy = Calendar.getInstance();
    
    // Generar las 4 fechas: hoy + 3 días siguientes
    Set<String> fechasValidas = new HashSet<>();
    for (int i = 0; i < 30; i++) {
        fechasValidas.add(sdf.format(calHoy.getTime()));
        calHoy.add(Calendar.DAY_OF_MONTH, 1);
    }

    double total = 0.0;
    for (int i = 0; i < fechas.size(); i++) {
        if (fechasValidas.contains(fechas.get(i))) {
            total += ingresosIndividuales.get(i);
        }
    }

    List<Double> resultado = new ArrayList<>();
    resultado.add(total);
    return resultado;
    }
    
    public static void agregarProductostablaTotalDe30(DefaultTableModel dtm2) {
    List<Double> Total = calcularTotalIngresosProximos30Dias();
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm2.insertRow(0, fila); 
    }
    
    public static List<Double> utilidadNetaComoListaDe30(List<String> nombresProductos) {
    List<Double> totalVentas = calcularTotalIngresosProximos30Dias();
    double total = totalVentas.get(0); // Obtenemos el total de ventas
    double utilidad = total * 0.15;    // Calculamos el 15%

    List<Double> utilidadLista = new ArrayList<>();
    utilidadLista.add(utilidad);
    return utilidadLista;
    }
    
    public static void agregarProductostablaUtilidadNetaDe30(DefaultTableModel dtm3) {
    List<Double> Total = utilidadNetaComoListaDe30(nombresDePedidos());
    
            Object[] fila = new Object[1];
            fila[0] = Total.get(0);
            
            dtm3.insertRow(0, fila); 
    }
    
    
}


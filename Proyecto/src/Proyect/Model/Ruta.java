/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;

/**
 *
 * @author MELANIE BULA FUENTES
 */

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.time.LocalDate;

public class Ruta {
    public String nombre;
    public String dia; // Ej: "Lunes", "Martes"...

    public Ruta(String nombre, String dia) {
        this.nombre = nombre;
        this.dia = dia;
    }
    
    // Rutas por defectoAdd commentMore actions
    public static ArrayList<Ruta> rutasPorDefecto() {
        ArrayList<Ruta> rutas = new ArrayList<>();
        rutas.add(new Ruta("Centro", "Lunes"));
        rutas.add(new Ruta("Norte", "Martes"));
        rutas.add(new Ruta("Sur", "Miércoles"));
        rutas.add(new Ruta("Este", "Jueves"));
        rutas.add(new Ruta("Oeste", "Viernes"));
        return rutas;
    }

    // Encuentra la ruta del día actual
    public static Ruta rutaDeHoy(ArrayList<Ruta> rutas) {
        String hoy = LocalDate.now().getDayOfWeek()
            .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
        hoy = normalizaDia(hoy);
        for (Ruta r : rutas) {
            if (normalizaDia(r.dia).equals(hoy)) return r;
        }
        return null;
    }

    // Para mostrar en la cabecera
    public static String mensajeCabecera(ArrayList<Ruta> rutas) {
        Ruta r = rutaDeHoy(rutas);
        if (r == null) {
            return "No hay ruta programada para hoy.";
        }
        return "Ruta del día: " + r.nombre + " (" + r.dia + ")";
    }

    // Normaliza el nombre del día para comparar
    public static String normalizaDia(String dia) {
        return Normalizer.normalize(dia, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

    // Checa si hoy es laborable (hay ruta programada)
    public static boolean esHoyLaboral() {
        String hoy = LocalDate.now().getDayOfWeek()
            .getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
        hoy = normalizaDia(hoy);
        return !hoy.equals("sabado") && !hoy.equals("sábado") && !hoy.equals("domingo");
    }
}
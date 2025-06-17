/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;

/**
 *
 * @author MELANIE BULA FUENTES
 */


import Proyect.Model.Cliente;
import java.util.List;

public class Ruta {
    public String dia;
    public String nombreRuta;
    public List<Cliente> clientes;

    public Ruta(String dia, String nombreRuta, List<Cliente> clientes) {
        this.dia = dia;
        this.nombreRuta = nombreRuta;
        this.clientes = clientes;
    }
}
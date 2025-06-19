/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;

/**
 *
 * @author MELANIE BULA FUENTES
 */
import java.util.List;

public class Cliente {
    public String nombre;
    public List<Pedido> pedidos;

    public Cliente(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

import java.time.LocalDateTime;

/**
 *
 * @author tolan
 */
public class Compra {
    private int id;
    private int clienteId; //El cliente que realizó la compra
    private int boletoId; //El boleto que fue comprado
    private LocalDateTime fechaHora;
    private double total;
    private String estado;

    public Compra() {}

    public Compra(int clienteId, int boletoId, LocalDateTime fechaHora, double total) {
        this.clienteId = clienteId;
        this.boletoId = boletoId;
        this.fechaHora = fechaHora;
        this.total = total;
        this.estado = "COMPRADO";
    }

    public int getId() { 
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClienteId() {
        return clienteId;
    }
    public void setClienteId(int clienteId) { 
        this.clienteId = clienteId;
    }
    public int getBoletoId() { 
        return boletoId;
    }
    public void setBoletoId(int boletoId) { 
        this.boletoId = boletoId; 
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFecha(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    public double getTotal() { 
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ID de la Compra: " + id + ", ID del Comprador" + clienteId + ", ID del Boleto=" + boletoId;
    }
}

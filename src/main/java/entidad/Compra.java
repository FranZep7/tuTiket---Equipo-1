/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author tolan
 */
public class Compra {
    private int id;
    private int clienteId; //El cliente que realizó la compra
    private int boletoId; //El boleto que fue comprado
    private String fecha;
    private String hora;
    private double total;
    private String estado;

    public Compra() {}

    public Compra(int clienteId, int boletoId, String fecha, String hora, double total) {
        this.clienteId = clienteId;
        this.boletoId = boletoId;
        this.fecha = fecha;
        this.hora = hora;
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
    public void setBoletoId(int boletoId) { this.boletoId = boletoId; }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() 
    { return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
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

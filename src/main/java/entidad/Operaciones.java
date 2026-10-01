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
public class Operaciones {
    private int id;
    private int cuentaClienteId;
    private String tipoOperacion;
    private double monto;
    private LocalDateTime fechaHora;

    public Operaciones(){
    }
    public Operaciones(int cuentaClienteId, String tipoOperacion, double monto, LocalDateTime fechaHora) {
        this.cuentaClienteId = cuentaClienteId;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCuentaClienteId() {
        return cuentaClienteId;
    }
    public void setCuentaClienteId(int cuentaClienteId) {
        this.cuentaClienteId = cuentaClienteId;
    }
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "ID de la Operación: " + id + ", ID de la Cuenta del cliente: " + cuentaClienteId + ", Tipo de Operación: " + tipoOperacion + ", Monto: " + monto + ", Fecha y Hora:" + fechaHora;
    }
}

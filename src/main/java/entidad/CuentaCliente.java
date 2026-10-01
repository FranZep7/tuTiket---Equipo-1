/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author tolan
 */
public class CuentaCliente {
    private int id;
    private int clienteId; //Cliente al que pertenece la cuenta
    private String banco;
    private String numeroCuenta;
    private double saldo;

    public CuentaCliente() {}

    public CuentaCliente(int clienteId, String banco, String numeroCuenta, double saldo) {
        this.clienteId = clienteId;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
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
    public String getBanco() { 
        return banco;
    }
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getNumeroCuenta() { 
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) { 
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ID de la Cuenta(Cliente): " + id + ", Banco:" + banco;
    }
}

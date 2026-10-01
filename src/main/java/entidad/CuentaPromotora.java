/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author tolan
 */
public class CuentaPromotora {
    private int id;
    private int promotoraId; // A qué promotora pertenece ESTA cuenta
    private String banco;
    private String numeroCuenta;
    private double saldo;

    public CuentaPromotora() {}

    public CuentaPromotora(int promotoraId, String banco, String numeroCuenta, double saldo) {
        this.promotoraId = promotoraId;
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
    public int getPromotoraId() {
        return promotoraId; 
    }
    public void setPromotoraId(int promotoraId) {
        this.promotoraId = promotoraId;
    }
    public String getBanco() {
        return banco;
    }
    public void setBanco(String banco) { this.banco = banco; }
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
        return "ID de la Cuenta(Promotora): " + id + ", Banco: " + banco;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import entidad.CuentaCliente;

/**
 *
 * @author tolan
 */
public class ValidacionCuenta {
    public static void validar(CuentaCliente cuenta) throws ValidacionException {
        Validador.requerido(cuenta.getBanco(), "banco");
        Validador.requerido(cuenta.getNumeroCuenta(), "número de cuenta");
        Validador.soloNumeros(cuenta.getNumeroCuenta(), "número de cuenta");
        Validador.longitudMinima(cuenta.getNumeroCuenta(), 10, "número de cuenta");
        Validador.longitudMaxima(cuenta.getNumeroCuenta(), 10, "número de cuenta");
        Validador.noNegativo(cuenta.getSaldo(), "saldo");
    }
}

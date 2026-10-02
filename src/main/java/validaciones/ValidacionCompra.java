/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import entidad.Boleto;
import entidad.Compra;
import entidad.CuentaCliente;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author tolan
 */
public class ValidacionCompra {
    public static void validarBoletoDisponible(Boleto boleto) throws ValidacionException {
        if (boleto == null) {
            throw new ValidacionException("El boleto no existe.");
        }
        if (!"DISPONIBLE".equals(boleto.getEstatus())) {
            throw new ValidacionException("El boleto ya no está disponible.");
        }
    }

    public static void validarSaldoSuficiente(List<CuentaCliente> cuentas, double costo) throws ValidacionException {
        if (cuentas == null || cuentas.isEmpty()) {
            throw new ValidacionException("El cliente no tiene cuentas bancarias.");
        }
        for (CuentaCliente cuenta : cuentas) {
            if (cuenta.getSaldo() >= costo) {
                return;
            }
        }
        throw new ValidacionException("Saldo insuficiente en las cuentas del cliente.");
    }

    public static void validarCancelacion(Compra compra) throws ValidacionException {
        if (compra == null) {
            throw new ValidacionException("La compra no existe.");
        }
        if ("CANCELADO".equals(compra.getEstado())) {
            throw new ValidacionException("La compra ya fue cancelada.");
        }
        if (compra.getFechaHora().plusHours(24).isBefore(LocalDateTime.now())) {
            throw new ValidacionException("Solo puedes cancelar dentro de las 24 horas posteriores a la compra.");
        }
    }

    public static void validarClienteParaEvento(int edadCliente, int edadMinima) throws ValidacionException {
        if (edadCliente < edadMinima) {
            throw new ValidacionException("El cliente no cumple la edad mínima del evento.");
        }
    }
}
    

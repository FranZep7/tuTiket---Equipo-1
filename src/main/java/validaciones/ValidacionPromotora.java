/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import entidad.Promotora;

/**
 *
 * @author tolan
 */
public class ValidacionPromotora {
    public static void validar(Promotora promotora) throws ValidacionException {
        Validador.requerido(promotora.getNombreComercial(), "nombre comercial");
        Validador.longitudMaxima(promotora.getNombreComercial(), 80, "nombre comercial");

        Validador.requerido(promotora.getCalle(), "calle");
        Validador.requerido(promotora.getNumero(), "número");
        Validador.requerido(promotora.getColonia(), "colonia");
        Validador.requerido(promotora.getCiudad(), "ciudad");
        Validador.requerido(promotora.getEstado(), "estado");
    }
}

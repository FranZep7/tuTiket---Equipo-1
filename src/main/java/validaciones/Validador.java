/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

/**
 *
 * @author tolan
 */
public class Validador {
    private Validador() {
    }

    public static void requerido(String valor, String campo) throws ValidacionException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidacionException("El campo " + campo + " es obligatorio.");
        }
    }

    public static void soloLetras(String valor, String campo) throws ValidacionException {
        requerido(valor, campo);
        if (!valor.trim().matches("[A-Za-zÁÉÍÓÚáéíóúÑñüÜ ]+")) {
            throw new ValidacionException("El campo " + campo + " solo puede contener letras y espacios.");
        }
    }

    public static void soloNumeros(String valor, String campo) throws ValidacionException {
        requerido(valor, campo);
        if (!valor.trim().matches("\\d+")) {
            throw new ValidacionException("El campo " + campo + " solo puede contener dígitos.");
        }
    }

    public static void longitudMinima(String valor, int min, String campo) throws ValidacionException {
        requerido(valor, campo);
        if (valor.trim().length() < min) {
            throw new ValidacionException("El campo " + campo + " debe tener al menos " + min + " caracteres.");
        }
    }

    public static void longitudMaxima(String valor, int max, String campo) throws ValidacionException {
        if (valor != null && valor.trim().length() > max) {
            throw new ValidacionException("El campo " + campo + " no puede exceder " + max + " caracteres.");
        }
    }

    public static void mayorQueCero(double valor, String campo) throws ValidacionException {
        if (valor <= 0) {
            throw new ValidacionException("El campo " + campo + " debe ser mayor que cero.");
        }
    }

    public static void noNegativo(double valor, String campo) throws ValidacionException {
        if (valor < 0) {
            throw new ValidacionException("El campo " + campo + " no puede ser negativo.");
        }
    }
}

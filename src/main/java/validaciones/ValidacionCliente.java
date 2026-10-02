/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import entidad.Cliente;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author tolan
 */
public class ValidacionCliente {
    private static final int EDAD_MINIMA = 18;

    public static void validar(Cliente cliente) throws ValidacionException {
        validarUsuario(cliente.getUsuario());
        validarContrasena(cliente.getContrasena());
        validarNombreCompleto(cliente);
        validarFechaNacimiento(cliente.getFechaNacimiento());
    }

    public static void validarUsuario(String usuario) throws ValidacionException {
        Validador.requerido(usuario, "usuario");
        Validador.longitudMinima(usuario, 4, "usuario");
        Validador.longitudMaxima(usuario, 20, "usuario");
        if (!usuario.matches("[A-Za-z0-9_]+")) {
            throw new ValidacionException("El usuario solo puede contener letras, números y guión bajo.");
        }
    }

    public static void validarContrasena(String contrasena) throws ValidacionException {
        Validador.requerido(contrasena, "contraseña");
        Validador.longitudMinima(contrasena, 6, "contraseña");
        Validador.longitudMaxima(contrasena, 30, "contraseña");
    }

    public static void validarNombreCompleto(Cliente cliente) throws ValidacionException {
        Validador.soloLetras(cliente.getNombres(), "nombres");
        Validador.soloLetras(cliente.getApellidoPaterno(), "apellido paterno");
        Validador.soloLetras(cliente.getApellidoMaterno(), "apellido materno");
    }

    public static void validarFechaNacimiento(LocalDate fecha) throws ValidacionException {
        if (fecha == null) {
            throw new ValidacionException("La fecha de nacimiento es obligatoria.");
        }
        if (fecha.isAfter(LocalDate.now())) {
            throw new ValidacionException("La fecha de nacimiento no puede ser futura.");
        }
        int edad = Period.between(fecha, LocalDate.now()).getYears();
        if (edad < EDAD_MINIMA) {
            throw new ValidacionException("El cliente debe ser mayor de " + EDAD_MINIMA + " años.");
        }
        if (edad > 120) {
            throw new ValidacionException("La fecha de nacimiento no es válida.");
        }
    }

    public static void validarEdadParaEvento(LocalDate fechaNacimiento, int edadMinima) throws ValidacionException {
        if (fechaNacimiento == null) {
            throw new ValidacionException("El cliente no tiene fecha de nacimiento registrada.");
        }
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < edadMinima) {
            throw new ValidacionException("El evento requiere una edad mínima de " + edadMinima + " años.");
        }
    }

    public static void validarCredenciales(String usuario, String contrasena) throws ValidacionException {
        Validador.requerido(usuario, "usuario");
        Validador.requerido(contrasena, "contraseña");
    }
}

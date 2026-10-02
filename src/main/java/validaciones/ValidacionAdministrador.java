/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import entidad.Administrador;

/**
 *
 * @author tolan
 */
public class ValidacionAdministrador {
    public static void validar(Administrador admin) throws ValidacionException {
        Validador.requerido(admin.getUsuario(), "usuario");
        Validador.longitudMinima(admin.getUsuario(), 4, "usuario");
        Validador.requerido(admin.getContrasena(), "contraseña");
        Validador.longitudMinima(admin.getContrasena(), 6, "contraseña");
        Validador.requerido(admin.getNombres(), "nombre completo");
    }
}

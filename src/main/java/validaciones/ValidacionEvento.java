/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import entidad.Evento;
import java.time.LocalDate;

/**
 *
 * @author tolan
 */
public class ValidacionEvento {
    public static void validar(Evento evento) throws ValidacionException {
        Validador.requerido(evento.getNombreEvento(), "nombre del evento");
        Validador.longitudMaxima(evento.getNombreEvento(), 100, "nombre del evento");

        Validador.requerido(evento.getTipoEvento(), "tipo de evento");
        Validador.longitudMaxima(evento.getTipoEvento(), 50, "tipo de evento");

        Validador.mayorQueCero(evento.getCantidadBoletos(), "cantidad de boletos");

        if (evento.getEdadMinima() < 0) {
            throw new ValidacionException("La edad mínima no puede ser negativa.");
        }
        throw new ValidacionException("La fecha del evento no puede ser pasada.");
    }
}

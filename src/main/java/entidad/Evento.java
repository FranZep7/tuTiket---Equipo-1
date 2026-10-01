/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author tolan
 */
public class Evento {
     private int id;
    private int promotoraId; //La promotora que creó este evento
    private String nombreEvento;
    private String tipoEvento;
    private int edadMinima;
    private String imagenPromocional;
    private int cantidadBoletos;

    public Evento() {
    }

    public Evento(int promotoraId, String nombreEvento, String tipoEvento,int edadMinima, String imagenPromocional, int cantidadBoletos) {
        this.promotoraId = promotoraId;
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.edadMinima = edadMinima;

        this.imagenPromocional = imagenPromocional;
        this.cantidadBoletos = cantidadBoletos;
    }

    public int getId() {
        return id; 
    }
    public void setId(int id) 
    { this.id = id; 
    }
    public int getPromotoraId() {
        return promotoraId;
    }
    public void setPromotoraId(int promotoraId) {
        this.promotoraId = promotoraId;
    }
    public String getNombreEvento() {
        return nombreEvento;
    }
    public void setNombre(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    public String getTipoEvento() { 
        return tipoEvento;
    }
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    public int getEdadMinima() {
        return edadMinima;
    }
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }
    public String getImagenPromocional() { 
        return imagenPromocional;
    }
    public void setImagenPromocional(String imagenPromocional) { 
        this.imagenPromocional = imagenPromocional;
    }
    public int getCantidadBoletos() {
        return cantidadBoletos;
    }
    public void setCantidadBoletos(int cantidadBoletos) { 
        this.cantidadBoletos = cantidadBoletos;
    }

    @Override
    public String toString() {
        return "ID del Evento:" + id + ", Nombre del Evento:" + nombreEvento;
    }
}

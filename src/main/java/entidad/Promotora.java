/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author tolan
 */
public class Promotora {
    private int id;
    private String nombreComercial;
    private String calle;
    private String colonia;
    private String ciudad;
    private String estado;

    public Promotora() {}

    public Promotora(String nombreComercial, String calle, String colonia, String ciudad, String estado) {
        this.nombreComercial = nombreComercial;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getId() { 
        return this.id;
    }
    public void setId(int id) {
        this.id = id; 
    }
    public String getNombreComercial() { 
        return nombreComercial; 
    }
    public void setNombreComercial(String nombreComercial) { 
        this.nombreComercial = nombreComercial; 
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle; 
    }
    public String getColonia() {
        return colonia;
    }
    public void setColonia(String colonia) { 
        this.colonia = colonia;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) { 
        this.ciudad = ciudad; 
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado; 
    }
     @Override
    public String toString() {
        return "ID de la Promotora" + id + ", Nombre Comercial: " + nombreComercial;
    }
}


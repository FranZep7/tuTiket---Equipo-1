package entidad;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    private String usuario;
    private String contra;

    public Cliente() {}

    public Cliente(String nombres, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String usuario, String contra) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.contra = contra;
    }

    public int getId() { 
        return id;
    }
    public void setId(int id) { 
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) { 
        this.usuario = usuario;
    }
    public String getContrasena() {
        return contra;
    }
    public void setContrasena(String contrasena) {
        this.contra = contrasena;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) { 
        this.nombres = nombres;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getFechaNacimiento() { 
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento;
    }

    
     @Override
    public String toString() {
        return "ID del Cliente:" + id + ", Usuario:" + usuario + "Nombre Completo: "+ nombres + apellidoPaterno + apellidoMaterno;
    }
}


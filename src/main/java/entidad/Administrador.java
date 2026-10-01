/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidad;

/**
 *
 * @author tolan
 */
public class Administrador {
    private int id;
    private int promotoraId; //Qué promotora SUPERVISA este Administrador
    private String usuario;
    private String contra;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Administrador() {}

    public Administrador(String usuario, String contrasena, String nombres,
                          String apellidoPaterno, String apellidoMaterno) {

        this.usuario = usuario;
        this.contra = contrasena;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getId() {
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public int getPromotoraId() {
        return promotoraId;
    } 
    public void setPromotoraId(int promotoraId){
        this.promotoraId = promotoraId;
    }
    public String getUsuario() { 
        return usuario; 
    }
    public void setUsuario(String usuario) { 
        this.usuario = usuario; 
    }
    public String getContrana() { 
        return contra; 
    }
    public void setContrasena(String contra) { 
        this.contra = contra; 
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
    
    @Override
    public String toString() {
        return "ID del Admin: " + id + ", Usuario: " + usuario;
    }
}

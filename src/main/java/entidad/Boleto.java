package entidad;

public class Boleto {
    private int id;
    private int eventoId; //A qué evento pertenece este boleto
    private String estatus;
    private double costo;

    public Boleto() {}

    public Boleto(int eventoId, String estatus, double costo){
        this.eventoId = eventoId;
        this.estatus = estatus;
        this.costo = costo;
    }

    public int getId() {
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public int getEventoId() {
        return eventoId;
    }
    public void setEventoId(int eventoId) { 
        this.eventoId = eventoId;
    }
    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) { 
        this.estatus = estatus;
    }
    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "ID del Boleto:" + id + ", Estado:" + estatus;
    }
}

package Modelo;

public class Problema1_EstadoAlterado {
    private String nombre;
    private int turnosRestantes;

    public Problema1_EstadoAlterado(String nombre, int turnosRestantes) {
        this.nombre = nombre;
        this.turnosRestantes = turnosRestantes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTurnosRestantes() {
        return turnosRestantes;
    }
    
    public void reducirTurnos(){
        this.turnosRestantes--;
    }
}

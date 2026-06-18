
package Modelo;

public class Problema1_Arquero extends Problema1_Personaje {
    private int precision;

    public Problema1_Arquero(String nombre, int vida, int nivel, int precision) {
        super(nombre, vida, nivel);
        this.precision = precision;
    }

    @Override
    public int atacar() {
        return precision + (nivel * 4);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Arquero] Precision: %d", precision);
    }
}
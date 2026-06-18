
package Modelo;

public class Problema1_Mago extends Problema1_Personaje {
    private int poderMagico;

    public Problema1_Mago(String nombre, int vida, int nivel, int poderMagico) {
        super(nombre, vida, nivel);
        this.poderMagico = poderMagico;
    }

    @Override
    public int atacar() {
        return poderMagico * nivel;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Mago] Poder Magico: %d", poderMagico);
    }
}
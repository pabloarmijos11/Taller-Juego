
package Modelo;

public class Problema1_Arquero extends Problema1_Personaje {
    private int precision;

    public Problema1_Arquero(String nombre, int vida, int ataque, int defensa, int energia) {
        super(nombre, vida, ataque, defensa, energia);
    }

    @Override
    public int atacar() {
        if (armaEquipada != null) {
            int bonoArma = armaEquipada.getValor();
            return (precision + (nivel * 4)) + bonoArma;
        } else {
            int bonoArma = 0;
            return (precision + (nivel * 4)) + bonoArma;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Arquero] Precision: %d", precision);
    }
    
    @Override
    public void atacar(Problema1_Personaje objetivo) {
        System.out.println(this.nombre + " dispara una flecha.");
        objetivo.defender(this.ataque);
    }

    @Override
    public void defender(int dañoRecibido) {
        if (dañoRecibido > 0) this.vida -= dañoRecibido;
    }

    @Override
    protected void ejecutarEfectoEspecial(Problema1_Personaje objetivo) {
        System.out.println("⚡ ¡" + this.nombre + " dispara una RÁFAGA VELOZ causando " + (this.ataque + 15) + " de daño! ⚡");
        objetivo.defender(this.ataque + 15);
}
package Modelo;

public class Problema1_Mago extends Problema1_Personaje {

    private int poderMagico;

    public Problema1_Mago(String nombre, int vida, int ataque, int defensa, int energia) {
        super(nombre, vida, ataque, defensa, energia);
    }

    @Override
    public int atacar() {
        if (armaEquipada != null) {
            int bonoArma = armaEquipada.getValor();
            return (poderMagico * nivel) + bonoArma;
        } else {
            int bonoArma = 0;
            return (poderMagico * nivel) + bonoArma;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Mago] Poder Magico: %d", poderMagico);
    }
    
    @Override
    public void atacar(Problema1_Personaje objetivo) {
        System.out.println(this.nombre + " lanza un ataque básico.");
        objetivo.defender(this.ataque);
    }

    @Override
    public void defender(int dañoRecibido) {
        if (dañoRecibido > 0) this.vida -= dañoRecibido;
    }

    @Override
    protected void ejecutarEfectoEspecial(Problema1_Personaje objetivo) {
        System.out.println("🔥 ¡" + this.nombre + " lanza una BOLA DE FUEGO causando " + (this.ataque * 3) + " de daño! 🔥");
        objetivo.defender(this.ataque * 3);
    }
}

package Modelo;

public class Problema1_Guerrero extends Problema1_Personaje {

    private int fuerzaCuerpoACuerpo;

    public Problema1_Guerrero(String nombre, int vida, int ataque, int defensa, int energia) {
        super(nombre, vida, ataque, defensa, energia);
    }

    @Override
    public int atacar() {
        if (armaEquipada != null) {
            int bonoArma = armaEquipada.getValor();
            return (fuerzaCuerpoACuerpo + (nivel * 5)) + bonoArma;
        } else {
            int bonoArma = 0;
            return (fuerzaCuerpoACuerpo + (nivel * 5)) + bonoArma;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Guerrero] Fuerza: %d", fuerzaCuerpoACuerpo);
    }
    
    @Override
    public void atacar(Problema1_Personaje objetivo) {
        System.out.println(this.nombre + " realiza un ataque normal.");
        objetivo.defender(this.ataque);
    }

    @Override
    public void defender(int dañoRecibido) {
        int dañoReal = dañoRecibido - this.defensa;
        if (dañoReal > 0) this.vida -= dañoReal;
    }

    @Override
    protected void ejecutarEfectoEspecial(Problema1_Personaje objetivo) {
        System.out.println("💥 ¡" + this.nombre + " usa GOLPE CRÍTICO causando " + (this.ataque * 2) + " de daño! 💥");
        objetivo.defender(this.ataque * 2);
    }
}

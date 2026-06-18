package Modelo;


public abstract class Problema1_Personaje {

    protected String nombre;
    protected int vida;
    protected int nivel;

    public Problema1_Personaje(String nombre, int vida, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
    }

    public abstract int atacar();

    public void defender(int dano) {
        this.vida -= dano;
        if (this.vida < 0) {
            this.vida = 0;
        }
    }

    public void subirNivel() {
        this.nivel++;
        this.vida += 20;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return String.format("Personaje: %s | Nivel: %d | Vida: %d", nombre, nivel, vida);
    }
}

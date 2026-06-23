package Modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Problema1_Personaje {

    protected String nombre;
    protected int vida;
    protected int nivel;
    protected List<Problema1_Objeto> inventario = new ArrayList<>();
    protected Problema1_Arma armaEquipada;
    protected Problema1_Armadura armaduraEquipada;

    public Problema1_Personaje(String nombre, int vida, int nivel) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;

    }

    public void agregarAlInventario(Problema1_Objeto obj) {
        inventario.add(obj);
    }

    public void equipar(Problema1_Objeto obj) {
        if (obj instanceof Problema1_Arma) {
            this.armaEquipada = (Problema1_Arma) obj;
        }
        if (obj instanceof Problema1_Armadura) {
            this.armaduraEquipada = (Problema1_Armadura) obj;
        }
    }

    public abstract int atacar();

    public void defender(int dano) {
        int danoFinal;

        if (armaduraEquipada != null) {
        
            int valorDefensa = armaduraEquipada.getValor();
            danoFinal = dano - valorDefensa;

            
            if (danoFinal < 0) {
                danoFinal = 0;
            }
        } else {
            
            danoFinal = dano;
        }

        this.vida -= danoFinal;

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

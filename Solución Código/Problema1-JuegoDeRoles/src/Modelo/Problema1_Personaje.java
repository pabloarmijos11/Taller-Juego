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
    protected ArrayList<Problema1_EstadoAlterado> estados;
    protected int energia;
    protected int energiaMaxima;
    protected int cooldownEspecial;

    public Problema1_Personaje(String nombre, int vida, int nivel, int ataque, int energiaMaxima) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.estados = new ArrayList<>();
        this.energiaMaxima = energiaMaxima;
        this.energia = energiaMaxima;
        this.cooldownEspecial = 0;

    }

    public String agregarEstado(String nombreEstado, int turnos) {
        estados.add(new Problema1_EstadoAlterado(nombreEstado, turnos));
        return String.format("!% ahora tiene el estado %s por %d turnos", nombre, nombreEstado, turnos);
    }

    public boolean actualizarEstado() {
        boolean puedeAtacar = true;
        ArrayList<Problema1_EstadoAlterado> estadosVigentes = new ArrayList<>();

        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getNombre().equalsIgnoreCase("Envenedado")) {
                vida -= 10;
                if (vida < 0) {
                    vida = 0;
                }
                System.out.println("[Debuff] Veneno causa 10 de daño a " + nombre + " Vida Actual: " + vida);
            } else if (estados.get(i).getNombre().equalsIgnoreCase("Congelado")) {
                puedeAtacar = false;
                System.out.println("[Debuff] " + nombre + " esta congelado y pierde su turno...");
            }
            estados.get(i).reducirTurnos();

            if (estados.get(i).getTurnosRestantes() > 0) {
                estadosVigentes.add(estados.get(i));
            } else {
                System.out.println("[Efecto] El estado " + estados.get(i).getNombre() + " ha terminado para " + nombre);
            }
        }
        estados = estadosVigentes;
        return puedeAtacar;
    }

    public int obtenerBonoDaño() {
        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).getNombre().equalsIgnoreCase("Aumentar Fuerza")) {
                System.out.println("[Buff] ¡Aumentar Fuerza añade 15 de ataque!");
                return 15;
            }
        }
        return 0;
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

    public void pasarTurno() {
        if (this.cooldownEspecial > 0) {
            this.cooldownEspecial--;
        }
    }

    public void usarHabilidadEspecial(Problema1_Personaje objetivo, int costoEnergia, int turnosCooldown) {
        if (this.cooldownEspecial > 0) {
            System.out.println("❌ " + this.nombre + " no puede usar su habilidad especial todavía. (Enfriamiento: " + this.cooldownEspecial + " turnos)");
            return;
        }

        if (this.energia < costoEnergia) {
            System.out.println("❌ ¡" + this.nombre + " no tiene suficiente energía! Requiere " + costoEnergia + " pero tiene " + this.energia);
            return;
        }

        this.energia -= costoEnergia;
        this.cooldownEspecial = turnosCooldown;

        ejecutarEfectoEspecial(objetivo);
    }

    protected abstract void ejecutarEfectoEspecial(Problema1_Personaje objetivo);

    public abstract void atacar(Problema1_Personaje objetivo);

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public int getCooldownEspecial() {
        return cooldownEspecial;
    }

    public void subirNivel() {
        this.nivel++;
        this.vida += 20;
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

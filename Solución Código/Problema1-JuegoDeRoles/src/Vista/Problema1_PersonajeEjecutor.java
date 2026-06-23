package Vista;

import Modelo.Problema1_Arma;
import Modelo.Problema1_Armadura;
import Modelo.Problema1_Personaje;
import Modelo.Problema1_Guerrero;
import Modelo.Problema1_Mago;
import Modelo.Problema1_Arquero;
import Modelo.Problema1_Objeto;



public class Problema1_PersonajeEjecutor {
    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("  SIMULACIÓN COMPLETA DE COMBATE RPG - TODAS LAS FUNCIONES  ");
        System.out.println("=========================================================\n");

        // 1. CREACIÓN DE PERSONAJES (Constructores basados en tus clases)
        // Parámetros: nombre, vida, nivel, ataque/dañoBase, energiaMaxima
        Problema1_Personaje guerrero = new Problema1_Guerrero("Aragorn", 120, 1, 20, 40);
        Problema1_Personaje mago = new Problema1_Mago("Gandalf", 80, 1, 25, 50);
        Problema1_Personaje arquero = new Problema1_Arquero("Legolas", 100, 1, 15, 30);

        System.out.println("--- ESTADO INICIAL ---");
        System.out.println(guerrero);
        System.out.println(mago);
        System.out.println(arquero);
        System.out.println();

        // =====================================================================
        // FUNCIONALIDAD 1: SISTEMA DE INVENTARIO Y EQUIPAMIENTO
        // =====================================================================
        System.out.println("--- FUNCIONALIDAD 1: Inventario y Equipamiento ---");
        
        // Creamos objetos (Armas y Armaduras)
        Problema1_Objeto espada = new Problema1_Arma("Espada Forjada", 15);
        Problema1_Objeto escudo = new Problema1_Armadura("Escudo de Hierro", 10);
        Problema1_Objeto tunica = new Problema1_Armadura("Túnica Arcana", 5);

        // Agregamos al inventario y equipamos
        guerrero.agregarAlInventario(espada);
        guerrero.agregarAlInventario(escudo);
        guerrero.equipar(espada);
        guerrero.equipar(escudo);
        System.out.println("▶️ " + guerrero.getNombre() + " se ha equipado una Espada (+15) y un Escudo (+10).");

        mago.agregarAlInventario(tunica);
        mago.equipar(tunica);
        System.out.println("▶️ " + mago.getNombre() + " se ha equipado una Túnica Arcana (+5).\n");


        // =====================================================================
        // FUNCIONALIDAD 2: SISTEMA DE ESTADOS ALTERADOS (BUFFS/DEBUFFS)
        // =====================================================================
        System.out.println("--- FUNCIONALIDAD 2: Aplicación de Estados Alterados ---");
        
        // Aplicamos Veneno y Congelado a los objetivos
        System.out.println(mago.agregarEstado("Congelado", 1)); // Gandalf pierde 1 turno
        System.out.println(arquero.agregarEstado("Envenenado", 2)); // Legolas sufre veneno por 2 turnos
        System.out.println(guerrero.agregarEstado("Aumentar Fuerza", 2)); // Aragorn gana bono de ataque
        System.out.println();


        // =====================================================================
        // FUNCIONALIDAD 3: SISTEMA DE ENERGÍA Y COOLDOWNS
        // =====================================================================
        System.out.println("--- FUNCIONALIDAD 3: Escenario de Combate por Turnos ---");

        // ---- REPRODUCCIÓN DEL TURNO 1 ----
        System.out.println("\n--- [TURNO 1] ---");
        
        // Paso A: Actualizar estados del Guerrero antes de actuar
        boolean guerreroPuedeActuar = guerrero.actualizarEstado(); 
        if (guerreroPuedeActuar) {
            // Evaluamos el bono de daño por Buff
            int bonoBuff = guerrero.obtenerBonoDaño();
            System.out.println("⚔️ Daño calculado con herencia/armas: " + guerrero.atacar());
            
            // Intenta usar Habilidad Especial (costo: 25 energía, cooldown: 2 turnos)
            System.out.println("⚡ " + guerrero.getNombre() + " intenta usar su Habilidad Especial:");
            guerrero.usarHabilidadEspecial(arquero, 25, 2); 
        }

        // Paso B: Turno del Mago (Debe perder el turno por estar Congelado)
        boolean magoPuedeActuar = mago.actualizarEstado();
        if (!magoPuedeActuar) {
            System.out.println("💤 " + mago.getNombre() + " no pudo atacar este turno.");
        }


        // ---- REPRODUCCIÓN DEL TURNO 2 ----
        System.out.println("\n--- [TURNO 2] ---");
        
        // Reducimos cooldowns al iniciar la ronda
        guerrero.pasarTurno();
        mago.pasarTurno();
        
        // El guerrero intenta volver a usar la habilidad especial en la misma ronda
        guerrero.actualizarEstado();
        System.out.println("⚡ " + guerrero.getNombre() + " intenta usar de nuevo la Habilidad Especial:");
        guerrero.usarHabilidadEspecial(mago, 25, 2); // Debería fallar por COOLDOWN
        
        System.out.println("🏹 Turno de " + arquero.getNombre() + ":");
        arquero.actualizarEstado(); // Sufre daño por veneno
        arquero.atacar(guerrero); // Realiza un ataque normal directo (Mitigado por el Escudo equipado)


        // ---- REPRODUCCIÓN DEL TURNO 3 ----
        System.out.println("\n--- [TURNO 3] ---");
        
        // Reducimos cooldowns (El del guerrero llega a 0 y se libera)
        guerrero.pasarTurno();
        
        guerrero.actualizarEstado();
        System.out.println("🔋 Energía actual de " + guerrero.getNombre() + ": " + guerrero.getEnergia());
        System.out.println("⚡ " + guerrero.getNombre() + " intenta usar su Habilidad Especial de nuevo:");
        guerrero.usarHabilidadEspecial(mago, 25, 2); // Debería fallar por ENERGÍA (Le quedan 15 y requiere 25)

        System.out.println("\n=========================================================");
        System.out.println("                  FIN DE LA SIMULACIÓN                   ");
        System.out.println("=========================================================");
    }
}
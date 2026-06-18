package Vista;

import Modelo.Problema1_Personaje;
import Modelo.Problema1_Guerrero;
import Modelo.Problema1_Mago;
import Modelo.Problema1_Arquero;

public class Problema1_PersonajeEjecutor {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("            SIMULACION - JUEGO DE ROL    ");
        System.out.println("==================================================");

        Problema1_Personaje guerrero = new Problema1_Guerrero("Aragorn", 120, 1, 25);
        Problema1_Personaje mago = new Problema1_Mago("Gandalf", 90, 1, 35);
        Problema1_Personaje arquero = new Problema1_Arquero("Legolas", 100, 1, 28);
        
        System.out.println("");
        System.out.println("-------- ESTADO INICIAL DE LOS PERSONAJES --------");
        System.out.println(guerrero.toString());
        System.out.println(mago.toString());
        System.out.println(arquero.toString());

        Problema1_Personaje p1 = guerrero;
        Problema1_Personaje p2 = mago;

        System.out.println("");
        System.out.println("==================================================");
        System.out.println("      INICIO DE BATALLA: " + p1.getNombre().toUpperCase() + " VS " + p2.getNombre().toUpperCase());
        System.out.println("==================================================");

        int turno = 1;
        while (p1.getVida() > 0 && p2.getVida() > 0) {
            System.out.println("");
            System.out.println("TURNO " + turno);

            int danoP1 = p1.atacar();
            p2.defender(danoP1);
            System.out.println(p1.getNombre() + " ataca. Genera " + danoP1 + " de dano");
            System.out.println("Estado de " + p2.getNombre() + " -> Vida restante: " + p2.getVida());

            if (p2.getVida() <= 0) {
                break;
            }

            int danoP2 = p2.atacar();
            p1.defender(danoP2);
            System.out.println(p2.getNombre() + " contrataca. Genera " + danoP2 + " de dano");
            System.out.println("Estado de " + p1.getNombre() + " -> Vida restante: " + p1.getVida());

            turno++;
        }

        System.out.println("");
        System.out.println("==================================================");
        System.out.println("                FIN DE LA BATALLA                 ");
        System.out.println("==================================================");

        Problema1_Personaje ganador;

        if (p1.getVida() > 0) {
            ganador = p1;
        } else {
            ganador = p2;
        }

        System.out.println("El ganador es " + ganador.getNombre());
        ganador.subirNivel();
        System.out.println("Recompensa: Sube de Nivel y Recupera vida");
        System.out.println("Nuevo estado: " + ganador.toString());
    }
}

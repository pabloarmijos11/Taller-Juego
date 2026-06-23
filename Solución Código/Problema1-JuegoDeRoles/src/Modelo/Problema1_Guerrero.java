package Modelo;

public class Problema1_Guerrero extends Problema1_Personaje {

    private int fuerzaCuerpoACuerpo;

    public Problema1_Guerrero(String nombre, int vida, int nivel, int fuerzaCuerpoACuerpo) {
        super(nombre, vida, nivel);
        this.fuerzaCuerpoACuerpo = fuerzaCuerpoACuerpo;
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
}

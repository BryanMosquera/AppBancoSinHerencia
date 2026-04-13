public class Mago extends Personaje {
    public Mago(String nombre,Posicion posicion){
        super(nombre,80,4,11, posicion);
    }

    @Override 
    public void habilidad1(Personaje objetivo, Tablero tablero){
        if (objetivo == null || !objetivo.estaVivo())
            return;
        int dañoFinal = getDaño()+6+ tirarDados();
        objetivo.recibirDaño(dañoFinal);
    }

    @Override
    public void habilidad2 (Personaje objetivo, Tablero tablero){
        if (tablero == null)
            return;

        Posicion actual = getPosicion();
        Posicion destino = new Posicion(actual.getFila()+2, actual.getColumna());

        if (tablero.estaDentroTablero(destino)&& tablero.estaLibre(destino)){
            tablero.moverPersonaje(this, destino);
        }
    }
}

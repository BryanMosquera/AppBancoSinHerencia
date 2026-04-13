public class Combate {
    private Tablero tablero;

    public Combate (Tablero tablero){
        this.tablero =tablero;
    }

    public boolean mover(Personaje personaje,Posicion destino){
        if(personaje == null || destino == null)
            return false;
        if (!personaje.estaVivo())
            return false;
        if (!tablero.estaDentroTablero(destino))
            return false;
        if (!tablero.estaLibre(destino))
            return false;

        Posicion origen = personaje.getPosicion();
        if(origen== null)
            return false;
        int dFila = Math.abs(destino.getFila()- origen.getFila());
        int dCol = Math.abs(destino.getColumna()- origen.getColumna());

        boolean ortogonal = (dFila == 0 || dCol== 0);
        int distancia = dFila + dCol;

        if (!ortogonal)
            return false;
        if (distancia ==0)
            return false;
        if (distancia > personaje.getMovimiento())
            return false;

        return tablero.moverPersonaje(personaje, destino);
    }

    public boolean habilidad(Personaje actor, Personaje objetivo, int numeroHabilidad){
        if (actor == null || objetivo == null)
            return false;
        if (!actor.estaVivo() || !objetivo.estaVivo())
            return false;

        if (numeroHabilidad == 1){
            actor.habilidad1(objetivo, tablero);
            return true;
        } else if (numeroHabilidad == 2){
            actor.habilidad2(objetivo, tablero);
            return true;
        }
        return false;
    }
    public Tablero getTablero(){
        return tablero;
    }
}

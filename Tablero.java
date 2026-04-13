public class Tablero {
    private int filas;
    private int columnas;
    private Personaje[][]celdas;

    public Tablero (int filas, int columnas){
        if (filas <30 || columnas <30){
            throw new IllegalArgumentException("El tablero debe ser minimo 30x30"); 
        }
        this.filas = filas;
        this.columnas=columnas;
        this.celdas= new Personaje[filas][columnas];
    }

    public boolean estaDentroTablero(Posicion p){
        return p !=null && p.getFila() >=0 && p.getFila() <filas && p.getColumna() >= 0 && p.getColumna() < columnas;
    }

    public boolean estaLibre(Posicion p){
        if (!estaDentroTablero(p)) 
            return false;
        return celdas[p.getFila()][p.getColumna()]== null;
    }

    public boolean colocarPersonaje(Personaje personaje, Posicion p){
        if (personaje == null || p ==null)
            return false;
        celdas[p.getFila()][p.getColumna()]= personaje;
        personaje.setPosicion(p);
        return true;
    }

    public boolean moverPersonaje(Personaje personaje, Posicion destino){
        if(personaje==null || destino == null)
            return false;
        if (!estaLibre(destino))
            return false;

        Posicion origen = personaje.getPosicion();
        if(!estaDentroTablero(origen))
            return false;
        celdas[origen.getFila()][origen.getColumna()]= null;
        celdas[destino.getFila()][destino.getColumna()]=personaje;
        personaje.setPosicion(destino);
        return true;
    }

    public Personaje getPersonaje(Posicion p){
        if (!estaDentroTablero(p))
            return null;
        return celdas[p.getFila()][p.getColumna()];
    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }
}

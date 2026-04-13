import java.util.Random;

public abstract class Personaje {
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int danio;
    private int movimiento;
    private Posicion posicion;

    private static final Random RNG_RANDOM = new Random();

    public Personaje(String nombre, int vida, int movimiento, int danio, Posicion posicion){
        this.nombre=nombre;
        this.vidaMaxima=vida;
        this.vidaActual=vida;
        this.movimiento=movimiento;
        this.danio=danio;
        this.posicion=posicion;
    }
    public boolean estaVivo(){
        return vidaActual >0;
    }

    public void recibirDaño(int cantidad){
        if(cantidad < 0) throw new IllegalArgumentException("Daño invalido");
        vidaActual=Math.max(0, vidaActual-cantidad);
    }
    
    public void curar(int cantidad){
        if (cantidad <0) throw new IllegalArgumentException("Curacion invalida");
        vidaActual= Math.min(vidaMaxima, vidaActual+ cantidad);
    }

    public int tirarDados(){
        return RNG_RANDOM.nextInt(20)+1;
    }
    
    public int ataqueDebil(Personaje objetivo){
        int total= danio+tirarDados();
        objetivo.recibirDaño(total);
        return total;
    }

    public abstract void habilidad1(Personaje objetivo, Tablero tablero);
    public abstract void habilidad2(Personaje objetivo, Tablero tablero);

    public String getNombre(){
        return nombre;
    }

    public int getVidaActual(){
        return vidaActual;
    }

    public int getVidaMaxima(){
        return vidaMaxima;
    }

    public int getMovimiento(){
        return movimiento;
    }

    public int getDaño(){
        return danio;
    }

    public Posicion getPosicion(){
        return posicion;
    }

    public void setPosicion(Posicion posicion){
        this.posicion=posicion;
    }
}

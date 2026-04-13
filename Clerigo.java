public class Clerigo extends Personaje{
    public Clerigo (String nombre, Posicion posicion){
        super(nombre, 110,4,6, posicion);
    }

    @Override
    public void habilidad1(Personaje objetivo, Tablero tablero){
        if (objetivo == null || !objetivo.estaVivo())
            return;
        int curacion = 12 + tirarDados();
        objetivo.curar(curacion);
    }
    @Override
    public void habilidad2(Personaje objetivo, Tablero tablero){
        if (objetivo == null || !objetivo.estaVivo())
            return;

        int dañoFinal = getDaño()+3+ tirarDados();
        objetivo.recibirDaño(dañoFinal);
    }
}

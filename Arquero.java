public class Arquero extends Personaje{
    private int alcance;

    public Arquero (String nombre, Posicion posicion){
        super(nombre, 90,6,9,posicion);
        this.alcance=5;
    }

    @Override
    public void habilidad1(Personaje objetivo, Tablero tablero){
        if (objetivo == null || !objetivo.estaVivo())
            return;
        int dañoFinal = getDaño()+4+tirarDados();
        objetivo.recibirDaño(dañoFinal);
    }

    @Override 
    public void habilidad2(Personaje objetivo, Tablero tablero){
        if (objetivo== null || !objetivo.estaVivo())
            return;

        int dañoFinal = getDaño()+2+tirarDados();
        objetivo.recibirDaño(dañoFinal);
    }

    public int getAlcance(){
        return alcance;
    }


    
    

    
}

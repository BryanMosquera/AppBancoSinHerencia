public class Asesino extends Personaje {
    private boolean esfumado;

    public Asesino(String nombre, Posicion posicion){
        super(nombre,95,7,10, posicion);
        this.esfumado=false;
    }

    @Override
    public void habilidad1(Personaje objetivo, Tablero tablero){
        if (objetivo == null || !objetivo.estaVivo())
            return;

        int dañoFinal= getDaño()+7+tirarDados();
        objetivo.recibirDaño(dañoFinal);
    }

    @Override
    public void habilidad2(Personaje objetivo, Tablero tablero){
        this.esfumado= true;
    }
    @Override
    public void recibirDaño(int cantidad){
        if (esfumado){
            cantidad= Math.max(0, cantidad-10);
            esfumado=false;
        }
        super.recibirDaño(cantidad);
    }
}

public class Guerrero extends Personaje {
    private boolean escudoActivo;

    public Guerrero(String nombre, Posicion posicion){
        super(nombre, 100,5,8 , posicion);
        this.escudoActivo=false;
    }

    public void escudo(){
        this.escudoActivo=true;
    }

    @Override
    public void recibirDaño(int cantidad){
        if(escudoActivo){
            cantidad= Math.max(0, cantidad-8);
            escudoActivo=false;
        }
        super.recibirDaño(cantidad);
    }
    @Override
    public void habilidad1(Personaje objetivo, Tablero tablero){
        int danioFinal= getDaño()+5+tirarDados();
        objetivo.recibirDaño(danioFinal);
    }

    @Override
    public void habilidad2(Personaje objetivo, Tablero tablero){
        escudo();
    }
}

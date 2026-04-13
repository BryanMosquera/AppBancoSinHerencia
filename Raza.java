public class Raza {
    private String nombre;
    private int bonusVida;
    private int bonusMovimiento;
    private int bonusDanio;

    public Raza(String nombre, int bonusVida, int bonusMovimiento, int bonusDanio){
        this.nombre=nombre;
        this.bonusVida= bonusVida;
        this.bonusMovimiento=bonusMovimiento;
        this.bonusDanio=bonusDanio;
    }

    public String getNombre(){
        return nombre;
    }

    public int getBonusVida(){
        return bonusVida;
    }

    public int getBonusMovimiento(){
        return bonusMovimiento;
    }

    public int getBonusDanio(){
        return bonusDanio;
    }
}

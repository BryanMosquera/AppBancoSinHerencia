import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String []args){
        Tablero tablero = new Tablero(30, 30);
        Combate combate = new Combate(tablero);
        Personaje[] equipo1 = crearEquipo1(tablero);
        Personaje[] equipo2 = crearEquipo2(tablero);

        int turno= 1;

        while (hayVivos(equipo1) && hayVivos(equipo2)){
            System.out.println("\n==============================");
            System.out.println("Turno"+ turno);
            System.out.println("==============================");

            mostrarEstado(equipo1,equipo2);

            ejecutarTurno("Jugador 1", combate, equipo1,equipo2);
            if (!hayVivos(equipo2)) break;
            ejecutarTurno("Jugador 2", combate,equipo2,equipo1);
            turno++;
        }

        if (hayVivos(equipo1)){
            System.out.println("\nGana Jugador1");
        } else{
            System.out.println("\nGana Jugador 2");
        }
    }

    private static Personaje[] crearEquipo1(Tablero tablero){
        Personaje []eq= new Personaje[3];
        eq[0]= new Guerrero("G1", new Posicion(0, 0));
        eq[1]= new Arquero("Ar1", new Posicion(0, 1));
        eq[2] = new Clerigo("Cle1", new Posicion(0, 2));

        for (Personaje p : eq){
            tablero.colocarPersonaje(p, p.getPosicion());
        }
        return eq;
    }
    private static Personaje [] crearEquipo2(Tablero tablero){
        Personaje[]eq = new Personaje[3];
        eq[0] = new Mago("Mago2", new Posicion(29, 29));
        eq[1]= new Asesino("As2", new Posicion(29, 28));
        eq[2] = new Guerrero("G2", new Posicion(29, 27));

        for (Personaje p:eq){
            tablero.colocarPersonaje(p, p.getPosicion());
        }
        return eq;
    }
    private static void ejecutarTurno(String jugador,Combate combate, Personaje[]equipoPropio, Personaje[] equipoRival){
        System.out.println("\nTurno de "+ jugador);

        Personaje actor= elegirPersonajeVivo(equipoPropio,"Elige tu personaje");
        if (actor== null)
            return;

        System.out.println("Accion:");
        System.out.println("1. Mover");
        System.out.println("2. Habilidad 1");
        System.out.println("3. Habilidad 2");
        int accion = leerEntero("Opcion:");

        if (accion == 1){
            int fila = leerEntero("Fila destino:");
            int col= leerEntero("Columna destino:");
            boolean ok = combate.mover(actor, new Posicion(fila, col));
            System.out.println(ok ? "Movimiento Realizado": "Movimiento invalido");
            return;
        }

        if (accion == 2 || accion == 3){
            System.out.println("Objetivo");
            System.out.println("1. Aliado");
            System.out.println("2. Enemigo");
            int tipoObjetivo = leerEntero("Opcion");
            Personaje [] pool = (tipoObjetivo== 1)? equipoPropio: equipoRival;

            Personaje objetivo = elegirPersonajeVivo(pool, "Elegir objetivo");
            if (objetivo == null)
                return;

            boolean ok = combate.habilidad(actor, objetivo, (accion ==2)?1:2);
            System.out.println(ok? "Habilidad usada":"No se puede usar la habilidad");
            return;
        }
        System.out.println("Accion no valida");
    }

    private static Personaje elegirPersonajeVivo(Personaje[] equipo, String texto) {
        if (!hayVivos(equipo)) return null;

        while (true) {
            System.out.println("\n" + texto + ":");
            for (int i = 0; i < equipo.length; i++) {
                Personaje p = equipo[i];
                String estado = p.estaVivo() ? "VIVO" : "MUERTO";
                System.out.println((i + 1) + ". " + p.getNombre() + " (" + p.getClass().getSimpleName() + ") "
                        + "Vida: " + p.getVidaActual() + " - " + estado);
            }
            int op = leerEntero("Numero: ");
            if (op >= 1 && op <= equipo.length) {
                Personaje elegido = equipo[op - 1];
                if (elegido.estaVivo()) return elegido;
            }

            System.out.println("Seleccion invalida");
        }
    }
    private static boolean hayVivos(Personaje[] equipo) {
        for (Personaje p : equipo) {
            if (p.estaVivo()) return true;
        }
        return false;
    }

    private static void mostrarEstado(Personaje[] eq1, Personaje[] eq2) {
        System.out.println("\n--- Estado Equipo 1 ---");
        mostrarEquipo(eq1);

        System.out.println("\n--- Estado Equipo 2 ---");
        mostrarEquipo(eq2);
    }

    private static void mostrarEquipo(Personaje[] equipo) {
        for (Personaje p : equipo) {
            String pos = (p.getPosicion() == null) ? "(sin posicion)" : p.getPosicion().toString();
            System.out.println(
                p.getNombre()
                + " | " + p.getClass().getSimpleName()
                + " | Vida: " + p.getVidaActual()
                + " | Pos: " + pos
            );
        }
    }
    private static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Introduce un numero valido");
            }
        }
    }
}

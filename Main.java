public class Main {
    public static void main(String[] args) {
        MotorJuego motor = new MotorJuego();
        Jugador jugador = new Jugador("Heroe", 0, 0, 2, 2, 10, 'H');
        Enemigo enemigo = new Enemigo("Alien", 5, 0, 2, 2, 5, 'E');

        motor.agregarEntidad(jugador);
        motor.agregarEntidad(enemigo);
        motor.setEstado("RUNNING");

        GestorEntradas entrada = new GestorEntradas();

        for (int turno = 1; turno <= 5; turno++) {
            System.out.println("--- Turno " + turno + " ---");
            entrada.moverJugador(jugador, 1, 0);
            entrada.accionJugador(jugador, enemigo, motor);
            motor.actualizar();
            System.out.println();
        }

        System.out.println("Juego finalizado. Estado: " + motor.getEstado());
    }
}

public class Main {
    public static void main(String[] args) {
        MotorJuego motor = new MotorJuego();
        Jugador jugador = new Jugador("Heroe", 0, 0, 2, 2, 10, 'H');
        Enemigo enemigo = new Enemigo("Alien", 5, 0, 2, 2, 5, 'E');

        motor.agregarEntidad(jugador);
        motor.agregarEntidad(enemigo);
        motor.setEstado("RUNNING");

        GestorEntradas entrada = new GestorEntradas();

        // Secuencia de comandos que simulan una partida por consola
        String[] comandos = {"DERECHA", "DERECHA", "ARRIBA", "ARRIBA", "IZQUIERDA", "ABAJO", "DERECHA"};

        int turno = 1;
        for (String cmd : comandos) {
            if (!motor.getEstado().equals("RUNNING")) break;
            System.out.println("--- Turno " + turno + ": comando=" + cmd + " ---");
            entrada.moverJugador(jugador, cmd);
            entrada.accionJugador(jugador, enemigo, motor);
            motor.actualizar();
            System.out.println("Posiciones: jugador=(" + jugador.getX() + ", " + jugador.getY() + ") | enemigo=(" + enemigo.getX() + ", " + enemigo.getY() + ")");
            System.out.println();
            // Finalizar si el jugador o enemigo mueren
            if (!jugador.isViva() || !enemigo.isViva()) {
                motor.setEstado("GAME_OVER");
                break;
            }
            turno++;
        }

        System.out.println("Juego finalizado. Estado: " + motor.getEstado());
    }
}

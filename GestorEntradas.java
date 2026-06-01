public class GestorEntradas {
    public void moverJugador(Jugador jugador, int dx, int dy) {
        int nuevaX = jugador.getX() + dx;
        int nuevaY = jugador.getY() + dy;
        jugador.setX(nuevaX);
        jugador.setY(nuevaY);
        System.out.println("Movimiento: jugador a (" + nuevaX + ", " + nuevaY + ")");
    }

    public void accionJugador(Jugador jugador, Enemigo enemigo, MotorJuego motor) {
        System.out.println("Jugador intenta una accion contra el enemigo.");
        if (jugador.isViva() && enemigo.isViva()) {
            if (Math.abs(jugador.getX() - enemigo.getX()) <= 1) {
                jugador.atacar(enemigo);
            } else {
                System.out.println("El enemigo esta lejos y no puede atacar.");
            }
        }
        if (enemigo.isViva()) {
            enemigo.atacarJugador(jugador);
            if (!jugador.isViva()) {
                motor.eliminarEntidad(jugador);
            }
        } else {
            motor.eliminarEntidad(enemigo);
        }
    }
}

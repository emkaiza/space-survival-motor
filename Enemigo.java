public class Enemigo extends EntidadVideojuego {
    public Enemigo(String nombre, int x, int y, int ancho, int alto, int vida, char imagen) {
        super(nombre, x, y, ancho, alto, vida, imagen);
    }

    @Override
    public void actualizar() {
        if (getX() > 0) {
            setX(getX() - 1);
        }
        System.out.println("Enemigo " + getNombre() + " se mueve a (" + getX() + ", " + getY() + ") con vida " + getVida());
    }

    public void atacarJugador(Jugador jugador) {
        if (jugador.isViva()) {
            System.out.println("Enemigo ataca al jugador " + jugador.getNombre());
            jugador.recibirDano(2);
            System.out.println("Vida del jugador: " + jugador.getVida());
        }
    }
}

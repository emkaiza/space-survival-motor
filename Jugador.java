public class Jugador extends EntidadVideojuego {
    public Jugador(String nombre, int x, int y, int ancho, int alto, int vida, char imagen) {
        super(nombre, x, y, ancho, alto, vida, imagen);
    }

    @Override
    public void actualizar() {
        System.out.println("Jugador " + getNombre() + " se encuentra en (" + getX() + ", " + getY() + ") con vida " + getVida());
    }

    public void atacar(Enemigo enemigo) {
        if (enemigo.isViva()) {
            System.out.println("Jugador ataca a " + enemigo.getNombre());
            enemigo.recibirDano(3);
            System.out.println("Vida enemiga: " + enemigo.getVida());
        }
    }
}

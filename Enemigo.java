public class Enemigo extends EntidadVideojuego {
    private String estado;
    private int patrolMinX;
    private int patrolMaxX;
    private int patrolDir;

    public Enemigo(String nombre, int x, int y, int ancho, int alto, int vida, char imagen) {
        super(nombre, x, y, ancho, alto, vida, imagen);
        this.estado = "PATRULLAR";
        this.patrolMinX = x - 2;
        this.patrolMaxX = x + 2;
        this.patrolDir = -1;
    }

    @Override
    public void actualizar() {
        // comportamiento por defecto si no se usa el actualizado por MotorJuego
        System.out.println("Enemigo " + getNombre() + " estado=" + estado + " posición=(" + getX() + ", " + getY() + ") vida=" + getVida());
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String nuevo) {
        if (nuevo == null) return;
        if (!nuevo.equals(this.estado)) {
            this.estado = nuevo;
            System.out.println("Enemigo " + getNombre() + " cambia a estado: " + this.estado);
        }
    }

    // Actualiza comportamiento según la posición del jugador
    public void actualizarComportamiento(Jugador jugador) {
        if (!isViva()) return;
        if (jugador == null || !jugador.isViva()) {
            // sin jugador, seguir patrullando
            setEstado("PATRULLAR");
            patrullar();
            return;
        }

        // detectar colisión por rectángulos
        boolean colision = getX() < jugador.getX() + jugador.getAncho()
                && getX() + getAncho() > jugador.getX()
                && getY() < jugador.getY() + jugador.getAlto()
                && getY() + getAlto() > jugador.getY();

        int distanciaManhattan = Math.abs(getX() - jugador.getX()) + Math.abs(getY() - jugador.getY());

        if (colision) {
            setEstado("ATACAR");
            atacarJugador(jugador);
            return;
        }

        if (distanciaManhattan <= 3) {
            setEstado("PERSEGUIR");
            perseguir(jugador);
            return;
        }

        setEstado("PATRULLAR");
        patrullar();
    }

    private void patrullar() {
        int nx = getX() + patrolDir;
        if (nx < patrolMinX || nx > patrolMaxX) {
            patrolDir *= -1;
            nx = getX() + patrolDir;
        }
        setX(nx);
        System.out.println("Enemigo " + getNombre() + " patrullando a (" + getX() + ", " + getY() + ")");
    }

    private void perseguir(Jugador jugador) {
        if (getX() < jugador.getX()) setX(getX() + 1);
        else if (getX() > jugador.getX()) setX(getX() - 1);
        if (getY() < jugador.getY()) setY(getY() + 1);
        else if (getY() > jugador.getY()) setY(getY() - 1);
        System.out.println("Enemigo " + getNombre() + " persigue a jugador -> (" + getX() + ", " + getY() + ")");
    }

    public void atacarJugador(Jugador jugador) {
        if (jugador.isViva()) {
            System.out.println("Enemigo ataca al jugador " + jugador.getNombre());
            jugador.recibirDano(2);
            System.out.println("Vida del jugador: " + jugador.getVida());
        }
    }
}

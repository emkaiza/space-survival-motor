public abstract class EntidadVideojuego {
    private String nombre;
    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int vida;
    private char imagen;

    public EntidadVideojuego(String nombre, int x, int y, int ancho, int alto, int vida, char imagen) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.vida = vida;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public char getImagen() {
        return imagen;
    }

    public void setImagen(char imagen) {
        this.imagen = imagen;
    }

    public boolean isViva() {
        return vida > 0;
    }

    public void recibirDano(int dano) {
        vida -= dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    public abstract void actualizar();
}

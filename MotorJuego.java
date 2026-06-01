import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class MotorJuego {
    private String estado;
    private ArrayList<EntidadVideojuego> entidades;
    private Set<String> colisionesPrevias;

    public MotorJuego() {
        this.estado = "INICIAL";
        this.entidades = new ArrayList<>();
        this.colisionesPrevias = new HashSet<>();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void agregarEntidad(EntidadVideojuego entidad) {
        entidades.add(entidad);
        System.out.println("Entidad agregada: " + entidad.getNombre());
    }

    public void eliminarEntidad(EntidadVideojuego entidad) {
        entidades.remove(entidad);
        System.out.println("Entidad eliminada: " + entidad.getNombre());
    }

    public void actualizar() {
        System.out.println("MotorJuego actualiza entidades...");
        for (EntidadVideojuego entidad : entidades) {
            if (entidad.isViva()) {
                entidad.actualizar();
            }
        }
        // Deteccion de colisiones entre entidades (jugador vs enemigo)
        detectarColision();
        limpiarEntidadesMuertas();
        if (entidades.isEmpty()) {
            setEstado("GAME_OVER");
            System.out.println("No quedan entidades vivas.");
        }
    }

    // Método simple que detecta colisión entre rectángulos de entidades
    // y aplica daño si el jugador colisiona con un enemigo.
    public void detectarColision() {
        for (int i = 0; i < entidades.size(); i++) {
            EntidadVideojuego a = entidades.get(i);
            if (!a.isViva()) continue;
            for (int j = i + 1; j < entidades.size(); j++) {
                EntidadVideojuego b = entidades.get(j);
                if (!b.isViva()) continue;

                boolean colision = a.getX() < b.getX() + b.getAncho()
                        && a.getX() + a.getAncho() > b.getX()
                        && a.getY() < b.getY() + b.getAlto()
                        && a.getY() + a.getAlto() > b.getY();

                String key = a.getNombre().compareTo(b.getNombre()) <= 0 ? a.getNombre() + "|" + b.getNombre() : b.getNombre() + "|" + a.getNombre();
                if (colision) {
                    if (!colisionesPrevias.contains(key)) {
                        // primera detección de esta colisión continua -> aplicar daño
                        if (a instanceof Jugador && b instanceof Enemigo) {
                            System.out.println("Colisión detectada: " + a.getNombre() + " (Jugador) con " + b.getNombre() + " (Enemigo)");
                            a.recibirDano(1);
                            System.out.println("Vida del jugador " + a.getNombre() + " ahora: " + a.getVida());
                        } else if (b instanceof Jugador && a instanceof Enemigo) {
                            System.out.println("Colisión detectada: " + b.getNombre() + " (Jugador) con " + a.getNombre() + " (Enemigo)");
                            b.recibirDano(1);
                            System.out.println("Vida del jugador " + b.getNombre() + " ahora: " + b.getVida());
                        }
                        colisionesPrevias.add(key);
                    }
                } else {
                    // si antes estaban colisionando y ahora ya no, eliminar del set
                    if (colisionesPrevias.contains(key)) {
                        colisionesPrevias.remove(key);
                    }
                }
            }
        }
    }

    private void limpiarEntidadesMuertas() {
        Iterator<EntidadVideojuego> iterador = entidades.iterator();
        while (iterador.hasNext()) {
            EntidadVideojuego entidad = iterador.next();
            if (!entidad.isViva()) {
                System.out.println("Entidad muerta detectada: " + entidad.getNombre());
                iterador.remove();
            }
        }
    }
}

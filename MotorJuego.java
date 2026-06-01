import java.util.ArrayList;
import java.util.Iterator;

public class MotorJuego {
    private String estado;
    private ArrayList<EntidadVideojuego> entidades;

    public MotorJuego() {
        this.estado = "INICIAL";
        this.entidades = new ArrayList<>();
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
        limpiarEntidadesMuertas();
        if (entidades.isEmpty()) {
            setEstado("GAME_OVER");
            System.out.println("No quedan entidades vivas.");
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

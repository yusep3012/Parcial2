import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Estudiante {

    String nombre, documento;
    List<Double> notas = new ArrayList<>();

    public Estudiante(String documento, String nombre, List<Double> notas) {
        this.documento = documento;
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "\nEstudiante: " +
                "Número de documento:'" + documento + '\'' +
                ", Nombre: " + nombre + '\'' +
                ", Notas: " + notas +
                ", Promedio: " + promedio() +
                "}";
    }

    public double promedio() {
        OptionalDouble promedio;
        promedio = notas.stream()
                .mapToDouble(e -> e)
                .average();

        return promedio.isPresent() ? promedio.getAsDouble() : null;
    }

}

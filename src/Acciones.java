import java.util.*;
import java.util.stream.Collectors;

public class Acciones {
    Scanner teclado = new Scanner(System.in);

    public void ejecutar() {
        List<Estudiante> listaEstudiante = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n*------*");
            System.out.println("* MENÚ *");
            System.out.println("*------*");

            System.out.println("1) Crear estudiante ");
            System.out.println("2) Actualizar estudiante ");
            System.out.println("3) Borrar estudiante ");
            System.out.println("4) Ver 3 mejores estudiantes ");
            System.out.println("5) Ver los estudiantes que ganaron ");
            System.out.println("6) Ver los estudiantes ");
            System.out.println("7) Salir");
            System.out.println("Ingrese la opción que desea realizar: ");
            int opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    listaEstudiante = crearEstudiante(listaEstudiante);
                    break;
                case 2:
                    listaEstudiante = actualizarEstudiante(listaEstudiante);
                    break;
                case 3:
                    listaEstudiante = eliminarEstudiante(listaEstudiante);
                case 4:
                    verLosTresMejores(listaEstudiante);
                    break;
                case 5:
                    estudiantesQueGanaron(listaEstudiante);
                    break;
                case 6:
                    if (listaEstudiante.size() == 0) {
                        System.out.println("\nLa lista se encuetra vacía, no hay datos que mostrar.\n");
                    } else {
                        listaEstudiante.forEach(estudiante -> System.out.println(estudiante.toString()));
                    }
                    break;
                case 7:
                    System.out.println("\n¡ Muchas gracias por utilizar el software !");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción ingresada errónea, por favor verificar las opciones posibles");
            }
        }
    }

    public List<Estudiante> crearEstudiante(List<Estudiante> listaEstudiante) {

        System.out.println();
        System.out.println("¿Cuántos estudiantes desea ingresar?");
        int cantidadEstudiantes = teclado.nextInt();
        teclado.nextLine();

        for (int i = 0; i < cantidadEstudiantes; i++) {
            List<Double> listaNotas = new ArrayList<>();

            System.out.println();
            System.out.println("Ingrese el documento del estudiante: ");
            String documentoEstudiante = teclado.nextLine();

            System.out.println();
            System.out.println("Ingrese el nombre del estudiante: ");
            String nombreEstudiante = teclado.nextLine();
            for (int j = 0; j < 4; j++) {
                System.out.println();
                System.out.println("Ingrese la nota " + (j + 1) + " de " + nombreEstudiante + ": ");
                Double nota = teclado.nextDouble();
                teclado.nextLine();
                listaNotas.add(nota);
            }
            listaEstudiante.add(new Estudiante(documentoEstudiante, nombreEstudiante, listaNotas));
        }
        return listaEstudiante;
    }

    public List<Estudiante> eliminarEstudiante(List<Estudiante> listaEstudiante) {

        if (listaEstudiante.size() == 0) {
            return listaEstudiante;
        } else {
            System.out.println();
            teclado.nextLine();
            System.out.println("Ingrese el documento del estudiante que desea eliminar: ");
            String documento = teclado.nextLine();

            return listaEstudiante.stream().filter(estudiante -> !estudiante.getDocumento().equals(documento))
                    .collect(Collectors.toList());
        }
    }
    

    public List<Estudiante> actualizarEstudiante(List<Estudiante> listaEstudiante) {
        List<Double> listaNotas = new ArrayList();

        if (listaEstudiante.size() == 0) {
            System.out.println();
            System.out.println("¡ La lista de los estudiantes está vacía !");
            System.out.println();
        } else {
            System.out.println();
            teclado.nextLine();
            System.out.println("Ingrese el documento del estudiante que desea actualizar: ");
            String documento = teclado.nextLine();
            String nombreEstudiante = listaEstudiante.stream().filter(estudiante -> estudiante.getDocumento().
                    equals(documento)).map(Estudiante::getNombre)
                    .collect(Collectors.joining());
            if (nombreEstudiante.equals("")) {
                System.out.println("\nEl documento del estudiante ingresado no se encuentra \n");
            } else {
                System.out.println(nombreEstudiante);
                System.out.println();
                System.out.println("¿Qué desea actualizar?");
                System.out.println("1) Nombre");
                System.out.println("2) Notas");
                int opcion = teclado.nextInt();
                if (opcion == 1) {
                    teclado.nextLine();
                    System.out.println("Ingrese el nombre nuevo: ");
                    String nuevoNombre = teclado.nextLine();

                    listaEstudiante.stream().filter(estudiante -> estudiante.getDocumento().equals(documento))
                            .forEach(estudiante -> estudiante.setNombre(nuevoNombre));
                }
                if (opcion == 2) {
                    for (int j = 0; j < 4; j++) {
                        System.out.println("Ingrese la nota " + (j + 1) + " del estudiante: " + nombreEstudiante);
                        Double nota = teclado.nextDouble();
                        teclado.nextLine();
                        listaNotas.add(nota);
                        listaEstudiante.stream().filter(estudiante -> estudiante.getDocumento().equals(documento))
                                .forEach(estudiante -> estudiante.setNotas(listaNotas));
                    }
                }
            }
        }
        return listaEstudiante;
    }

    public void verLosTresMejores(List<Estudiante> listaEstudiante) {
        if (listaEstudiante.size() == 0) {
            System.out.println("\nLa lista se encuetra vacía, no hay datos que mostrar.\n");
        } else {
            listaEstudiante.stream().sorted(Comparator.
                    comparing(estudiante -> estudiante.promedio(), Comparator.reverseOrder())).limit(3).
                    forEach(estudiante -> System.out.println(estudiante.toString()));
        }
    }

    public void estudiantesQueGanaron(List<Estudiante> listaEstudiante) {

        if (listaEstudiante.size() == 0) {
            System.out.println("\nLa lista se encuetra vacía, no hay datos que mostrar.\n");
        } else {
            Map<String, Double> estudiantes = listaEstudiante.stream().
                    filter(estudiante -> estudiante.promedio() >= 3.0).
                    collect(Collectors.toMap(estudiante -> estudiante.getDocumento(),
                            estudiante -> estudiante.promedio()));

            for (Map.Entry<String, Double> valor : estudiantes.entrySet()) {
                System.out.println("Documento: " + valor.getKey() + " Promedio: " + valor.getValue());
            }
        }
    }
}

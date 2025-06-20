import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear encuestas para Sucursal Centro
        Encuesta e1 = new Encuesta("Juan", "El tiempo de espera fue largo", 2);
        Encuesta e2 = new Encuesta("Ana", null, 5);
        Encuesta e3 = new Encuesta("Luis", "La atención no fue buena", 3);

        // Crear encuestas para Sucursal Norte
        Encuesta e4 = new Encuesta("Marta", "La atención fue buena, pero la limpieza puede mejorar", 3);
        Encuesta e5 = new Encuesta("Pedro", null, 4);

        // Crear sucursales con sus encuestas
        Sucursal centro = new Sucursal("Centro", Arrays.asList(e1, e2, e3));
        Sucursal norte = new Sucursal("Norte", Arrays.asList(e4, e5));

        List<Sucursal> sucursales = Arrays.asList(centro, norte);

        // Procesar encuestas
        sucursales.stream()
                // Desanidar todas las encuestas de cada sucursal
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                // Filtrar encuestas con calificación menor o igual a 3
                                .filter(encuesta -> encuesta.getCalificacion() <= 3)
                                // Recuperar comentarios si existen
                                .flatMap(encuesta ->
                                        encuesta.getComentario().stream()
                                                // Mapear el comentario al mensaje con el nombre de la sucursal
                                                .map(comentario ->
                                                        "Sucursal " + sucursal.getNombre() +
                                                                ": Seguimiento a paciente con comentario: \"" + comentario + "\""
                                                )
                                )
                )
                // Mostrar cada mensaje
                .forEach(System.out::println);
    }
}

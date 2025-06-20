import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlataformaEducativa {

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + total + " minutos");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio ej = (Ejercicio) obj;
                ej.setRevisado(true);
                System.out.println("✅ Ejercicio '" + ej.titulo + "' marcado como revisado.");
            }
        }
    }

    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        System.out.println("\n🔍 Filtrando materiales por autor:");
        for (MaterialCurso material : lista) {
            if (filtro.test(material)) {
                material.mostrarDetalle();
            }
        }
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();

        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis"));
        materiales.add(new Ejercicio("Condicionales", "Mario"));

        System.out.println("📚 Materiales del curso:");
        mostrarMateriales(materiales);

        // Contar duración de videos
        List<Video> videos = new ArrayList<>();
        for (MaterialCurso mat : materiales) {
            if (mat instanceof Video) {
                videos.add((Video) mat);
            }
        }
        contarDuracionVideos(videos);

        // Marcar ejercicios revisados
        marcarEjerciciosRevisados(materiales);

        // Filtrar por autor
        filtrarPorAutor(materiales, m -> m.autor.equals("Mario"));
    }
}

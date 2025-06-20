import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MisionEspacial {
    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> comm = executor.submit(new SistemaComunicaciones());

        // Imprimir resultados (el orden de finalización puede variar)
        System.out.println(comm.get());
        System.out.println(soporte.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        System.out.println("✅ Todos los sistemas reportan estado operativo.");

        executor.shutdown();
    }
}

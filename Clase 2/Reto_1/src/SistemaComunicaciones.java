import java.util.concurrent.Callable;

public class SistemaComunicaciones implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(500); // Simula procesamiento
        return "📡 Comunicaciones: enlace con estación terrestre establecido.";
    }
}

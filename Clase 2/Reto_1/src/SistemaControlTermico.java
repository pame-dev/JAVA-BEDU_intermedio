import java.util.concurrent.Callable;

public class SistemaControlTermico implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1200); // Simula procesamiento
        return "🔥 Control térmico: temperatura estable (22°C).";
    }
}

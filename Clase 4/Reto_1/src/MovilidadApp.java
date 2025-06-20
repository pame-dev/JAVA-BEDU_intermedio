import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class MovilidadApp {

    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🚦 Calculando ruta...");
            try {
                // Simula latencia 2-3 segundos
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en cálculo de ruta");
            }
            // Retorna ruta simulada
            return "Centro -> Norte";
        });
    }

    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("💰 Estimando tarifa...");
            try {
                // Simula latencia 1-2 segundos
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001));
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en estimación de tarifa");
            }
            // Retorna tarifa simulada
            return 75.50;
        });
    }

    public void procesarViaje() {
        calcularRuta()
                .thenCombine(estimarTarifa(), (ruta, tarifa) ->
                        "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
                )
                .exceptionally(ex -> {
                    // Manejo de errores
                    return "❌ Error en el proceso: " + ex.getMessage();
                })
                .thenAccept(System.out::println);
    }
}

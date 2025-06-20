import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class AeropuertoControl {

    // Simula latencia 2-3 segundos
    private void simularLatencia() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001));
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupción en latencia");
        }
    }

    // Simula resultado booleano aleatorio con probabilidad dada (0.0 - 1.0)
    private boolean resultadoConProbabilidad(double probabilidad) {
        return ThreadLocalRandom.current().nextDouble() < probabilidad;
    }

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = resultadoConProbabilidad(0.8);
            System.out.println("🛣️ Pista disponible: " + resultado);
            return resultado;
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = resultadoConProbabilidad(0.85);
            System.out.println("🌦️ Clima favorable: " + resultado);
            return resultado;
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = resultadoConProbabilidad(0.9);
            System.out.println("🚦 Tráfico aéreo despejado: " + resultado);
            return resultado;
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            simularLatencia();
            boolean resultado = resultadoConProbabilidad(0.95);
            System.out.println("👷‍♂️ Personal disponible: " + resultado);
            return resultado;
        });
    }

    public void autorizarAterrizaje() {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture
                .allOf(pista, clima, trafico, personal)
                .thenApply(v ->
                        pista.join() && clima.join() && trafico.join() && personal.join()
                )
                .thenAccept(condicionesOptimas -> {
                    if (condicionesOptimas) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("\n🚫 Aterrizaje denegado: error en la verificación - " + ex.getMessage());
                    return null;
                });
    }
}

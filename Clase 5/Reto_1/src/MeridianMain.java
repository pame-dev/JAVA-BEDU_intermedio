import reactor.core.publisher.Flux;

import java.time.Duration;

public class MeridianMain {
    public static void main(String[] args) throws InterruptedException {
        MeridianSystem system = new MeridianSystem();

        // Iniciar los sistemas y suscribirse (puedes descomentar si quieres ver sus datos no críticos)
        system.monitorSensoresTrafico().subscribe();
        system.monitorContaminacion().subscribe();
        system.monitorAccidentes().subscribe();
        system.monitorTrenesMaglev().subscribe();
        system.monitorSemaforos().subscribe();

        // Escuchar alertas críticas
        Flux<String> alertsFlux = system.getAlerts();

        // Combinar alertas para detectar alertas globales
        alertsFlux.buffer(Duration.ofSeconds(1)) // agrupa alertas ocurridas en 1 segundo
                .filter(list -> list.size() >= 3)
                .subscribe(list -> {
                    System.out.println("🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime");
                    list.forEach(System.out::println);
                });

        // Mostrar alertas individuales si no son globales
        alertsFlux.subscribe(alert -> System.out.println(alert));

        // Mantener el programa activo
        Thread.sleep(20000);
    }
}

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class MeridianSystem {

    private final Sinks.Many<String> alertSink = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    public Flux<String> getAlerts() {
        return alertSink.asFlux();
    }

    public Flux<String> monitorSensoresTrafico() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 101))
                .doOnNext(level -> {
                    if (level > 70) {
                        alertSink.tryEmitNext("🚗 Alerta: Congestión del " + level + "% en Avenida Solar");
                    }
                })
                .onBackpressureBuffer()
                .map(level -> "Sensores tráfico nivel: " + level);
    }

    public Flux<String> monitorContaminacion() {
        return Flux.interval(Duration.ofMillis(600))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 101))
                .doOnNext(pm -> {
                    if (pm > 50) {
                        alertSink.tryEmitNext("🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");
                    }
                })
                .map(pm -> "Contaminación PM2.5: " + pm);
    }

    public Flux<String> monitorAccidentes() {
        String[] prioridades = {"Baja", "Media", "Alta"};
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades[ThreadLocalRandom.current().nextInt(0, 3)])
                .doOnNext(prio -> {
                    if ("Alta".equals(prio)) {
                        alertSink.tryEmitNext("🚑 Emergencia vial: Accidente con prioridad Alta");
                    }
                })
                .map(prio -> "Accidente prioridad: " + prio);
    }

    public Flux<String> monitorTrenesMaglev() {
        return Flux.interval(Duration.ofMillis(700))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 11))
                .doOnNext(retraso -> {
                    if (retraso > 5) {
                        alertSink.tryEmitNext("🚝 Tren maglev con retraso crítico: " + retraso + " minutos");
                    }
                })
                .onBackpressureBuffer()
                .map(retraso -> "Tren retraso: " + retraso + " min");
    }

    public Flux<String> monitorSemaforos() {
        String[] estados = {"Verde", "Amarillo", "Rojo"};
        return Flux.interval(Duration.ofMillis(400))
                .map(i -> estados[ThreadLocalRandom.current().nextInt(0, 3)])
                .scan(new Object() {
                    int rojosSeguidos = 0;
                    String estadoPrevio = "";
                }, (acc, estado) -> {
                    if ("Rojo".equals(estado)) {
                        acc.rojosSeguidos++;
                        if (acc.rojosSeguidos == 3) {
                            alertSink.tryEmitNext("🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte");
                        }
                    } else {
                        acc.rojosSeguidos = 0;
                    }
                    acc.estadoPrevio = estado;
                    return acc;
                })
                .map(acc -> "Semáforo estado: " + acc.estadoPrevio);
    }
}

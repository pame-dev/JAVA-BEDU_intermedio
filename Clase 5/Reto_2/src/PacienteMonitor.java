import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class PacienteMonitor {

    private final int pacienteId;

    public PacienteMonitor(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Flux<String> flujoSignosVitales() {
        return Flux.interval(Duration.ofMillis(300))
                .map(tick -> generarSignosVitales())
                .flatMap(tuple -> procesarEventoCritico(tuple))
                .delayElements(Duration.ofSeconds(1)); // Simula backpressure: 1 evento crítico por segundo
    }

    private Tuple3<Integer, String, String> generarSignosVitales() {
        int fc = ThreadLocalRandom.current().nextInt(40, 131); // FC entre 40 y 130
        int sistolica = ThreadLocalRandom.current().nextInt(80, 151); // 80-150
        int diastolica = ThreadLocalRandom.current().nextInt(50, 101); // 50-100
        int spo2 = ThreadLocalRandom.current().nextInt(85, 101); // 85-100

        String pa = sistolica + "/" + diastolica;
        String oxigeno = String.valueOf(spo2);

        return Tuples.of(fc, pa, oxigeno);
    }

    private Flux<String> procesarEventoCritico(Tuple3<Integer, String, String> tuple) {
        int fc = tuple.getT1();
        String[] paParts = tuple.getT2().split("/");
        int sistolica = Integer.parseInt(paParts[0]);
        int diastolica = Integer.parseInt(paParts[1]);
        int spo2 = Integer.parseInt(tuple.getT3());

        return Flux.create(emitter -> {
            boolean emitido = false;

            if (fc < 50 || fc > 120) {
                emitter.next("⚠️ Paciente " + pacienteId + " - FC crítica: " + fc + " bpm");
                emitido = true;
            }

            if (sistolica < 90 || diastolica < 60 || sistolica > 140 || diastolica > 90) {
                emitter.next("⚠️ Paciente " + pacienteId + " - PA crítica: " + sistolica + "/" + diastolica + " mmHg");
                emitido = true;
            }

            if (spo2 < 90) {
                emitter.next("⚠️ Paciente " + pacienteId + " - SpO2 baja: " + spo2 + "%");
                emitido = true;
            }

            if (!emitido) {
                emitter.complete(); // No hay eventos críticos
            }
        });
    }
}

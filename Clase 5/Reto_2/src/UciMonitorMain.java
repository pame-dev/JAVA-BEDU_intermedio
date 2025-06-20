import reactor.core.publisher.Flux;

public class UciMonitorMain {
    public static void main(String[] args) throws InterruptedException {
        PacienteMonitor paciente1 = new PacienteMonitor(1);
        PacienteMonitor paciente2 = new PacienteMonitor(2);
        PacienteMonitor paciente3 = new PacienteMonitor(3);

        // Fusionar los flujos de los 3 pacientes
        Flux<String> flujoUci = Flux.merge(
                paciente1.flujoSignosVitales(),
                paciente2.flujoSignosVitales(),
                paciente3.flujoSignosVitales()
        );

        // Priorizar FC sobre otros eventos
        flujoUci
                .sort((e1, e2) -> {
                    boolean e1Fc = e1.contains("FC crítica");
                    boolean e2Fc = e2.contains("FC crítica");
                    if (e1Fc && !e2Fc) return -1;
                    if (!e1Fc && e2Fc) return 1;
                    return 0;
                })
                .subscribe(evento -> System.out.println(evento));

        // Mantener el programa activo
        Thread.sleep(20000);
    }
}

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {
    public static void main(String[] args) {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...");

        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.execute(new ProfesionalMedico("Dra. Sánchez", salaCirugia));
        executor.execute(new ProfesionalMedico("Dr. Gómez", salaCirugia));
        executor.execute(new ProfesionalMedico("Enfermera López", salaCirugia));
        executor.execute(new ProfesionalMedico("Dr. Pérez", salaCirugia));
        executor.execute(new ProfesionalMedico("Dra. Torres", salaCirugia));

        executor.shutdown();
    }
}

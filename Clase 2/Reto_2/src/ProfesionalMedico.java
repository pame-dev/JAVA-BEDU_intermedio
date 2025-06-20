public class ProfesionalMedico implements Runnable {
    private String nombre;
    private RecursoMedico recurso;

    public ProfesionalMedico(String nombre, RecursoMedico recurso) {
        this.nombre = nombre;
        this.recurso = recurso;
    }

    @Override
    public void run() {
        recurso.usar(nombre);
    }
}


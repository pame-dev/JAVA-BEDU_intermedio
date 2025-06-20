public class Main {
    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        app.procesarViaje();

        // Para que el main no termine antes que las tareas asincrónicas
        Thread.sleep(4000);
    }
}

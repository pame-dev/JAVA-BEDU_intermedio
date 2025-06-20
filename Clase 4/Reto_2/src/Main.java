public class Main {
    public static void main(String[] args) throws InterruptedException {
        AeropuertoControl control = new AeropuertoControl();
        control.autorizarAterrizaje();

        // Esperar para que terminen las tareas asincrónicas antes de salir
        Thread.sleep(7000);
    }
}

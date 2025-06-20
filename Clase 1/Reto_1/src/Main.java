import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Mostrar órdenes
        System.out.println("📋 Órdenes registradas:");
        PlantaProduccion.mostrarOrdenes(ordenesMasa);

        System.out.println("\n📋 Órdenes registradas:");
        PlantaProduccion.mostrarOrdenes(ordenesPersonalizadas);

        System.out.println("\n📋 Órdenes registradas:");
        PlantaProduccion.mostrarOrdenes(ordenesPrototipo);

        // Procesar personalizadas
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        PlantaProduccion.procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        // Resumen total
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipo);

        PlantaProduccion.contarOrdenes(todas);
    }
}

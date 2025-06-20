import java.util.ArrayList;
import java.util.List;
public class PlantaProduccion {

    // Método genérico: mostrar órdenes
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    // Método genérico: procesar personalizadas
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                ((OrdenPersonalizada) obj).agregarCostoAdicional(costoAdicional);
            }
        }
    }

    public static void contarOrdenes(List<OrdenProduccion> lista) {
        int masa = 0, personalizada = 0, prototipo = 0;

        for (OrdenProduccion orden : lista) {
            if (orden instanceof OrdenMasa) {
                masa++;
            } else if (orden instanceof OrdenPersonalizada) {
                personalizada++;
            } else if (orden instanceof OrdenPrototipo) {
                prototipo++;
            }
        }

        System.out.println("\n📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizada);
        System.out.println("🧪 Prototipos: " + prototipo);
    }
}

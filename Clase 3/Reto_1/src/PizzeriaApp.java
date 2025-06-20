import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PizzeriaApp {
    public static void main(String[] args) {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("Juan", "domicilio", "555-1234"));
        pedidos.add(new Pedido("Ana", "local", null));
        pedidos.add(new Pedido("Luis", "domicilio", null));
        pedidos.add(new Pedido("Marta", "domicilio", "555-5678"));

        List<String> mensajes = pedidos.stream()
                .filter(p -> "domicilio".equalsIgnoreCase(p.getTipoEntrega()))
                .map(Pedido::getTelefono)
                .flatMap(Optional::stream) // Solo deja los teléfonos presentes
                .map(tel -> "📞 Confirmación enviada al número: " + tel)
                .collect(Collectors.toList());

        // Mostrar los mensajes
        mensajes.forEach(System.out::println);
    }
}

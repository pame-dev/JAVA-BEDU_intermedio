import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository productoRepo, MarcaRepository marcaRepo) {
        return args -> {
            // Crear marcas
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");

            marcaRepo.save(apple);
            marcaRepo.save(samsung);

            // Crear productos asociados a marcas
            productoRepo.save(new Producto("iPhone 15", "Teléfono inteligente", 25000, apple));
            productoRepo.save(new Producto("iPad Pro", "Tablet de alta gama", 30000, apple));
            productoRepo.save(new Producto("Galaxy S23", "Teléfono inteligente", 22000, samsung));
            productoRepo.save(new Producto("Smart TV", "Televisión 4K", 15000, samsung));

            // Mostrar productos agrupados por marca
            System.out.println("\n📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                        .filter(p -> p.getMarca().getId().equals(marca.getId()))
                        .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}

package poruit.demofitnes;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class MainController {

    private final Map<Integer, Product> productMap = new HashMap<>();
    private final Map<Integer, Order> orderMap = new HashMap<>();
    private final AtomicInteger orderIdCounter = new AtomicInteger(1);


    @PostConstruct
    public void init() {
        productMap.put(1, new Product(1, "Сывороточный протеин", "Высококачественный сывороточный протеин 1000г", 2999,
                "https://body-pit.ru/i/product_i/677_1_b.jpg", "протеины"));
        productMap.put(5, new Product(5, "Казеиновый протеин", "Медленный протеин для долгого усвоения 900г", 3499,
                "https://body-pit.ru/i/product_i/220_1_b.jpg", "протеины"));
        productMap.put(6, new Product(6, "Многокомпонентный протеин", "Комплексный протеин 1200г", 2799,
                "https://body-pit.ru/i/product_i/315_1_b.jpg", "протеины"));
        productMap.put(7, new Product(7, "Изолят протеина", "Чистый изолят сывороточного протеина 800г", 3999,
                "https://body-pit.ru/i/product_i/112_1_b.jpg", "протеины"));

        productMap.put(3, new Product(3, "BCAA 2:1:1", "Аминокислоты BCAA 2:1:1 500г", 2499,
                "https://body-pit.ru/i/product_i/47906_1_b.jpg", "аминокислоты"));
        productMap.put(8, new Product(8, "L-глутамин", "Чистый L-глутамин 300г", 1599,
                "https://body-pit.ru/i/product_i/77_1_b.jpg", "аминокислоты"));
        productMap.put(9, new Product(9, "Аргинин", "L-аргинин 200 капсул", 1299,
                "https://body-pit.ru/i/product_i/102_1_b.jpg", "аминокислоты"));
        productMap.put(10, new Product(10, "Комплекс EAA", "Незаменимые аминокислоты 400г", 2199,
                "https://body-pit.ru/i/product_i/198_1_b.jpg", "аминокислоты"));

        productMap.put(2, new Product(2, "Креатин", "Моногидрат креатина 300г", 1999,
                "https://body-pit.ru/i/product_i/241_1_b.jpg", "добавки"));
        productMap.put(11, new Product(11, "Предтреник", "Энергетический предтренировочный комплекс 300г", 2799,
                "https://body-pit.ru/i/product_i/312_1_b.jpg", "добавки"));
        productMap.put(12, new Product(12, "Жиросжигатель", "Комплекс для снижения веса 120 капсул", 2399,
                "https://body-pit.ru/i/product_i/189_1_b.jpg", "добавки"));
        productMap.put(13, new Product(13, "Бустер тестостерона", "Натуральный бустер тестостерона 90 капсул", 2599,
                "https://body-pit.ru/i/product_i/201_1_b.jpg", "добавки"));

        productMap.put(4, new Product(4, "Мультивитамины", "Комплекс витаминов и минералов 90 таблеток", 1599,
                "https://body-pit.ru/i/product_i/2536_2_b.jpg", "витамины"));
        productMap.put(14, new Product(14, "Витамин D3 + K2", "Комплекс для здоровья костей 60 капсул", 899,
                "https://body-pit.ru/i/product_i/412_1_b.jpg", "витамины"));
        productMap.put(15, new Product(15, "Омега-3", "Рыбий жир 1000мг 120 капсул", 1299,
                "https://body-pit.ru/i/product_i/178_1_b.jpg", "витамины"));
        productMap.put(16, new Product(16, "Магний + B6", "Для нервной системы и мышц 90 таблеток", 799,
                "https://body-pit.ru/i/product_i/298_1_b.jpg", "витамины"));
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return productMap.values();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = productMap.get(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // ---------- ЗАКАЗЫ ----------

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        int id = orderIdCounter.getAndIncrement();
        order.setId(id);
        orderMap.put(id, order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders")
    public Collection<Order> getOrders() {
        return orderMap.values();
    }

    @GetMapping("/orders/phones")
    public List<String> getAllPhoneNumbers() {
        return orderMap.values().stream()
                .map(Order::getPhoneNumber)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        Order order = orderMap.get(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/orders/{id}/success")
    public ResponseEntity<Order> markOrderAsSuccessful(@PathVariable int id, @RequestParam boolean successful) {
        Order order = orderMap.get(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        order.setSuccessful(successful);
        return ResponseEntity.ok(order);
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        if (orderMap.remove(id) != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

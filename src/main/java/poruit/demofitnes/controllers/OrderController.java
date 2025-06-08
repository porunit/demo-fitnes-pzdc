package poruit.demofitnes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poruit.demofitnes.data.dto.OrderDTO;
import poruit.demofitnes.services.OrderService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order) {
        orderService.createOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/phones")
    public ResponseEntity<List<String>> getAllPhoneNumbers() {
        return ResponseEntity.ok(orderService.getAllPhoneNumbers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/success")
    public ResponseEntity<OrderDTO> markOrderAsSuccessful(@PathVariable int id, @RequestParam boolean successful) {
        return ResponseEntity.ok(orderService.markStatus(id, successful));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}

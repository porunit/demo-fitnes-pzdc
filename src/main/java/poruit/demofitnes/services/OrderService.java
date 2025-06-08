package poruit.demofitnes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poruit.demofitnes.data.dto.OrderDTO;
import poruit.demofitnes.data.entity.Order;
import poruit.demofitnes.data.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDTO::from)
                .collect(Collectors.toList());
    }

    public void createOrder(OrderDTO orderDTO) {
        Order order = Order.from(orderDTO);
        orderRepository.save(order);
    }

    public List<String> getAllPhoneNumbers() {
        return orderRepository.findAll()
                .stream()
                .map(Order::getPhoneNumber)
                .distinct()
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int id) {
        return orderRepository.findById(id)
                .map(OrderDTO::from)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderDTO markStatus(int id, boolean successful) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setSuccessful(successful);
        return OrderDTO.from(orderRepository.save(order));
    }

    public void deleteOrder(int id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}

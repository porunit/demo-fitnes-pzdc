package poruit.demofitnes.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import poruit.demofitnes.data.entity.Order;

public interface OrderRepository extends MongoRepository<Order, Integer> {
}

package poruit.demofitnes.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import poruit.demofitnes.data.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer> {
}

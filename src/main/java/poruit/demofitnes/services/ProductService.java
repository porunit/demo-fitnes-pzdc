package poruit.demofitnes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import poruit.demofitnes.data.dto.ProductDTO;
import poruit.demofitnes.data.entity.Product;
import poruit.demofitnes.data.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(ProductDTO.from(product));
        }
        return productDTOs;
    }

    public ProductDTO getProductById(int id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            return ProductDTO.from(productOpt.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " not found");
    }
}

package poruit.demofitnes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poruit.demofitnes.data.dto.ProductDTO;
import poruit.demofitnes.services.ProductService;

import java.util.Collection;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Collection<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        ProductDTO result = productService.getProductById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.getProductById(id));
    }
}

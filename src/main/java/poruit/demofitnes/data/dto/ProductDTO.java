package poruit.demofitnes.data.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import poruit.demofitnes.data.entity.Product;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    Long id = Integer.toUnsignedLong(UUID.randomUUID().hashCode());
    String name;
    String description;
    Integer price;
    String image;
    String category;
    String imageUrl;

    public static ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImage());
        productDTO.setCategory(product.getCategory());
        productDTO.setImageUrl(product.getImageUrl());
        return productDTO;
    }
}

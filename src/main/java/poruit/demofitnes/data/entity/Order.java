package poruit.demofitnes.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import poruit.demofitnes.data.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Field("_id")
    Long id;
    List<Integer> products;
    String address;
    LocalDateTime date = LocalDateTime.now();
    String tgNickname;
    Integer totalPrice;
    String phoneNumber;
    Boolean successful = false;

    public static Order from(OrderDTO dto) {
        return new Order(
                dto.getId(),
                dto.getProducts(),
                dto.getAddress(),
                dto.getDate(),
                dto.getTgNickname(),
                dto.getTotalPrice(),
                dto.getPhoneNumber(),
                dto.getSuccessful()
        );
    }
}

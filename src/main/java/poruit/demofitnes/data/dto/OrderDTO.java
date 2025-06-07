package poruit.demofitnes.data.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    int id;
    List<Integer> products;
    String address;
    LocalDateTime date = LocalDateTime.now();
    String tgNickname;
    int totalPrice;
    String phoneNumber;
    Boolean successful = false;
}

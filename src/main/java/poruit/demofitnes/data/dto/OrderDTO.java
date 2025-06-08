package poruit.demofitnes.data.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import poruit.demofitnes.data.entity.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    Long id = Integer.toUnsignedLong(UUID.randomUUID().hashCode());

    @NotNull
    @NotEmpty
    List<Integer> products;

    @NotNull
    @NotEmpty
    @NotBlank
    String address;

    LocalDateTime date = LocalDateTime.now();

    @NotNull
    @NotEmpty
    @NotBlank
    String tgNickname;

    @NotNull
    @Positive
    Integer totalPrice;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\+?\\d{10,15}", message = "Invalid phone number")
    String phoneNumber;

    Boolean successful = false;

    public static OrderDTO from(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getProducts(),
                order.getAddress(),
                order.getDate(),
                order.getTgNickname(),
                order.getTotalPrice(),
                order.getPhoneNumber(),
                order.getSuccessful()
        );
    }
}

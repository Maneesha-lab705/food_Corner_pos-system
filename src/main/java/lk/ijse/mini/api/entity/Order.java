package lk.ijse.mini.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    String orderId;
    String customerId;
    Double price;
    LocalDate date;
}

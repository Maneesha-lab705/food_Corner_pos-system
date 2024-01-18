package lk.ijse.mini.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail{
    String orderId;
    String itemCode;
    String description;
    Double unitPrice;
    Integer qty;
    Double total;
}

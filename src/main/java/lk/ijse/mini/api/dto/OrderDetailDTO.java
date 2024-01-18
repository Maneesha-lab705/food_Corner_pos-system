package lk.ijse.mini.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO implements Serializable {
    String orderId;
    String itemCode;
    String description;
    Double unitPrice;
    Integer qty;
    Double total;
}

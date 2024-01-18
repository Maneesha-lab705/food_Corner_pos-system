package lk.ijse.mini.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Item {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
}

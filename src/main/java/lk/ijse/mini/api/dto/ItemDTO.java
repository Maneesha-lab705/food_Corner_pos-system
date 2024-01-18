package lk.ijse.mini.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ItemDTO {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
}

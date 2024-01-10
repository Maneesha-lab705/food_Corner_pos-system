package lk.ijse.mini.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private Integer contact;

    public CustomerDTO(String id, String name) {
        this.id=id;
        this.name=name;
    }
}

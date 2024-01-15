package lk.ijse.mini.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private String id;
    private String name;
    private String address;
    private String email;
    private Integer contact;

}
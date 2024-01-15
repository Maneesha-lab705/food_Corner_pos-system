package lk.ijse.mini.api.bo.custom;

import lk.ijse.mini.api.dto.CustomerDTO;

import java.sql.Connection;
import java.util.ArrayList;

public interface CustomerBO {
    boolean saveCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomer(String id);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String customerID);

    ArrayList<CustomerDTO> getAllCustomer(Connection connection);
}

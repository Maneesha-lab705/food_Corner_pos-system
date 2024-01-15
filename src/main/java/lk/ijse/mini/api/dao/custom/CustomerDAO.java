package lk.ijse.mini.api.dao.custom;

import lk.ijse.mini.api.dto.CustomerDTO;
import lk.ijse.mini.api.entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;

public interface CustomerDAO {

    boolean save(Customer customer);

    Customer sharch(String id);

    boolean update(Customer customer);

    boolean delete(String customerID);

    ArrayList<CustomerDTO> getAllCustomer(Connection connection);
}

package lk.ijse.mini.api.bo.custom.BOImpl;

import lk.ijse.mini.api.bo.custom.CustomerBO;
import lk.ijse.mini.api.dao.custom.CustomerDAO;
import lk.ijse.mini.api.dao.custom.impl.CustomerDAOIMPL;
import lk.ijse.mini.api.dto.CustomerDTO;
import lk.ijse.mini.api.entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO =new CustomerDAOIMPL();
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        return customerDAO.save(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getContact()));

    }

    @Override
    public CustomerDTO getCustomer(String id) {
        Customer customer =customerDAO.sharch(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact());


    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getContact()));

    }

    @Override
    public boolean deleteCustomer(String customerID) {
        return customerDAO.delete(customerID);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer(Connection connection) {
        ArrayList<CustomerDTO> allCustomer = customerDAO.getAllCustomer(connection);
        return allCustomer;
    }
}

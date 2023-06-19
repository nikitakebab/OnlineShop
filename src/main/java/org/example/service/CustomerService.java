package org.example.service;

import lombok.NonNull;
import org.example.DTO.CustomerDTO;
import org.example.DTO.OrderDTO;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDTO> getCustomers(Long customerId, String email, String phone) {
        return customerRepository.findAll(Example.of(new Customer(customerId, email, phone))).stream().map(CustomerDTO::new).toList();
    }

    public void addCustomer(CustomerDTO customerDTO) {
        customerRepository.save(new Customer(customerDTO));
    }

    public void saveAll(List<CustomerDTO> customerDTOList) {
        customerRepository.saveAll(customerDTOList.stream().map(Customer::new).toList());
    }

    public Customer findCustomer(OrderDTO orderDTO){
        return customerRepository.findAll().stream().filter(customer -> customer.getCustomerId().equals(orderDTO.getCustomerId())).toList().get(0);
    }
}

package org.example.controller;

import org.example.DTO.CustomerDTO;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    ResponseEntity<List<CustomerDTO>> getCustomers() {
        List<CustomerDTO> customers = customerService.getCustomers();

        return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);

    }

    @PostMapping("/customer")
    void addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO);
    }

    @PostMapping("/customers")
    void addCustomers(@RequestBody List<CustomerDTO> customerDTOList){
        customerService.saveAll(customerDTOList);
    }
}

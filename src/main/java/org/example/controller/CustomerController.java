package org.example.controller;

import org.example.DTO.CustomerDTO;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    @CrossOrigin
    ResponseEntity<List<CustomerDTO>> getCustomers(@RequestParam(value = "customer_id", required = false) Long customerId,
                                                   @RequestParam(value = "email", required = false) String email,
                                                   @RequestParam(value = "phone", required = false) String phone) {
        List<CustomerDTO> customers = customerService.getCustomers(customerId, email, phone);

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

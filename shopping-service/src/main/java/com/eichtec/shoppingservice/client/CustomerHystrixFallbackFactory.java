package com.eichtec.shoppingservice.client;

import com.eichtec.shoppingservice.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerHystrixFallbackFactory  implements CustomerClient{
    @Override
    public ResponseEntity<Customer> getCustomer(Long id) {
        Customer customer = Customer.builder()
                .firstName("")
                .lastName("")
                .email("")
                .photoUrl("").build();

        return ResponseEntity.ok(customer);
    }
}

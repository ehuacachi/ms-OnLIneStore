package com.eichtec.customerservice.service;

import com.eichtec.customerservice.entity.Customer;
import com.eichtec.customerservice.entity.Region;
import com.eichtec.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        List<Customer> customers = customerRepository.findByRegion(region);
        if(customers.isEmpty()){
            return null;
        }
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerBD = customerRepository.findByNumberID(customer.getNumberID());
        if(customerBD != null){
            return customerBD;
        }
        customer.setState("CREATE");
        customerBD = customerRepository.save(customer);
        return customerBD;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerBD = getCustomer(customer.getId());
        if(customerBD == null){
            return null;
        }
        customerBD.setFirstName(customer.getFirstName());
        customerBD.setLastName(customer.getLastName());
        customerBD.setEmail(customer.getEmail());
        customerBD.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(customerBD);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerBD = getCustomer(customer.getId());
        if(customerBD == null){
            return null;
        }
        customerBD.setState("DELETE");
        return customerRepository.save(customerBD);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}

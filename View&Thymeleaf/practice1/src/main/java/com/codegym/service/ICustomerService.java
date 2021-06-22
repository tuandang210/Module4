package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    Customer create(Customer customer);

    Customer edit(int id, Customer customer);

    void delete(int id);
}

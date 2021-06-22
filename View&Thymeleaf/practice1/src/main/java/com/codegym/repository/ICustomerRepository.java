package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.List;

public interface ICustomerRepository {

    List<Customer> findAll();

    Customer findById(int id);

    Customer create(Customer customer);

    Customer edit(int id, Customer customer);

    void delete(int id);
}

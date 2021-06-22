package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository{

    private static List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer(1, "John", "john@codegym.vn", "Hanoi"));
        customers.add(new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        customers.add(new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
        customers.add(new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        customers.add(new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        customers.add(new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(int id) {
        for(Customer customer : customers){
            if(customer.getId()==id){
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer create(Customer customer) {
        customers.add(customers.size(),customer);
        return customer;
    }

    @Override
    public Customer edit(int id, Customer customer) {
        for (int i=0; i<customers.size();i++){
            if(customers.get(i).getId()== id){
                customers.set(i, customer);
            }
        }
        return customer;
    }

    @Override
    public void delete(int id) {
        for (int i=0; i<customers.size();i++){
            if(customers.get(i).getId() == id){
                customers.remove(i);
                break;
            }
        }
    }
}

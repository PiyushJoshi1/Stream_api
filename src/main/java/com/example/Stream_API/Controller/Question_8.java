package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Customer;
import com.example.Stream_API.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question_8 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q8")
    public String Question_8(){

        /*Q8  Produce a data map with order records grouped by customer*/

        Map<Customer, List<Order>> solution = orderRepo.findAll().stream().collect(Collectors.groupingBy(Order::getCustomer));
        for(Customer c : solution.keySet()){
            System.out.println(c + " " + solution.get(c));
        }


        return solution.toString();
    }

}

package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Order;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

public class Question_3 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q3")
    public String Question_3(){
        /*Q3. Get the 3 most recent placed order */

        List<Order> solution = orderRepo.findAll().stream().sorted(Comparator.comparing(Order::getOrderDate).reversed()).limit(3).toList();
        solution.forEach(System.out::println);
        return solution.toString();
    }

}

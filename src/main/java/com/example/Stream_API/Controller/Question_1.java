package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Question_1 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q1")
    public String Question_1(){
        List<Product> sol1 = orderRepo.findAll().stream()
                .filter(o-> o.getCustomer().getTier()==2)
                .filter(o-> o.getOrderDate().compareTo(LocalDate.of(2010,2,1))>=0)
                .filter(o->o.getOrderDate().compareTo(LocalDate.of(2013,4,1))<=0)
                .flatMap(o->o.getProducts().stream()).collect(Collectors.toList());
        System.out.println(sol1);
        return sol1.toString();
    }

}

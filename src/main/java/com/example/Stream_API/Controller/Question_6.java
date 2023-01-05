package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

public class Question_6 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q6")
    public double Question_6(){

        /*Q6 Calculate order average payment placed on 8-Jan-2012 */

        double solution = orderRepo.findAll().stream()
                .filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,8))==0)
                .peek(o->{
                    System.out.println(o);
                    o.getProducts().forEach(System.out::println);
                })
                .flatMap(o->o.getProducts().stream())
                .mapToDouble(o->o.getPrice()).average().orElse(0);

        System.out.println(solution);

        return solution;
    }

}

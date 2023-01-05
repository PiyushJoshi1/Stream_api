package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Order;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Question_4 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q4")
    public String Question_3(){
        /*Q4. Get a list of orders which were ordered on 8-Jan-2012, log the order records to the console and then return its product list*/

        List<Product> solution = orderRepo.findAll().stream().filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,8))==0).peek(o->{
            System.out.println(o);
            System.out.println("*******");
        }).flatMap(o->o.getProducts().stream()).distinct().collect(Collectors.toList());

        solution.forEach(System.out::println);
        return solution.toString();
    }

}

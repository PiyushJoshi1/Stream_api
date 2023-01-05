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

public class Question_5 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q5")
    public double Question_5(){
        /*Q5 Calculate total lump sum of all orders placed in Jan 2012*/

        double solution = orderRepo.findAll().stream().filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,1))>=0)
                .filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,31))<=0)
                .flatMap(o->{
                            System.out.println(o);
                            o.getProducts().forEach(System.out::println);
                            return o.getProducts().stream();
                        }
                ).mapToDouble(o->o.getPrice()).sum();
        System.out.println(solution);

        return solution;
    }

}

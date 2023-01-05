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

public class Question_2 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q2")
    public String Question_2(){
        Product solution = productRepo.findAll().stream().filter(o->o.getCategory().equals("Baby"))
                .min((obj1,obj2)->obj1.getPrice().compareTo(obj2.getPrice())).orElse(null);

        Product soluton_1 = productRepo.findAll().stream().filter((o->o.getCategory().equals("Baby"))).sorted((obj1,obj2)->obj1.getPrice().compareTo(obj2.getPrice())).findFirst().orElse(null);
        System.out.println(solution);
        System.out.println(soluton_1);

        return soluton_1.toString();
    }

}

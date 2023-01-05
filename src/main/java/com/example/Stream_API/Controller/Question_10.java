package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Order;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Question_10 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q10")
    public String Question_10(){

        /*Q11 Obtain a data map with list of product name by category*/

        Map<String, List<String>> solution = productRepo.findAll().stream().collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.mapping(product ->product.getName(), Collectors.toList()))
        );
        for(String p : solution.keySet()){
            System.out.println(p + " " + solution.get(p));
        }

        return solution.toString();
    }

}

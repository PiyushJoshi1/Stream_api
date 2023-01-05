package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Customer;
import com.example.Stream_API.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Question_9 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q9")
    public String Question_9(){

        /*Q9  Produce a data map with order record and product total sum*/

        Map<Order,Long> solution = orderRepo.findAll().stream().collect(Collectors.toMap(
                Function.identity(),
                o-> (long) o.getProducts().stream().mapToDouble(obj->obj.getPrice()).sum()
        ));
        for(Order o : solution.keySet()){
            System.out.println(o + " " + solution.get(o));
        }

        return solution.toString();
    }

}

package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class Question_7 {
    /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/Q7")
    public String Question_7(){

        /*Q7 Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Baby”*/
        DoubleSummaryStatistics solution =  productRepo.findAll().stream()
                .mapToDouble(p->p.getPrice()).summaryStatistics();
        System.out.println("Max = " + solution.getMax() + " Min = " + solution.getMin() + " Sum = " + solution.getSum() + " count =  " + solution.getCount());

        /*Q8 Obtain a data map with order id and order’s product count*/

        Map<Long,Integer> sol8 = orderRepo.findAll().stream().collect(Collectors.toMap(o->o.getId(), o->o.getProducts().size()));
        for(Long l : sol8.keySet()){
            System.out.println(l + " " + sol8.get(l));
        }

        return solution.toString();
    }

}

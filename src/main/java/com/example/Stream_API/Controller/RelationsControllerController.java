package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Customer;
import com.example.Stream_API.model.Order;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class RelationsControllerController {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/")
    public String Demo(){

        // for Add Customers to DataBase
        Customer c1 = new Customer(1L,"Dev",2,new ArrayList<>());
        Customer c2 = new Customer(2L,"Lory",1,new ArrayList<>());
        Customer c3 = new Customer(3L,"Boss",2,new ArrayList<>());
        Customer c4 = new Customer(4L,"Baba",1,new ArrayList<>());
        Customer c5 = new Customer(5L,"Devi",2,new ArrayList<>());
        Customer c6 = new Customer(6L,"Lora",1,new ArrayList<>());

        customerRepo.save(c1);
        customerRepo.save(c2);
        customerRepo.save(c3);
        customerRepo.save(c4);
        customerRepo.save(c5);
        customerRepo.save(c6);
        //End Customers addition

        //For Add Products to DataBase
        Product p1 = new Product(1L,"Soap","Baby",200D,new HashSet<>());
        Product p2 = new Product(2L,"basmati","rice",500D,new HashSet<>());
        Product p3 = new Product(3L,"shampoo","Baby",120D,new HashSet<>());
        Product p4 = new Product(4L,"tukri","rice",580D,new HashSet<>());
        Product p5 = new Product(5L,"powder","Baby",260D,new HashSet<>());
        Product p6 = new Product(6L,"Mogra","rice",620D,new HashSet<>());

        productRepo.save(p1);
        productRepo.save(p2);
        productRepo.save(p3);
        productRepo.save(p4);
        productRepo.save(p5);
        productRepo.save(p6);
        // End Products.

        //For Add Orders to DataBase
        Order o1 = new Order(1L,LocalDate.of(2010, 1, 8),LocalDate.of(2011, 3, 8),"waiting",null,new HashSet<>());
        Order o2 = new Order(2L,LocalDate.of(2011, 1, 8),LocalDate.of(2012, 3, 8),"Done",null,new HashSet<>());
        Order o3 = new Order(3L,LocalDate.of(2012, 1, 8),LocalDate.of(2013, 3, 8),"Done",null,new HashSet<>());
        Order o4 = new Order(4L,LocalDate.of(2012, 1, 8),LocalDate.of(2014, 3, 8),"Done",null,new HashSet<>());
        Order o5 = new Order(5L,LocalDate.of(2014, 1, 8),LocalDate.of(2015, 3, 8),"waiting",null,new HashSet<>());
        Order o6 = new Order(6L,LocalDate.of(2012, 1, 8),LocalDate.of(2016, 3, 8),"Done",null,new HashSet<>());

        orderRepo.save(o1);
        orderRepo.save(o2);
        orderRepo.save(o3);
        orderRepo.save(o4);
        orderRepo.save(o5);
        orderRepo.save(o6);
        // End Order Saved

        /* Relations */

        //Order Customer Relations

        for(int i=1;i<7;i++) {
            Order o = orderRepo.findById(Long.valueOf(i)).orElse(null);
            Customer c = customerRepo.findById(Long.valueOf(i)).orElse(null);
            o.setCustomer(c);
            c.getOrders().add(o);
            orderRepo.save(o);
        }
        //End Order Customer Relations

        //Order Product Relations
        for(int i=1;i<7;i++){
            Order o =  orderRepo.findById(Long.valueOf(i)).orElse(null);
            Product p = productRepo.findById(Long.valueOf(i)).orElse(null);
            o.getProducts().add(p);
            p.getOrders().add(o);
            orderRepo.save(o);
        }
        for(int i=6;i>=1;i--){
            Order o =  orderRepo.findById(Long.valueOf(7-i)).orElse(null);
            Product p = productRepo.findById(Long.valueOf(i)).orElse(null);
            o.getProducts().add(p);
            p.getOrders().add(o);
            orderRepo.save(o);
        }
        //End Order Product Relations

        /*Relation End */
        return "This is part ";
    }
}

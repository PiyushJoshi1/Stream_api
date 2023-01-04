package com.example.Stream_API.Controller;

import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Customer;
import com.example.Stream_API.model.Order;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
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

        /*Q1. Obtain a list of products ordered by customer of tier 2 between 01-Feb-2010 and 01-Apr-2013 */

        List<Product> sol1 = orderRepo.findAll().stream()
                        .filter(o-> o.getCustomer().getTier()==2)
                        .filter(o-> o.getOrderDate().compareTo(LocalDate.of(2010,2,1))>=0)
                        .filter(o->o.getOrderDate().compareTo(LocalDate.of(2013,4,1))<=0)
                                .flatMap(o->o.getProducts().stream()).collect(Collectors.toList());
        System.out.println(sol1);

        /*Q2. Get the cheapest products of “Baby” category */


        Product sol2 = productRepo.findAll().stream().filter(o->o.getCategory().equals("Baby"))
                .min((obj1,obj2)->obj1.getPrice().compareTo(obj2.getPrice())).orElse(null);

        Product sol2_1 = productRepo.findAll().stream().filter((o->o.getCategory().equals("Baby"))).sorted((obj1,obj2)->obj1.getPrice().compareTo(obj2.getPrice())).findFirst().orElse(null);
        System.out.println(sol2);
        System.out.println(sol2_1);

        /*Q3. Get the 3 most recent placed order */

        List<Order> sol3 = orderRepo.findAll().stream().sorted(Comparator.comparing(Order::getOrderDate).reversed()).limit(3).toList();
        sol3.forEach(System.out::println);

        /*Q4. Get a list of orders which were ordered on 8-Jan-2012, log the order records to the console and then return its product list*/

        List<Product> sol4 = orderRepo.findAll().stream().filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,8))==0).peek(o->{
            System.out.println(o);
            System.out.println("*******");
        }).flatMap(o->o.getProducts().stream()).distinct().collect(Collectors.toList());

        sol4.forEach(System.out::println);

        /*Q5 Calculate total lump sum of all orders placed in Jan 2012*/

        double sol5 = orderRepo.findAll().stream().filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,1))>=0)
                        .filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,31))<=0)
                .flatMap(o->{
                    System.out.println(o);
                    o.getProducts().forEach(System.out::println);
                    return o.getProducts().stream();
                }
                ).mapToDouble(o->o.getPrice()).sum();
        System.out.println(sol5);

        /*Q6 Calculate order average payment placed on 8-Jan-2012 */

        double sol6 = orderRepo.findAll().stream()
                .filter(o->o.getOrderDate().compareTo(LocalDate.of(2012,1,8))==0)
                .peek(o->{
                    System.out.println(o);
                    o.getProducts().forEach(System.out::println);
                })
                .flatMap(o->o.getProducts().stream())
                .mapToDouble(o->o.getPrice()).average().orElse(0);

        System.out.println(sol6);

        /*Q7 Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Baby”*/
        DoubleSummaryStatistics statistics_Sol7 =  productRepo.findAll().stream()
                .mapToDouble(p->p.getPrice()).summaryStatistics();
        System.out.println("Max = " + statistics_Sol7.getMax() + " Min = " + statistics_Sol7.getMin() + " Sum = " + statistics_Sol7.getSum() + " count =  " + statistics_Sol7.getCount());

        /*Q8 Obtain a data map with order id and order’s product count*/

        Map<Long,Integer> sol8 = orderRepo.findAll().stream().collect(Collectors.toMap(o->o.getId(),o->o.getProducts().size()));
        for(Long l : sol8.keySet()){
            System.out.println(l + " " + sol8.get(l));
        }

        /*Q9  Produce a data map with order records grouped by customer*/

        Map<Customer,List<Order>> sol9 = orderRepo.findAll().stream().collect(Collectors.groupingBy(Order::getCustomer));
        for(Customer c : sol9.keySet()){
            System.out.println(c + " " + sol9.get(c));
        }

        /*Q10  Produce a data map with order record and product total sum*/

        Map<Order,Long> sol10 = orderRepo.findAll().stream().collect(Collectors.toMap(
                Function.identity(),
                o-> (long) o.getProducts().stream().mapToDouble(obj->obj.getPrice()).sum()
        ));
        for(Order o : sol10.keySet()){
            System.out.println(o + " " + sol10.get(o));
        }

        /*Q11 Obtain a data map with list of product name by category*/

        Map<String,List<String>> sol11 = productRepo.findAll().stream().collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.mapping(product ->product.getName(), Collectors.toList()))
        );
        for(String p : sol11.keySet()){
            System.out.println(p + " " + sol11.get(p));
        }

        /*Q12 Get the most expensive product by category*/
        Map<String,Optional<Product>> sol12 = productRepo.findAll().stream().collect(Collectors.groupingBy(Product::getCategory,Collectors.maxBy(Comparator.comparing(Product::getPrice))));
        System.out.println(sol12);

        return "This is part ";
    }
}

package com.example.Stream_API.Service;

import com.example.Stream_API.Repo.OrderRepo;
import com.example.Stream_API.model.Customer;
import com.example.Stream_API.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo ;

    public List<Order> getOrders(){
        return orderRepo.findAll();
    }
    public void setOrder(Order order){
        orderRepo.save(order);
    }
}

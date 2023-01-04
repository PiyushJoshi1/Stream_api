package com.example.Stream_API.Repo;

import com.example.Stream_API.model.Order;
import com.example.Stream_API.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

}

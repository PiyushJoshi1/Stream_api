package com.example.Stream_API.Service;

import com.example.Stream_API.Repo.ProductRepo;
import com.example.Stream_API.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public List<Product> getProducts(){
        return productRepo.findAll();
    }
    public void setProduct(Product p){
        productRepo.save(p);
    }
}

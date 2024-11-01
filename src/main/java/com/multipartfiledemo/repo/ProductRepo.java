package com.multipartfiledemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multipartfiledemo.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}

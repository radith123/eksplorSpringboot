package com.example.notif.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notif.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}

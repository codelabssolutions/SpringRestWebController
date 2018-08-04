package com.hotfix.jpa.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotfix.jpa.enitity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{ 

}
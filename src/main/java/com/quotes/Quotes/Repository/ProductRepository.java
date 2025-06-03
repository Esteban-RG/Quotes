package com.quotes.Quotes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quotes.Quotes.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


}

package com.quotes.Quotes.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quotes.Quotes.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.description LIKE CONCAT('%', :description, '%')")
    List<Product> findByCategoryAndDescription(@Param("categoryId") Long category, @Param("description") String description);

    List<Product> findByDescriptionContaining(String description);


    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategory(@Param("categoryId") Long category);


}

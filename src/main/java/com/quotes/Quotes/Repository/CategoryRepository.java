package com.quotes.Quotes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quotes.Quotes.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


}

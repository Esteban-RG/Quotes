package com.quotes.Quotes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quotes.Quotes.Model.UnitOfMeasure;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure,Long> {


}

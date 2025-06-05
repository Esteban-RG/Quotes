package com.quotes.Quotes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quotes.Quotes.Model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {


}

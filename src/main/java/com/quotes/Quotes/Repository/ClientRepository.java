package com.quotes.Quotes.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quotes.Quotes.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


}

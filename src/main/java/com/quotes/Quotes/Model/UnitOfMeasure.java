package com.quotes.Quotes.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UnitOfMeasure {

    private @Id @GeneratedValue Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "unitOfMeasure", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    protected UnitOfMeasure(){
        this(null);
    }

    UnitOfMeasure(String name){
        this.name = name;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

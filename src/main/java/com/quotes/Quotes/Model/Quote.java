package com.quotes.Quotes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;


@Entity
public class Quote {

    private @Id @GeneratedValue Long id;

    @Column(name = "project", nullable = false)
    private String project;

    @Column(name = "total", nullable = false)
    private Float total;

    @Column(name = "date", nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    protected Quote() {
        this(null,null,null,null);
    }

    Quote(String project, Float total, String date, Client client){
        this.project = project;
        this.total = total;
        this.date = date;
        this.client = client;
    }

    // Setters

    public void setProject(String project){
        this.project = project;
    }

    public void setTotal(Float total){
        this.total = total;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setClient(Client client){
        this.client = client;
    }






    // Getters 

    public String getProject(){
        return this.project;
    }

    public Float getTotal(){
        return this.total;
    }

    public String getDate(){
        return this.date;
    }

    public Client getClient(){
        return this.client;
    }



}

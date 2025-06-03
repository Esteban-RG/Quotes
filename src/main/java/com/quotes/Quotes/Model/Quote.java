package com.quotes.Quotes.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuoteProduct> quoteProducts = new HashSet<>();

    protected Quote() {
        this(null,null,null,null);
    }

    Quote(String project, Float total, String date, Client client){
        this.project = project;
        this.total = total;
        this.date = date;
        this.client = client;
    }

    // Métodos para gestionar productos
    public void addProduct(Product product, Integer quantity) {
        // Buscar si el producto ya existe en la cotización
        QuoteProduct existingQuoteProduct = quoteProducts.stream()
            .filter(qp -> qp.getProduct().equals(product))
            .findFirst()
            .orElse(null);

        if (existingQuoteProduct != null) {
            // Si el producto existe, actualizar la cantidad
            existingQuoteProduct.setQuantity(existingQuoteProduct.getQuantity() + quantity);
        } else {
            // Si el producto no existe, crear uno nuevo
            QuoteProduct quoteProduct = new QuoteProduct(this, product, quantity);
            quoteProducts.add(quoteProduct);
        }
        
        updateTotal();
    }

    public void removeProduct(Product product) {
        quoteProducts.removeIf(qp -> qp.getProduct().equals(product));
        updateTotal();
    }

    public void updateProductQuantity(Product product, Integer newQuantity) {
        QuoteProduct existingQuoteProduct = quoteProducts.stream()
            .filter(qp -> qp.getProduct().equals(product))
            .findFirst()
            .orElse(null);

        if (existingQuoteProduct != null) {
            if (newQuantity <= 0) {
                removeProduct(product);
            } else {
                existingQuoteProduct.setQuantity(newQuantity);
                updateTotal();
            }
        }
    }

    private void updateTotal() {
        this.total = (float) quoteProducts.stream()
            .mapToDouble(QuoteProduct::getSubtotal)
            .sum();
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

    public Set<QuoteProduct> getQuoteProducts() {
        return quoteProducts;
    }

    public void setQuoteProducts(Set<QuoteProduct> quoteProducts) {
        this.quoteProducts = quoteProducts;
        updateTotal();
    }
}

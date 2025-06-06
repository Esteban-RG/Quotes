package com.quotes.Quotes.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.CategoryDTO;
import com.quotes.Quotes.DTO.ClientDTO;
import com.quotes.Quotes.DTO.ProductDTO;
import com.quotes.Quotes.DTO.QuoteCreateDTO;
import com.quotes.Quotes.DTO.QuoteDTO;
import com.quotes.Quotes.DTO.QuoteProductCreateDTO;
import com.quotes.Quotes.DTO.QuoteProductDTO;
import com.quotes.Quotes.DTO.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.Client;
import com.quotes.Quotes.Model.Product;
import com.quotes.Quotes.Model.Quote;
import com.quotes.Quotes.Repository.ClientRepository;
import com.quotes.Quotes.Repository.ProductRepository;
import com.quotes.Quotes.Repository.QuoteRepository;

import jakarta.transaction.Transactional;

@Service
public class QuoteService {
    private final QuoteRepository repository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    

    public QuoteService(QuoteRepository repository, ClientRepository clientRepository, ProductRepository productRepository){
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Transactional()
    public List<QuoteDTO> getAll(){
        return repository.findAll()
        .stream()
        .map(quote -> new QuoteDTO(
            quote.getId(), 
            quote.getDate(), 
            quote.getProject(), 
            quote.getTotal(), 
            new ClientDTO(
                quote.getClient().getId(),
                quote.getClient().getEmail(),
                quote.getClient().getName(),
                quote.getClient().getPhoneNumber()
            ),
            quote.getQuoteProducts().stream()
            .map(qp -> new QuoteProductDTO(
                qp.getId(),
                qp.getQuantity(),
                qp.getSubtotal(),
                new ProductDTO(
                    qp.getProduct().getId(),
                    qp.getProduct().getDescription(),
                    qp.getProduct().getImgPath(),
                    qp.getProduct().getPrice(),
                    new CategoryDTO(
                        qp.getProduct().getCategory().getId(),
                        qp.getProduct().getCategory().getName()
                    ),
                    new UnitOfMeasureDTO(
                        qp.getProduct().getUnitOfMeasure().getId(),
                        qp.getProduct().getUnitOfMeasure().getName()
                    )
                )
            ))
            .collect(Collectors.toList())
        ))
        .collect(Collectors.toList());
    }

    @Transactional
    public Optional<QuoteDTO> getById(Long id){
        return repository.findById(id)
        .map(quote -> new QuoteDTO(
            quote.getId(), 
            quote.getDate(), 
            quote.getProject(), 
            quote.getTotal(), 
            new ClientDTO(
                quote.getClient().getId(),
                quote.getClient().getEmail(),
                quote.getClient().getName(),
                quote.getClient().getPhoneNumber()
            ),
            quote.getQuoteProducts().stream()
            .map(qp -> new QuoteProductDTO(
                qp.getId(),
                qp.getQuantity(),
                qp.getSubtotal(),
                new ProductDTO(
                    qp.getProduct().getId(),
                    qp.getProduct().getDescription(),
                    qp.getProduct().getImgPath(),
                    qp.getProduct().getPrice(),
                    new CategoryDTO(
                        qp.getProduct().getCategory().getId(),
                        qp.getProduct().getCategory().getName()
                    ),
                    new UnitOfMeasureDTO(
                        qp.getProduct().getUnitOfMeasure().getId(),
                        qp.getProduct().getUnitOfMeasure().getName()
                    )
                )
            ))
            .collect(Collectors.toList())
        ));
    }

    public Quote create(QuoteCreateDTO dto){
        Client client = clientRepository.findById(dto.clientId())
        .orElseThrow(() -> new RuntimeException("Client not found"));

        Quote quote = new Quote();
        quote.setDate(dto.date());
        quote.setProject(dto.project());
        quote.setClient(client);

        for (QuoteProductCreateDTO qpDto : dto.products()) {
            Product producto = productRepository.findById(qpDto.productId())
            .orElseThrow(() -> new RuntimeException("Product not found"));

            quote.addProduct(producto, qpDto.quantity());
        }
        return repository.save(quote);
    }

    @Transactional
    public Optional<Quote> update(Long id, QuoteCreateDTO newData) {
        
        return repository.findById(id)
                .map(existing -> {
                    Client client = clientRepository.findById(newData.clientId())
                        .orElseThrow(() -> new RuntimeException("Client not found"));

                    existing.setDate(newData.date());
                    existing.setProject(newData.project());
                    existing.setClient(client);

                    // Eliminar todos los productos de la quote

                    new HashSet<>(existing.getQuoteProducts()).forEach(qp -> {
                        existing.removeProduct(qp.getProduct());
                    });

                    // Agregar nuevos productos a la quote

                    for (QuoteProductCreateDTO qpDto : newData.products()) {
                        Product producto = productRepository.findById(qpDto.productId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                        existing.addProduct(producto, qpDto.quantity());
                    }


                    return repository.save(existing);
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(quote -> {
            repository.delete(quote);
            return true;
        }).orElse(false);
    }
}

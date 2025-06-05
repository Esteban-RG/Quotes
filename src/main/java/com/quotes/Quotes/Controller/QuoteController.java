package com.quotes.Quotes.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quotes.Quotes.DTO.CategoryDTO;
import com.quotes.Quotes.DTO.ClientDTO;
import com.quotes.Quotes.DTO.ProductDTO;
import com.quotes.Quotes.DTO.QuoteCreateDTO;
import com.quotes.Quotes.DTO.QuoteDTO;
import com.quotes.Quotes.DTO.QuoteProductDTO;
import com.quotes.Quotes.DTO.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.Quote;
import com.quotes.Quotes.Services.QuoteService;




@RestController
@RequestMapping("/api/quotes")
public class QuoteController {


    private final QuoteService service;

    public QuoteController(QuoteService service){
        this.service = service;
    }


    @GetMapping
    public List<QuoteDTO> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteDTO> getProductById(@PathVariable Long id){
        return service.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuoteDTO> create(@RequestBody QuoteCreateDTO dto) {
        Quote created = service.create(dto);

        List<QuoteProductDTO> productDTOs = created.getQuoteProducts()
            .stream()
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
            .collect(Collectors.toList());

        QuoteDTO response = new QuoteDTO(
            created.getId(),
            created.getDate(),
            created.getProject(),
            created.getTotal(),
            new ClientDTO(
                created.getClient().getId(),
                created.getClient().getEmail(),
                created.getClient().getName(),
                created.getClient().getPhoneNumber()
            ),
            productDTOs
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<QuoteDTO> update(@PathVariable Long id, @RequestBody QuoteCreateDTO dto){

        return service.update(id, dto)
        .map(updated -> {
            QuoteDTO response = new QuoteDTO(
                updated.getId(),
                updated.getDate(),
                updated.getProject(),
                updated.getTotal(),
                new ClientDTO(
                    updated.getClient().getId(),
                    updated.getClient().getEmail(),
                    updated.getClient().getName(),
                    updated.getClient().getPhoneNumber()
                ),
                updated.getQuoteProducts()
                .stream()
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
                );
            return ResponseEntity.ok(response);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}

package com.quotes.Quotes.Controller;

import java.util.List;

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

import com.quotes.Quotes.DTO.Category.CategoryDTO;
import com.quotes.Quotes.DTO.Product.ProductCreateDTO;
import com.quotes.Quotes.DTO.Product.ProductDTO;
import com.quotes.Quotes.DTO.Product.Search.SearchProduct;
import com.quotes.Quotes.DTO.UnitOfMeasure.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.Product;
import com.quotes.Quotes.Services.ProductService;




@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }


    @GetMapping
    public List<ProductDTO> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return service.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto){
        Product created = service.create(dto);

        ProductDTO response = new ProductDTO(
            created.getId(),
            created.getDescription(),
            created.getImgPath(),
            created.getPrice(),
            new CategoryDTO(created.getCategory().getId(), created.getCategory().getName()),
            new UnitOfMeasureDTO(created.getUnitOfMeasure().getId(), created.getUnitOfMeasure().getName())
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductCreateDTO dto){
        return service.update(id, dto)
        .map(updated -> {
            ProductDTO response = new ProductDTO(
                updated.getId(),
                updated.getDescription(),
                updated.getImgPath(),
                updated.getPrice(),
                new CategoryDTO(updated.getCategory().getId(), updated.getCategory().getName()),
                new UnitOfMeasureDTO(updated.getUnitOfMeasure().getId(), updated.getUnitOfMeasure().getName())
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




    // Search

    @PostMapping("/search")
    public List<ProductDTO> multiFieldSearch(@RequestBody SearchProduct search){
        
           return service.search(search);
        
    }
}

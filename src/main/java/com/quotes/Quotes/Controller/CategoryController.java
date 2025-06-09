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

import com.quotes.Quotes.DTO.Category.CategoryCreateDTO;
import com.quotes.Quotes.DTO.Category.CategoryDTO;
import com.quotes.Quotes.Model.Category;
import com.quotes.Quotes.Services.CategoryService;




@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }


    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        return service.getById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCreateDTO dto){
        Category created = service.create(dto);

        CategoryDTO response = new CategoryDTO(
            created.getId(),
            created.getName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryCreateDTO dto){
        return service.update(id, dto)
        .map(updated -> {
            CategoryDTO response = new CategoryDTO(
                updated.getId(),
                updated.getName()
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

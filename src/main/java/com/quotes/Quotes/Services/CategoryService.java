package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.CategoryDTO;
import com.quotes.Quotes.Model.Category;
import com.quotes.Quotes.Repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

   
    public List<CategoryDTO> getAll(){
        return repository.findAll()
        .stream()
        .map(cat -> new CategoryDTO(cat.getId(), cat.getName()))
        .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getById(Long id){
        return repository.findById(id)
        .map(cat -> new CategoryDTO(cat.getId(), cat.getName()));
    }

    public Category create(Category category){
        return repository.save(category);
    }

    public Optional<Category> update(Long id, Category newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.getName());
                    return repository.save(existing);
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(category -> {
            repository.delete(category);
            return true;
        }).orElse(false);
    }
}

package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quotes.Quotes.DTO.Category.CategoryCreateDTO;
import com.quotes.Quotes.DTO.Category.CategoryDTO;
import com.quotes.Quotes.Model.Category;
import com.quotes.Quotes.Repository.CategoryRepository;

import jakarta.transaction.Transactional;

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

    public Category create(CategoryCreateDTO dto){
        Category category = new Category();
        category.setName(dto.name());

        return repository.save(category);
    }

    @Transactional
    public Optional<Category> update(Long id, CategoryCreateDTO newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newData.name());
                    return repository.save(existing);
                });
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);

        if (optionalCategory.isEmpty()) {
            return false; 
        }

        Category category = optionalCategory.get();

        if (!category.getProducts().isEmpty()) {
            return false;
        }

        repository.delete(category);
        return true;
    }

}

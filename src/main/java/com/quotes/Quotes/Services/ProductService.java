package com.quotes.Quotes.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.quotes.Quotes.DTO.Category.CategoryDTO;
import com.quotes.Quotes.DTO.Product.ProductCreateDTO;
import com.quotes.Quotes.DTO.Product.ProductDTO;
import com.quotes.Quotes.DTO.Product.Search.SearchProduct;
import com.quotes.Quotes.DTO.UnitOfMeasure.UnitOfMeasureDTO;
import com.quotes.Quotes.Model.Category;
import com.quotes.Quotes.Model.Product;
import com.quotes.Quotes.Model.UnitOfMeasure;
import com.quotes.Quotes.Repository.CategoryRepository;
import com.quotes.Quotes.Repository.ProductRepository;
import com.quotes.Quotes.Repository.UnitOfMeasureRepository;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository){
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

   
    public List<ProductDTO> getAll(){
        return repository.findAll()
        .stream()
        .map(product -> new ProductDTO(
            product.getId(), 
            product.getDescription(), 
            product.getImgPath(), 
            product.getPrice(), 
            new CategoryDTO(
                product.getCategory().getId(),
                product.getCategory().getName()
            ), 
            new UnitOfMeasureDTO(
                product.getUnitOfMeasure().getId(),
                product.getUnitOfMeasure().getName()
            )
        ))
        .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getById(Long id){
        return repository.findById(id)
        .map(product -> new ProductDTO(
            product.getId(), 
            product.getDescription(), 
            product.getImgPath(), 
            product.getPrice(), 
            new CategoryDTO(
                product.getCategory().getId(),
                product.getCategory().getName()
            ), 
            new UnitOfMeasureDTO(
                product.getUnitOfMeasure().getId(),
                product.getUnitOfMeasure().getName()
            )
        ));
    }

    public Product create(ProductCreateDTO dto){
        Category category = categoryRepository.findById(dto.categoryId())
        .orElseThrow(() -> new RuntimeException("Category not found"));

        UnitOfMeasure unit = unitOfMeasureRepository.findById(dto.unitOfMeasureId())
            .orElseThrow(() -> new RuntimeException("Unit of Measure not found"));

        Product product = new Product();
        product.setDescription(dto.description());
        product.setImgPath(dto.imgPath());
        product.setPrice(dto.price());
        product.setCategory(category);
        product.setUnitOfMeasure(unit);

        return repository.save(product);
    }

    public Optional<Product> update(Long id, ProductCreateDTO newData) {
        
        return repository.findById(id)
                .map(existing -> {
                    Category category = categoryRepository.findById(newData.categoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

                    UnitOfMeasure unit = unitOfMeasureRepository.findById(newData.unitOfMeasureId())
                        .orElseThrow(() -> new RuntimeException("Unit of Measure not found"));

                    existing.setDescription(newData.description());
                    existing.setImgPath(newData.imgPath());
                    existing.setPrice(newData.price());
                    existing.setCategory(category);
                    existing.setUnitOfMeasure(unit);
                    return repository.save(existing);
                });
    }

    public boolean delete(Long id) {
        return repository.findById(id).map(product -> {
            repository.delete(product);
            return true;
        }).orElse(false);
    }


    public List<ProductDTO> search(SearchProduct search) {
    List<Product> res;

    boolean hasDescription = StringUtils.hasText(search.description());
    boolean hasCategory = search.categoryId() != null && search.categoryId() != 0;

    if (hasDescription && hasCategory) {
        res = repository.findByCategoryAndDescription(search.categoryId(), search.description());
    } else if (hasDescription) {
        res = repository.findByDescriptionContaining(search.description());
    } else if (hasCategory) {
        res = repository.findByCategory(search.categoryId());
    } else {
        res = repository.findAll(); 
    }

    return res.stream()
            .map(product -> new ProductDTO(
                    product.getId(),
                    product.getDescription(),
                    product.getImgPath(),
                    product.getPrice(),
                    new CategoryDTO(
                            product.getCategory().getId(),
                            product.getCategory().getName()
                    ),
                    new UnitOfMeasureDTO(
                            product.getUnitOfMeasure().getId(),
                            product.getUnitOfMeasure().getName()
                    )
            ))
            .collect(Collectors.toList());
}

}

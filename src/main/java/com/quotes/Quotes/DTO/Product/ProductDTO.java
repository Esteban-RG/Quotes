package com.quotes.Quotes.DTO.Product;

import com.quotes.Quotes.DTO.Category.CategoryDTO;
import com.quotes.Quotes.DTO.UnitOfMeasure.UnitOfMeasureDTO;

public record ProductDTO(Long id, String description, String img_path, Float price, CategoryDTO category, UnitOfMeasureDTO unitOfMeasure) {

}




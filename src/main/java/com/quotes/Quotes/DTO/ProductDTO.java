package com.quotes.Quotes.DTO;

import com.quotes.Quotes.Model.Category;
import com.quotes.Quotes.Model.UnitOfMeasure;

public record ProductDTO(Long id, String name, String description, String price, String img_path, Category category, UnitOfMeasure unitOfMeasure) {

}

package com.quotes.Quotes.DTO;

public record ProductDTO(Long id, String description, String img_path, Float price, CategoryDTO category, UnitOfMeasureDTO unitOfMeasure) {

}




package com.quotes.Quotes.DTO.Product;

public record ProductCreateDTO(
    String description,
    String imgPath,
    Float price,
    Long categoryId,
    Long unitOfMeasureId
) {}
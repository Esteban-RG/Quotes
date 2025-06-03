package com.quotes.Quotes.DTO;

public record ProductCreateDTO(
    String description,
    String imgPath,
    Float price,
    Long categoryId,
    Long unitOfMeasureId
) {}
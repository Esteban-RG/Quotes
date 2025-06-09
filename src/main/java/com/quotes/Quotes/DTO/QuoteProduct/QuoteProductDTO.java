package com.quotes.Quotes.DTO.QuoteProduct;

import com.quotes.Quotes.DTO.Product.ProductDTO;

public record QuoteProductDTO(Long id, Integer quantity, Float subtotal, ProductDTO product) {

}

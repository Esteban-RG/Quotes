package com.quotes.Quotes.DTO.Quote;

import java.util.List;

import com.quotes.Quotes.DTO.QuoteProduct.QuoteProductCreateDTO;

public record QuoteCreateDTO(String date, String project, Long clientId, List<QuoteProductCreateDTO> products) {

}

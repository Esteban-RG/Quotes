package com.quotes.Quotes.DTO.Quote;

import java.util.List;

import com.quotes.Quotes.DTO.Client.ClientDTO;
import com.quotes.Quotes.DTO.QuoteProduct.QuoteProductDTO;

public record QuoteDTO(Long id, String date, String project, Float Total, ClientDTO client , List<QuoteProductDTO> products) {
}
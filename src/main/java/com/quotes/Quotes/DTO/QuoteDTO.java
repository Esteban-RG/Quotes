package com.quotes.Quotes.DTO;

import java.util.List;

public record QuoteDTO(Long id, String date, String project, Float Total, ClientDTO client , List<QuoteProductDTO> products) {
}
package com.quotes.Quotes.DTO;

import java.util.List;

public record QuoteCreateDTO(String date, String project, Long clientId, List<QuoteProductCreateDTO> products) {

}

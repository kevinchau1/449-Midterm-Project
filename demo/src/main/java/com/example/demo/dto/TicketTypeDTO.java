package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeDTO {
    private Long ticketTypeId;
    private String name;
    private BigDecimal price;
    private Integer quantityAvailable;
}

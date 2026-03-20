package com.example.demo.controller;

import com.example.demo.dto.CreateTicketTypeRequest;
import com.example.demo.dto.TicketTypeDTO;
import com.example.demo.service.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-types")
@RequiredArgsConstructor
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    @PostMapping
    public ResponseEntity<TicketTypeDTO> createTicketType(@RequestBody CreateTicketTypeRequest request) {
        TicketTypeDTO ticketType = ticketTypeService.createTicketType(request);
        return new ResponseEntity<>(ticketType, HttpStatus.CREATED);
    }
}

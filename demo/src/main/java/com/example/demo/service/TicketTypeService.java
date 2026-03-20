package com.example.demo.service;

import com.example.demo.dto.CreateTicketTypeRequest;
import com.example.demo.dto.TicketTypeDTO;
import com.example.demo.entity.Event;
import com.example.demo.entity.TicketType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    @Transactional
    public TicketTypeDTO createTicketType(CreateTicketTypeRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + request.getEventId()));

        TicketType ticketType = new TicketType();
        ticketType.setName(request.getName());
        ticketType.setPrice(request.getPrice());
        ticketType.setQuantityAvailable(request.getQuantityAvailable());
        ticketType.setEvent(event);

        TicketType savedTicketType = ticketTypeRepository.save(ticketType);

        return convertToDTO(savedTicketType);
    }

    private TicketTypeDTO convertToDTO(TicketType ticketType) {
        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(ticketType.getTicketTypeId());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setQuantityAvailable(ticketType.getQuantityAvailable());
        return dto;
    }
}

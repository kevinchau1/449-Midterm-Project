package com.example.demo.service;

import com.example.demo.dto.CreateEventRequest;
import com.example.demo.dto.EventResponseDTO;
import com.example.demo.dto.RevenueDTO;
import com.example.demo.dto.TicketTypeDTO;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventStatus;
import com.example.demo.entity.Organizer;
import com.example.demo.entity.Venue;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.OrganizerRepository;
import com.example.demo.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;

    @Transactional
    public EventResponseDTO createEvent(CreateEventRequest request) {
        Organizer organizer = organizerRepository.findById(request.getOrganizerId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found with id: " + request.getOrganizerId()));

        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found with id: " + request.getVenueId()));

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setStatus(request.getStatus());
        event.setOrganizer(organizer);
        event.setVenue(venue);

        Event savedEvent = eventRepository.save(event);

        return convertToResponseDTO(savedEvent);
    }

    public List<EventResponseDTO> getAllUpcomingEvents() {
        List<Event> upcomingEvents = eventRepository.findByStatus(EventStatus.UPCOMING);
        return upcomingEvents.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
        return convertToResponseDTO(event);
    }

    public RevenueDTO getEventRevenue(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        BigDecimal totalRevenue = eventRepository.calculateTotalRevenueForEvent(eventId);

        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setEventTitle(event.getTitle());
        revenueDTO.setTotalConfirmedRevenue(totalRevenue);

        return revenueDTO;
    }

    private EventResponseDTO convertToResponseDTO(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setEventId(event.getEventId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setStatus(event.getStatus());
        dto.setOrganizerName(event.getOrganizer().getName());
        dto.setVenueName(event.getVenue().getName());

        if (event.getTicketTypes() != null) {
            List<TicketTypeDTO> ticketTypeDTOs = event.getTicketTypes().stream()
                    .map(this::convertToTicketTypeDTO)
                    .collect(Collectors.toList());
            dto.setTicketTypes(ticketTypeDTOs);
        }

        return dto;
    }

    private TicketTypeDTO convertToTicketTypeDTO(com.example.demo.entity.TicketType ticketType) {
        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(ticketType.getTicketTypeId());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setQuantityAvailable(ticketType.getQuantityAvailable());
        return dto;
    }
}

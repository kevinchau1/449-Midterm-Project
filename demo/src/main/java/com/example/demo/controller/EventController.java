package com.example.demo.controller;

import com.example.demo.dto.CreateEventRequest;
import com.example.demo.dto.EventResponseDTO;
import com.example.demo.dto.RevenueDTO;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody CreateEventRequest request) {
        EventResponseDTO createdEvent = eventService.createEvent(request);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllUpcomingEvents() {
        List<EventResponseDTO> events = eventService.getAllUpcomingEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        EventResponseDTO event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/{id}/revenue")
    public ResponseEntity<RevenueDTO> getEventRevenue(@PathVariable Long id) {
        RevenueDTO revenue = eventService.getEventRevenue(id);
        return ResponseEntity.ok(revenue);
    }
}

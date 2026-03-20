package com.example.demo.controller;

import com.example.demo.dto.OrganizerDTO;
import com.example.demo.service.OrganizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<OrganizerDTO> createOrganizer(@RequestBody OrganizerDTO organizerDTO) {
        OrganizerDTO createdOrganizer = organizerService.createOrganizer(organizerDTO);
        return new ResponseEntity<>(createdOrganizer, HttpStatus.CREATED);
    }
}

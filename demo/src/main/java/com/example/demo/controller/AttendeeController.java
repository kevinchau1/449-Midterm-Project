package com.example.demo.controller;

import com.example.demo.dto.AttendeeBookingsDTO;
import com.example.demo.dto.AttendeeDTO;
import com.example.demo.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @PostMapping
    public ResponseEntity<AttendeeDTO> registerAttendee(@RequestBody AttendeeDTO attendeeDTO) {
        AttendeeDTO registeredAttendee = attendeeService.registerAttendee(attendeeDTO);
        return new ResponseEntity<>(registeredAttendee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/bookings")
    public ResponseEntity<AttendeeBookingsDTO> getAttendeeBookings(@PathVariable Long id) {
        AttendeeBookingsDTO bookings = attendeeService.getAttendeeBookings(id);
        return ResponseEntity.ok(bookings);
    }
}

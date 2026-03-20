package com.example.demo.dto;

import com.example.demo.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private EventStatus status;
    private Long organizerId;
    private Long venueId;
}

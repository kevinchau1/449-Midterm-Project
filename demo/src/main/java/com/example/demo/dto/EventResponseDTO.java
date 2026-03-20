package com.example.demo.dto;

import com.example.demo.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO {
    private Long eventId;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private EventStatus status;
    private String organizerName;
    private String venueName;
    private List<TicketTypeDTO> ticketTypes;
}

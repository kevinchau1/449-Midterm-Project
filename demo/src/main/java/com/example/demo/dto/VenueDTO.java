package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueDTO {
    private Long venueId;
    private String name;
    private String address;
    private String city;
    private Integer totalCapacity;
}

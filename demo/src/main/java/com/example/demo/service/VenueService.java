package com.example.demo.service;

import com.example.demo.dto.VenueDTO;
import com.example.demo.entity.Venue;
import com.example.demo.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    @Transactional
    public VenueDTO createVenue(VenueDTO venueDTO) {
        Venue venue = new Venue();
        venue.setName(venueDTO.getName());
        venue.setAddress(venueDTO.getAddress());
        venue.setCity(venueDTO.getCity());
        venue.setTotalCapacity(venueDTO.getTotalCapacity());

        Venue savedVenue = venueRepository.save(venue);

        return convertToDTO(savedVenue);
    }

    private VenueDTO convertToDTO(Venue venue) {
        VenueDTO dto = new VenueDTO();
        dto.setVenueId(venue.getVenueId());
        dto.setName(venue.getName());
        dto.setAddress(venue.getAddress());
        dto.setCity(venue.getCity());
        dto.setTotalCapacity(venue.getTotalCapacity());
        return dto;
    }
}

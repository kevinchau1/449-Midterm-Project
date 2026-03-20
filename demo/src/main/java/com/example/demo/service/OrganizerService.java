package com.example.demo.service;

import com.example.demo.dto.OrganizerDTO;
import com.example.demo.entity.Organizer;
import com.example.demo.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Transactional
    public OrganizerDTO createOrganizer(OrganizerDTO organizerDTO) {
        Organizer organizer = new Organizer();
        organizer.setName(organizerDTO.getName());
        organizer.setEmail(organizerDTO.getEmail());
        organizer.setPhone(organizerDTO.getPhone());

        Organizer savedOrganizer = organizerRepository.save(organizer);

        return convertToDTO(savedOrganizer);
    }

    private OrganizerDTO convertToDTO(Organizer organizer) {
        OrganizerDTO dto = new OrganizerDTO();
        dto.setOrganizerId(organizer.getOrganizerId());
        dto.setName(organizer.getName());
        dto.setEmail(organizer.getEmail());
        dto.setPhone(organizer.getPhone());
        return dto;
    }
}

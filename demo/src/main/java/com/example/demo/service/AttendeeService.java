package com.example.demo.service;

import com.example.demo.dto.AttendeeBookingsDTO;
import com.example.demo.dto.AttendeeDTO;
import com.example.demo.dto.BookingResponseDTO;
import com.example.demo.entity.Attendee;
import com.example.demo.entity.Booking;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AttendeeRepository;
import com.example.demo.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final BookingRepository bookingRepository;

    @Transactional
    public AttendeeDTO registerAttendee(AttendeeDTO attendeeDTO) {
        Attendee attendee = new Attendee();
        attendee.setName(attendeeDTO.getName());
        attendee.setEmail(attendeeDTO.getEmail());

        Attendee savedAttendee = attendeeRepository.save(attendee);

        return convertToDTO(savedAttendee);
    }

    public AttendeeBookingsDTO getAttendeeBookings(Long attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found with id: " + attendeeId));

        List<Booking> bookings = bookingRepository.findByAttendeeAttendeeId(attendeeId);

        List<BookingResponseDTO> bookingDTOs = bookings.stream()
                .map(this::convertToBookingResponseDTO)
                .collect(Collectors.toList());

        AttendeeBookingsDTO dto = new AttendeeBookingsDTO();
        dto.setAttendeeName(attendee.getName());
        dto.setBookings(bookingDTOs);

        return dto;
    }

    private AttendeeDTO convertToDTO(Attendee attendee) {
        AttendeeDTO dto = new AttendeeDTO();
        dto.setAttendeeId(attendee.getAttendeeId());
        dto.setName(attendee.getName());
        dto.setEmail(attendee.getEmail());
        return dto;
    }

    private BookingResponseDTO convertToBookingResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setBookingReference(booking.getBookingReference());
        dto.setBookingDate(booking.getBookingDate());
        dto.setPaymentStatus(booking.getPaymentStatus());
        dto.setAttendeeName(booking.getAttendee().getName());
        dto.setEventTitle(booking.getTicketType().getEvent().getTitle());
        dto.setTicketTypeName(booking.getTicketType().getName());
        dto.setPrice(booking.getTicketType().getPrice());
        return dto;
    }
}

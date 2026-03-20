package com.example.demo.service;

import com.example.demo.dto.BookingResponseDTO;
import com.example.demo.dto.CreateBookingRequest;
import com.example.demo.entity.Attendee;
import com.example.demo.entity.Booking;
import com.example.demo.entity.PaymentStatus;
import com.example.demo.entity.TicketType;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AttendeeRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketTypeRepository ticketTypeRepository;

    @Transactional
    public BookingResponseDTO createBooking(CreateBookingRequest request) {
        Attendee attendee = attendeeRepository.findById(request.getAttendeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Attendee not found with id: " + request.getAttendeeId()));

        TicketType ticketType = ticketTypeRepository.findById(request.getTicketTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket type not found with id: " + request.getTicketTypeId()));

        if (ticketType.getQuantityAvailable() <= 0) {
            throw new BusinessException("Sorry, this ticket type is sold out.");
        }

        boolean alreadyBooked = bookingRepository.existsByAttendeeAndTicketType(
                request.getAttendeeId(), request.getTicketTypeId());

        if (alreadyBooked) {
            throw new BusinessException("You have already booked this ticket type.");
        }

        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);

        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);

        String bookingReference = generateBookingReference(savedBooking.getBookingId());
        savedBooking.setBookingReference(bookingReference);
        savedBooking = bookingRepository.save(savedBooking);

        return convertToBookingResponseDTO(savedBooking);
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if (booking.getPaymentStatus() == PaymentStatus.CANCELLED) {
            throw new BusinessException("This booking is already cancelled.");
        }

        booking.setPaymentStatus(PaymentStatus.CANCELLED);

        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);
        ticketTypeRepository.save(ticketType);

        Booking updatedBooking = bookingRepository.save(booking);

        return convertToBookingResponseDTO(updatedBooking);
    }

    private String generateBookingReference(Long bookingId) {
        int year = Year.now().getValue();
        String paddedId = String.format("%05d", bookingId);
        return "TKT-" + year + "-" + paddedId;
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

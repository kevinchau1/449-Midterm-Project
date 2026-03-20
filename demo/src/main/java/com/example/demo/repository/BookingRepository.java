package com.example.demo.repository;

import com.example.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByAttendeeAttendeeId(Long attendeeId);

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.attendee.attendeeId = :attendeeId " +
           "AND b.ticketType.ticketTypeId = :ticketTypeId " +
           "AND b.paymentStatus <> 'CANCELLED'")
    boolean existsByAttendeeAndTicketType(@Param("attendeeId") Long attendeeId,
                                          @Param("ticketTypeId") Long ticketTypeId);
}

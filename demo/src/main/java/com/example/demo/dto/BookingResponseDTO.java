package com.example.demo.dto;

import com.example.demo.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Long bookingId;
    private String bookingReference;
    private LocalDateTime bookingDate;
    private PaymentStatus paymentStatus;
    private String attendeeName;
    private String eventTitle;
    private String ticketTypeName;
    private BigDecimal price;
}

package com.rcphotography.controller;

import com.rcphotography.entity.BookingRequest;
import com.rcphotography.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // ✅ New URL: /admin/bookings (better than /viewBookings)
    @GetMapping("/admin/bookings")
    public String adminBookings(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<BookingRequest> bookings = bookingService.getAllBookings(pageable);
        model.addAttribute("bookings", bookings);
        return "admin/viewBookings";
    }

    @GetMapping("/booking")
    public String bookingForm() {
        return "booking";
    }

    @PostMapping("/booking/submit")
    public String submitBooking(@RequestParam String serviceType,
                                @RequestParam String fullName,
                                @RequestParam String email,
                                @RequestParam String phone,
                                @RequestParam String datePreferred,
                                @RequestParam String timePreferred,
                                @RequestParam(required = false) String message) {

        BookingRequest request = BookingRequest.builder()
                .serviceType(serviceType)
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .datePreferred(LocalDate.parse(datePreferred))
                .timePreferred(LocalTime.parse(timePreferred))
                .message(message)
                .createdAt(LocalDate.now())
                .build();

        bookingService.saveBooking(request);
        return "redirect:/booking?success";
    }
}

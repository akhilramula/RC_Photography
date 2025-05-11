package com.rcphotography.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rcphotography.entity.BookingRequest;

public interface BookingService {
	BookingRequest saveBooking(BookingRequest request);
	 List<BookingRequest> getRecentBookings();
	    Page<BookingRequest> getAllBookings(Pageable pageable);
}

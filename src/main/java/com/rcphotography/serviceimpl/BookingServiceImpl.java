package com.rcphotography.serviceimpl;

import com.rcphotography.entity.BookingRequest;
import com.rcphotography.repository.BookingRepository;
import com.rcphotography.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingRequest saveBooking(BookingRequest request) {
        if (request.getCreatedAt() == null) {
            request.setCreatedAt(LocalDate.now());
        }
        return bookingRepository.save(request);
    }

    
    @Override
    public List<BookingRequest> getRecentBookings() {
        return bookingRepository.findTop5ByOrderByCreatedAtDesc();
    }

    @Override
    public Page<BookingRequest> getAllBookings(Pageable pageable) {
    	 return bookingRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
}

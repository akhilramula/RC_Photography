package com.rcphotography.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcphotography.entity.BookingRequest;

@Repository
public interface BookingRepository extends JpaRepository<BookingRequest, Long> {
	
	List<BookingRequest> findTop5ByOrderByCreatedAtDesc(); // For recent bookings
	Page<BookingRequest> findAllByOrderByCreatedAtDesc(Pageable pageable); // Pagination ordered by date
}


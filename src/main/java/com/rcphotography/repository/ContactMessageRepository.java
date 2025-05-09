package com.rcphotography.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcphotography.entity.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findTop5ByOrderByDateSentDesc();
}


package com.rcphotography.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.rcphotography.entity.ContactMessage;
import com.rcphotography.repository.ContactMessageRepository;

@Controller
public class ContactController {
    
    @Autowired
    private ContactMessageRepository messageRepo;

    @PostMapping("/contact")
    public String submitMessage(ContactMessage contactMessage) {
        contactMessage.setDateSent(LocalDateTime.now());
        messageRepo.save(contactMessage);
        return "redirect:/contact?success";
    }
}


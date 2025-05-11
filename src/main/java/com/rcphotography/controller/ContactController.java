package com.rcphotography.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @GetMapping("/admin/messages")
    public String viewMessages(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10); // Show 10 messages per page
        Page<ContactMessage> messages = messageRepo.findAll(pageable);
        model.addAttribute("messages", messages);
        return "admin/viewMessages";
    }
}


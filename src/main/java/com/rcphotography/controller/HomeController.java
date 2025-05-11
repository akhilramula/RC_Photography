package com.rcphotography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rcphotography.entity.BookingRequest;
import com.rcphotography.service.PhotoService;

@Controller
public class HomeController {

	@Autowired
	private PhotoService photoService;
	
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
    	model.addAttribute("allPhotos", photoService.getAllPhotos());
        return "portfolio";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    
    @ModelAttribute("bookingRequest")
    public BookingRequest bookingRequest() {
        return new BookingRequest();
    }

}

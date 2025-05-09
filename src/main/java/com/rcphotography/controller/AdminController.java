package com.rcphotography.controller;

import com.rcphotography.entity.Photo;
import com.rcphotography.repository.ContactMessageRepository;
import com.rcphotography.service.PhotoService;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PhotoService photoService;
	
	 @Autowired
	    private ContactMessageRepository messageRepo;

	@GetMapping("/dashboard")
	public String adminDashboard(Model model) {
		model.addAttribute("totalPhotos", photoService.getTotalPhotos());
		model.addAttribute("recentPhotos", photoService.getRecentPhotos());
		model.addAttribute("recentMessages", messageRepo.findTop5ByOrderByDateSentDesc());
		return "admin/AdminDashboard";
	}


	@GetMapping("/add")
	public String showAddPhotoForm(Model model) {
		return "admin/upload"; // upload.html view
	}

	@PostMapping("/upload")
	public String handleUpload(@RequestParam("files") MultipartFile[] files, @RequestParam("titles") String[] titles,
			@RequestParam("descriptions") String[] descriptions, Model model) {
		try {
			photoService.savePhotos(files, titles, descriptions);
			model.addAttribute("message", "Upload successful!");
		} catch (Exception e) {
			model.addAttribute("message", "Upload failed: " + e.getMessage());
		}
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/delete/{id}")
	public String deletePhoto(@PathVariable Long id) {
		photoService.deletePhotoById(id);
		return "redirect:/admin/manage-photos";
	}

	@GetMapping("/edit/{id}")
	public String showEditPhotoForm(@PathVariable Long id, Model model) {
		Photo photo = photoService.getPhotoById(id);
		model.addAttribute("photo", photo);
		return "admin/edit-photo"; // edit-photo.html
	}
	
	@PostMapping("/edit")
	public String updatePhoto(@ModelAttribute Photo formPhoto,
	                          @RequestParam("image") MultipartFile imageFile) {

	    Photo existingPhoto = photoService.getPhotoById(formPhoto.getId());

	    // Copy only fields that were edited
	    existingPhoto.setTitle(formPhoto.getTitle());
	    existingPhoto.setDescription(formPhoto.getDescription());

	    if (!imageFile.isEmpty()) {
	        String uploadDir = "D:/uploads";
	        String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
	        try {
	            File dest = new File(uploadDir + File.separator + filename);
	            dest.getParentFile().mkdirs();
	            imageFile.transferTo(dest);
	            existingPhoto.setImageUrl(filename);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // No need to set dateCreated; it's preserved
	    // lastUpdated will be set automatically by @PreUpdate
	    photoService.editPhotoById(existingPhoto);

	    return "redirect:/admin/manage-photos";
	}


	
	@GetMapping("/manage-photos")
	public String managePhotos(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        Model model) {

	    Page<Photo> photoPage = photoService.getPhotosPaginated(page, size);
	    model.addAttribute("photoPage", photoPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", photoPage.getTotalPages());
	    return "admin/manage-photos";
	}

	

}

package com.rcphotography.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.rcphotography.entity.Photo;

public interface PhotoService {

	public List<Photo> getAllPhotos();
	public Photo editPhotoById(Photo photo);
	public boolean deletePhotoById(long id);
	public Photo getPhotoById(long id);
	public List<String> savePhotos(MultipartFile[] files, String[] titles, String[] descriptions);
	 // Get total number of photos
    public long getTotalPhotos();

    // Get recent photos (last 5 uploads)
    public List<Photo> getRecentPhotos();
    
 // PhotoService.java
    Page<Photo> getPhotosPaginated(int page, int size);

}

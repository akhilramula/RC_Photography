package com.rcphotography.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rcphotography.entity.Photo;
import com.rcphotography.repository.PhotoRepository;
import com.rcphotography.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoRepository repo;

	@Override
	public List<Photo> getAllPhotos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Photo editPhotoById(Photo photo) {
		if (repo.existsById(photo.getId())) {
			return repo.save(photo);
		} else {
			return null;
		}

	}

	@Override
	public boolean deletePhotoById(long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Photo getPhotoById(long id) {
		if (repo.existsById(id)) {
			return repo.findById(id).get();
		} else {
			return null;
		}

	}

	@Override
	public List<String> savePhotos(MultipartFile[] files, String[] titles, String[] descriptions) {
		List<String> imageUrls = new ArrayList<>();
		String uploadDir = "D:/uploads";

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			try {
				String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
				File dest = new File(uploadDir + File.separator + filename);

				// Ensure the upload directory exists
				dest.getParentFile().mkdirs();

				file.transferTo(dest); // Save the file

				Photo photo = new Photo();
				photo.setTitle(titles[i]);
				photo.setDescription(descriptions[i]);
				photo.setImageUrl(filename);

				repo.save(photo); // Save photo metadata

				imageUrls.add(photo.getImageUrl()); // Add URL to return list

			} catch (IOException e) {
				e.printStackTrace();
				imageUrls.add("error_" + file.getOriginalFilename());
			}
		}

		return imageUrls;
	}

	@Override
	public long getTotalPhotos() {
		// TODO Auto-generated method stub
		return repo.count();
	}

	@Override
	public List<Photo> getRecentPhotos() {
		// TODO Auto-generated method stub
		return repo.findTop5ByOrderByDateCreatedDesc();
	}
	
	@Override
	public Page<Photo> getPhotosPaginated(int page, int size) {
	    return repo.findAll(PageRequest.of(page, size));
	}

}

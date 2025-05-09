package com.rcphotography.repository;

import com.rcphotography.entity.Photo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	   // Find top 5 most recent photos by date created
    List<Photo> findTop5ByOrderByDateCreatedDesc();
    
    //For pagination
    Page<Photo> findAll(Pageable pageable);
}

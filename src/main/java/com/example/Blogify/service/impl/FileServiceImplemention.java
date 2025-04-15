package com.example.Blogify.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Blogify.entities.Post;
import com.example.Blogify.exception.ResourceNotFoundException;
import com.example.Blogify.repositories.PostRepository;
import com.example.Blogify.service.FileService;

@Service
public class FileServiceImplemention implements FileService {
	
	@Autowired
	PostRepository post_repo;
	
	@Value("${project.image}")
	String path;

	@Override
	public String uploadsImage(Integer postId, MultipartFile file) throws IOException {
		// a. Fetch the post
	    Post post = post_repo.findById(postId)
	        .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

	    // b. Generate a unique file name (avoid conflicts)
	    String originalFilename = file.getOriginalFilename();
	    String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

	    
	    File dir = new File(path);
	    if (!dir.exists()) dir.mkdirs();

	    // d. Save the image in file system
	    Path filePath = Paths.get(path + uniqueFilename);
	    Files.copy(file.getInputStream(), filePath);

	    // e. Update DB record for image name
	    post.setImageName(uniqueFilename);
	    post_repo.save(post);

	    return uniqueFilename;
	}

	@Override
	public InputStream getResponse(String path, String filename) throws FileNotFoundException {
		 // Build full path to file
	    String fullPath = path + File.separator + filename;

	    // Open stream
	    return new FileInputStream(fullPath);
	}

}

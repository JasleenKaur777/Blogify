package com.example.Blogify;

import com.example.Blogify.entities.Category;

import com.example.Blogify.entities.Post;
import com.example.Blogify.entities.UserClass;
import com.example.Blogify.payloads.PostDTO;
import com.example.Blogify.repositories.CategoryRepository;
import com.example.Blogify.repositories.PostRepository;
import com.example.Blogify.repositories.UserRepository;
import com.example.Blogify.service.impl.PostImplemention;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private CategoryRepository categoryRepo;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostImplemention service;
    
   

    @Test
    void insertPost() {
        // Setup DTO
        PostDTO dto = new PostDTO();
        dto.setPostTitle("Sample Post");
        dto.setContent("Sample Content");
        dto.setImageName("sample.png");

        // Setup dummy entities
        UserClass user = new UserClass();
        user.setId(1);

        Category category = new Category();
        category.setCategory_id(1);

        Post post = new Post();
        post.setPostTitle("Sample Post");
        post.setContent("Sample Content");
        post.setImageName("sample.png");
        post.setUser(user);
        post.setCategory(category);

        // Mock repository behavior
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        when(postRepo.save(any(Post.class))).thenReturn(post);
        when(modelMapper.map(any(PostDTO.class), eq(Post.class))).thenReturn(post); // for dto -> entity
        when(modelMapper.map(any(Post.class), eq(PostDTO.class))).thenReturn(dto);  // for entity -> dto


        // Call service
        PostDTO result = service.insertPost(dto, 1, 1);

        // Assert result
        assertNotNull(result);
        assertEquals("Sample Post", result.getPostTitle());
        assertEquals("sample.png", result.getImageName());
    }
}

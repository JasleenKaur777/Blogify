package com.example.Blogify.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
String uploadsImage(Integer postId,MultipartFile file) throws IOException;
InputStream getResponse(String path,String filename) throws FileNotFoundException;
}

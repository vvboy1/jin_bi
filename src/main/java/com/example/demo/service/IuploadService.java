package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface IuploadService{

    String upload(MultipartFile file, String path);

    String uploadName(String no, MultipartFile file, String path);
}

package com.example.school_management_system.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
@Service
public class FileService {
private final Path uploudpath= Paths.get("uplouds");
List<String> list=List.of(".jpg",".jpeg",".png",".svg",".pdf");
public FileService()throws IOException{
    Files.createDirectories(uploudpath);
}
public void saveFile(MultipartFile file) throws IOException {
    if(file.isEmpty()||list.stream().noneMatch(type ->
        file.getOriginalFilename().toLowerCase().endsWith(type)
    )) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
       throw  new RuntimeException("File is Not Allowed");
    }
    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
    Path filepath = uploudpath.resolve(fileName);
    Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
}
}

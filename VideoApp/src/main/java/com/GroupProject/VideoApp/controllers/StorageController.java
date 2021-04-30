package com.GroupProject.VideoApp.controllers;

import com.GroupProject.VideoApp.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;

@RestController
@RequestMapping("/file")
public class StorageController {
    
    @Autowired
    private StorageService service;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String filename) {
        byte[] data = service.downloadFile(filename);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile (@PathVariable String filename) {
        return new ResponseEntity<>(service.deleteFile(filename), HttpStatus.OK);
    }
}

package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/download")
public class DownloadController {

    private final StorageService storageService;

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        System.err.println(SecurityContextHolder.getContext().getAuthentication().getName());
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}

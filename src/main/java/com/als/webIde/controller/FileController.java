package com.als.webIde.controller;

import com.als.webIde.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/{userId}/{filename}")
    public ResponseEntity<String> createOrUpdateFile(@PathVariable String userId,
                                                     @PathVariable String filename,
                                                     @RequestBody String content) {
        try {
            fileService.saveFile(userId, filename, content);
            return ResponseEntity.ok("파일 저장 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("파일을 저장하지 못했습니다. : " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/{filename}")
    public ResponseEntity<?> readFile(@PathVariable String userId,
                                      @PathVariable String filename) {
        try {
            String fileContent = fileService.readFile(userId, filename);
            return ResponseEntity.ok(fileContent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("파일을 불러오지 못했습니다: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String userId,
                                             @PathVariable String filename) {
        try {
            fileService.deleteFile(userId, filename);
            return ResponseEntity.ok("파일을 성공적으로 삭제했습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("파일을 삭제하지 못했습니다: " + e.getMessage());
        }
    }
}
package com.zagvladimir.controller;

import com.zagvladimir.service.FileService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/get-doc")
    public ResponseEntity<?> getDoc(@RequestParam("id") String id){
        //TODO для формирования BadRequest controller advice
        var document = fileService.getDocument(id);
        if(document == null){
            return ResponseEntity.badRequest().build();
        }
        var binaryContent = document.getBinaryContent();
        var fileSystemResource = fileService.getFileSystemResource(binaryContent);

        if(fileSystemResource == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getMimeType()))
                .header("Content-disposition","attachment; filename="+ document.getDocName())
                .body(fileSystemResource);
    }

    @GetMapping("/get-photo")
    public ResponseEntity<?> getphoto(@RequestParam("id") String id){
        //TODO для формирования BadRequest controller advice
        var photo = fileService.getPhoto(id);
        if(photo == null){
            return ResponseEntity.badRequest().build();
        }
        var binaryContent = photo.getBinaryContent();
        var fileSystemResource = fileService.getFileSystemResource(binaryContent);

        if(fileSystemResource == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header("Content-disposition","attachment;")
                .body(fileSystemResource);
    }
}

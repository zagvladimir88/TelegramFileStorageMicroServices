package com.zagvladimir.service;

import com.zagvladimir.entity.AppDocument;
import com.zagvladimir.entity.AppPhoto;
import com.zagvladimir.entity.BinaryContent;
import org.springframework.core.io.FileSystemResource;

public interface FileService {
    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
    FileSystemResource getFileSystemResource(BinaryContent binaryContent);
}

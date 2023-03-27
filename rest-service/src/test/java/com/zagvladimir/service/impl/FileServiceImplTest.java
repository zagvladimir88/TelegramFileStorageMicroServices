package com.zagvladimir.service.impl;


import com.zagvladimir.dao.AppDocumentDAO;
import com.zagvladimir.dao.AppPhotoDAO;
import com.zagvladimir.entity.AppDocument;
import com.zagvladimir.entity.AppPhoto;
import com.zagvladimir.entity.BinaryContent;
import com.zagvladimir.utils.CryptoTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceImplTest {
    @Mock
    private AppDocumentDAO appDocumentDAO;
    @Mock
    private AppPhotoDAO appPhotoDAO;
    @Mock
    private CryptoTool cryptoTool;

    @InjectMocks
    private FileServiceImpl fileService;


    @Test
    void testGetDocument_ReturnsExpectedDocument() {
        long documentId = 1;
        AppDocument expectAppDocument = new AppDocument();
        expectAppDocument.setId(1L);
        expectAppDocument.setTelegramFileId("Test");

        when(cryptoTool.idOf(any(String.class))).thenReturn(documentId);
        when(appDocumentDAO.findById(any())).thenReturn(Optional.of(expectAppDocument));
        AppDocument actualDocument = fileService.getDocument("test");

        assertEquals(expectAppDocument,actualDocument);
        verify(cryptoTool,times(1)).idOf(any());
        verify(appDocumentDAO,times(1)).findById(any());
    }

    @Test
    void testFailGetDocumentWithInvalidHashId() {
        String wrongHashId = "err";

        doReturn(null).when(cryptoTool).idOf(wrongHashId);
        AppDocument actualDocument = fileService.getDocument(wrongHashId);

        assertNull(actualDocument);
        verify(cryptoTool,times(1)).idOf(any());
        verify(appDocumentDAO,times(0)).findById(any());
    }

    @Test
    void testGetPhoto_ReturnsExpectedPhoto() {
        long documentId = 1;
        AppPhoto expectAppPhoto = new AppPhoto();
        expectAppPhoto.setId(1L);
        expectAppPhoto.setTelegramFileId("test");

        when(cryptoTool.idOf(any(String.class))).thenReturn(documentId);
        when(appPhotoDAO.findById(any())).thenReturn(Optional.of(expectAppPhoto));
        AppPhoto appPhoto = fileService.getPhoto("test");

        assertEquals(expectAppPhoto,appPhoto);
        verify(cryptoTool,times(1)).idOf(any());
        verify(appPhotoDAO,times(1)).findById(any());
    }

    @Test
    void testFailGetPhotoWithInvalidHashId() {
        String wrongHashId = "";

        doReturn(null).when(cryptoTool).idOf(wrongHashId);
        AppPhoto actualPhoto = fileService.getPhoto(wrongHashId);

        assertNull(actualPhoto);
        verify(cryptoTool,times(1)).idOf(any());
        verify(appPhotoDAO,times(0)).findById(any());
    }

    @Test
    void testGetFileSystemResource() throws IOException {
        BinaryContent binaryContent = mock(BinaryContent.class);
        byte[] fileContent = new byte[]{1, 2, 3};
        when(binaryContent.getFileAsArrayOfBytes()).thenReturn(fileContent);

        FileSystemResource actualResult = fileService.getFileSystemResource(binaryContent);

        assertNotNull(actualResult);
        assertTrue(actualResult.exists());
        assertArrayEquals(fileContent, Files.readAllBytes(actualResult.getFile().toPath()));
    }
}
package com.lungcare.backend.Service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    @Value("${lungcare.scan.storage.path:c:/lungcare/uploads}")
    private String basepath;
    public Resource loadimages(String imagepath)
    {
        try
        {
            Path filePath = Paths.get(basepath).resolve(imagepath).normalize();
            UrlResource resource = new UrlResource(filePath.toUri());

            if (!resource.exists())
            {
                throw new RuntimeException("file not found");
            }
            return (Resource) resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid image path",e);
        }

    }
}

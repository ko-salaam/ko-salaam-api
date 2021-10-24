package com.kosalaam.api.common;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class StorageUtils {

    private static String uploadPath;

    @Value("${upload.path}")
    private void setUploadPath(String path) {
        uploadPath = path;
    }

//    @PostConstruct
    public static void init(Path root) {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    public static String[] save(List<MultipartFile> imageFiles, UUID id) throws Exception {

        Path root = Paths.get(uploadPath + "/" + id);
        if (!Files.exists(root)) {
            init(root);
        }

        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile image : imageFiles) {
            Path realPath = root.resolve(UUID.randomUUID().toString());
            Files.copy(image.getInputStream(), realPath);
            imagePaths.add("http://localhost:8080/" + realPath.toString());
        }
        return imagePaths.toArray(new String[imagePaths.size()]);

    }

    public static Resource load(String filename) throws Exception {

        Path file = Paths.get(uploadPath)
                .resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Failed to load images");
        }
    }
}

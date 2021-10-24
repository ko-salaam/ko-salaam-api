package com.kosalaam.api.common;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.amazonaws.services.s3.AmazonS3;
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

//    public static void getBucket() {
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("ap-northeast-2").build();
////        List<Bucket> buckets = s3.listBuckets();
////        System.out.println("Your {S3} buckets are:");
////        for (Bucket b : buckets) {
////            System.out.println("* " + b.getName());
////        }
//
//        ListObjectsV2Result result = s3.listObjectsV2("kosalaam-storage");
//        List<S3ObjectSummary> objects = result.getObjectSummaries();
//        for (S3ObjectSummary os : objects) {
//            System.out.println("* " + os.getKey());
//            System.out.println(os.getStorageClass());
//        }
//    }

    private static String uploadPath;

    @Value("${upload.path}")
    private void setUploadPath(String path) {
        uploadPath = path;
    }

    @PostConstruct
    public static void init() {
        try {
            Files.createDirectories(Paths.get(uploadPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    public static String[] save(List<MultipartFile> imageFiles) throws Exception {

        Path root = Paths.get(uploadPath);
        if (!Files.exists(root)) {
            init();
        }

        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile image : imageFiles) {
            Path realPath = root.resolve(UUID.randomUUID().toString());
            Files.copy(image.getInputStream(), realPath);
            imagePaths.add(realPath.toUri().toString());
        }
        return imagePaths.toArray(new String[imagePaths.size()]);

    }

}

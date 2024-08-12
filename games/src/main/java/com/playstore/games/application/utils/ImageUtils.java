package com.playstore.games.application.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.playstore.games.domain.GameImage;

@Component
public class ImageUtils {

    @Autowired
    private Cloudinary cloudinary;

    public GameImage fileUpload(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        System.out.println(file.getOriginalFilename());

        validateFile(file);

        String url = uploadToCloudinary(file);

        return GameImage.builder()
                .image_url(url)
                .build();

    }

    private void validateFile(MultipartFile file) {

        if (file.getSize() > 5000000) {
            throw new IllegalArgumentException("The image exceeds 5 MB");
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
            throw new IllegalArgumentException("The allowed extensions are ‘.jpg’, ‘.jpe’, ‘.png’");
        }
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }
}

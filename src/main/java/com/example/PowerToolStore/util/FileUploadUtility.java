package com.example.PowerToolStore.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.PowerToolStore.config.CloudinaryConfig;
import com.example.PowerToolStore.exception.FileUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
public class FileUploadUtility {

    private final CloudinaryConfig config;

    public FileUploadUtility( CloudinaryConfig config)
    {
        this.config = config;
        this.config.setUrl(
                this.config.getUrl()
                        .replace("<API_SECRET>", this.config.getApiSecret())
                        .replace("<API_KEY>", this.config.getApiKey())
                        .replace("<CLOUD_NAME>", this.config.getCloudName())
        );
    }

    public Map uploadFile(MultipartFile file) throws IOException {

        try {
            Cloudinary cloudinary = new Cloudinary(this.config.getUrl());

            Map params = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", true,
                    "overwrite", true
            );

            // Upload the image
            return cloudinary.uploader().upload(file.getBytes(), params);
        }
        catch (Exception e)
        {
            throw new FileUploadException("Upload failed for file: " + file.getOriginalFilename(), e);
        }
    }
}

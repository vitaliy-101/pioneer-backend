package com.example.pioneerbackend.util;

import com.example.pioneerbackend.dto.docs.DocImageInfo;
import com.example.pioneerbackend.dto.image.ImageData;
import com.example.pioneerbackend.exceptions.MultipartFileBytesIsNullException;
import lombok.experimental.UtilityClass;
import org.apache.tika.Tika;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class FileUtils {

    public static List<byte[]> getByteFromMultipartList(List<MultipartFile> multipartFiles) {
        var files = multipartFiles.stream()
                .map(FileUtils::getByteFromMultipart)
                .toList();
        if (files.isEmpty()) {
            throw new IllegalArgumentException("No image data provided");
        }
        return files;
    }

    public static byte[] getByteFromMultipart(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (Exception e) {
            throw new MultipartFileBytesIsNullException();
        }
    }

    public static List<DocImageInfo> createDocInfoFromMultipartList(List<MultipartFile> multipartFiles) {
        var files = multipartFiles.stream()
                .map(FileUtils::createDocInfoFromMultipart)
                .toList();
        if (files.isEmpty()) {
            throw new IllegalArgumentException("No image data provided");
        }
        return files;
    }


    public static DocImageInfo createDocInfoFromMultipart(MultipartFile multipartFile) {
        try {
            var fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            return new DocImageInfo(fileName, multipartFile.getBytes());
        } catch (Exception e) {
            throw new MultipartFileBytesIsNullException();
        }
    }


    public static String convertToUrl(String url, String id) {
        return url + id;
    }

    public static ImageData createImageData(byte[] data) {
        var resource = new InputStreamResource(new ByteArrayInputStream(data));
        return new ImageData(resource, new Tika().detect(data));
    }

    public static ResponseEntity<Resource> createLoadResponseEntity(ImageData imageData) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageData.getContentType()))
                .body(imageData.getResource());
    }
}

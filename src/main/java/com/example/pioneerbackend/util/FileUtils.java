package com.example.pioneerbackend.util;

import com.example.pioneerbackend.exceptions.MultipartFileBytesIsNullException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.List;

@UtilityClass
public class FileUtils {

    public static List<byte[]> getByteFromMultipartList(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                .map(FileUtils::getByteFromMultipart)
                .toList();
    }

    public static byte[] getByteFromMultipart(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (Exception e) {
            throw new MultipartFileBytesIsNullException();
        }
    }

    public static String convertFileToUrl(String url, Long id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(url)
                .path(id.toString())
                .toUriString();
    }
}

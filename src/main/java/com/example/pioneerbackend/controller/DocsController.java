package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.docs.DocType;
import com.example.pioneerbackend.dto.docs.DocsCreateRequest;
import com.example.pioneerbackend.dto.docs.DocsResponse;
import com.example.pioneerbackend.service.docs.DocsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("api/v1/docs")
@RequiredArgsConstructor
public class DocsController {
    private final DocsService service;

    @PostMapping
    public DocsResponse create(@RequestPart(name = "request") DocsCreateRequest request,
                               @RequestPart(name = "files") List<MultipartFile> files) {
        return new DocsResponse(service.createDocs(request, files));
    }

    @GetMapping
    public DocsResponse findAll(DocType docType) {
        return new DocsResponse(service.findAll(docType));
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> loadFile(@PathVariable("id") Long id) throws UnsupportedEncodingException {
        var downloadResponse = service.findImageById(id);
        var imageData = downloadResponse.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imageData.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "fileUpload; filename=\"" + URLEncoder.encode(downloadResponse.getName(), "UTF-8")
                        + "\"").body(imageData.getResource());
    }

    @DeleteMapping("/file/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}

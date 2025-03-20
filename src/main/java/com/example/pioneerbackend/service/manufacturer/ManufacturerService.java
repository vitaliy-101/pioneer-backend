package com.example.pioneerbackend.service.manufacturer;

import com.example.pioneerbackend.dto.image.ImageData;
import com.example.pioneerbackend.dto.manufacturer.ManufacturerInfo;
import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import com.example.pioneerbackend.entity.product.ProductImage;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.mapper.ManufacturerMapper;
import com.example.pioneerbackend.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.example.pioneerbackend.util.FileUtils.createImageData;
import static com.example.pioneerbackend.util.FileUtils.getByteFromMultipart;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository repository;
    private final ManufacturerMapper mapper;

    public void create(String name, MultipartFile fileData) {
        repository.save(mapper.toEntity(name, getByteFromMultipart(fileData)));
    }

    public List<ManufacturerInfo> findAll() {
        return mapper.toInfoListFromEntityList(repository.findAll());
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ImageData findImageById(String id) {
        var fileData = repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Manufacturer.class, id))
                .getImage();
       return createImageData(fileData);
    }


}

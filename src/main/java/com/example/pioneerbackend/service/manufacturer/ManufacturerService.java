package com.example.pioneerbackend.service.manufacturer;

import com.example.pioneerbackend.dto.image.ImageData;
import com.example.pioneerbackend.dto.manufacturer.ManufacturerCreateRequest;
import com.example.pioneerbackend.dto.manufacturer.ManufacturerInfo;
import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import com.example.pioneerbackend.entity.product.ProductImage;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.mapper.ManufacturerMapper;
import com.example.pioneerbackend.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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

    public void create(ManufacturerCreateRequest request, MultipartFile fileData) {
        repository.save(mapper.toEntity(request, getByteFromMultipart(fileData)));
    }

    public void existsById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundByIdException(Manufacturer.class, id);
        }
    }

    @Modifying
    @Transactional
    public void update(ManufacturerCreateRequest request,
                       MultipartFile fileData,
                       Long id) {
        existsById(id);
        var updatedManufacturer = mapper.toEntity(request, getByteFromMultipart(fileData));
        updatedManufacturer.setId(id);
        repository.save(updatedManufacturer);
    }

    public List<ManufacturerInfo> findAll() {
        return mapper.toInfoListFromEntityList(repository.findAll());
    }

    @Transactional
    @Modifying
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Manufacturer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Manufacturer.class, id));
    }

    public Manufacturer findByIdNull(Long id) {
        if (id != null) {
            return findById(id);
        }
        return null;
    }

    public ImageData findImageById(Long id) {
        var fileData = findById(id).getImage();
        return createImageData(fileData);
    }


}

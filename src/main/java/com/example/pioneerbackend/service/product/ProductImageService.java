package com.example.pioneerbackend.service.product;

import com.example.pioneerbackend.dto.product.ProductImageData;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.product.ProductImage;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.mapper.ProductImageMapper;
import com.example.pioneerbackend.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.pioneerbackend.util.FileUtils.getByteFromMultipartList;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository repository;
    private final ProductImageMapper mapper;

    public Set<ProductImage> saveImages(List<MultipartFile> filesData, Product product) {
        var imageBytes = getByteFromMultipartList(filesData);
        if (imageBytes.isEmpty()) {
            throw new IllegalArgumentException("No image data provided");
        }

        return IntStream.range(0, imageBytes.size())
                .mapToObj(i -> {
                    var productImage = mapper.convertFromByteToEntity(imageBytes.get(i), product);
                    productImage.setMain(i == 0);
                    return repository.save(productImage);
                })
                .collect(Collectors.toSet());
    }

    public ProductImageData findImageById(Long id) {
        var fileData = repository.findById(id).orElseThrow(() -> new NotFoundByIdException(ProductImage.class, id))
                .getImage();
        var resource = new InputStreamResource(new ByteArrayInputStream(fileData));
        return new ProductImageData(resource, new Tika().detect(fileData));
    }
}

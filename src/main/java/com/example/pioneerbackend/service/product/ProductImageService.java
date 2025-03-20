package com.example.pioneerbackend.service.product;

import com.example.pioneerbackend.dto.image.ImageData;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.product.ProductImage;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.mapper.ProductImageMapper;
import com.example.pioneerbackend.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.pioneerbackend.util.FileUtils.createImageData;
import static com.example.pioneerbackend.util.FileUtils.getByteFromMultipartList;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository repository;
    private final ProductImageMapper mapper;

    public List<ProductImage> saveImages(List<MultipartFile> filesData, Product product) {
        var imageBytes = getByteFromMultipartList(filesData);

        return IntStream.range(0, imageBytes.size())
                .mapToObj(i -> {
                    var productImage = mapper.convertFromByteToEntity(imageBytes.get(i), product);
                    productImage.setMain(i == 0);
                    return repository.save(productImage);
                })
                .toList();
    }

    public ImageData findImageById(Long id) {
        var fileData = repository.findById(id).orElseThrow(() -> new NotFoundByIdException(ProductImage.class, id))
                .getImage();
        return createImageData(fileData);
    }
}

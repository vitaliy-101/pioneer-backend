package com.example.pioneerbackend.service.product;

import com.example.pioneerbackend.dto.product.filter.ProductFilter;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.repository.ProductRepository;
import com.example.pioneerbackend.specification.ProductFilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductImageService imageService;
    private final ProductFilterSpecification specification;

    public Product createProduct(Product product, List<MultipartFile> images) {
        var savedProduct = repository.save(product);
        if (images != null && !images.isEmpty()) {
            product.setImages(imageService.saveImages(images, savedProduct));
        }
        return savedProduct;
    }

    public Product findProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Product.class, id));
    }


    public Page<Product> findAll(ProductFilter request, Pageable pageable) {
        return repository.findAll(specification.filterByCriteria(request), pageable);
    }


}

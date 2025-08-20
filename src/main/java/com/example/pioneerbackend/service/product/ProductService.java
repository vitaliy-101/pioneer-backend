package com.example.pioneerbackend.service.product;

import com.example.pioneerbackend.dto.filter.ProductFilter;
import com.example.pioneerbackend.dto.product.ProductCreationRequest;
import com.example.pioneerbackend.dto.product.ProductSaleInfo;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.mapper.custom.CustomProductMapper;
import com.example.pioneerbackend.repository.ProductRepository;
import com.example.pioneerbackend.service.manufacturer.ManufacturerService;
import com.example.pioneerbackend.specification.ProductFilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductImageService imageService;
    private final ProductFilterSpecification specification;
    private final CustomProductMapper mapper;
    private final ManufacturerService manufacturerService;

    public Product createProduct(ProductCreationRequest request, List<MultipartFile> images) {
        var manufacturer = manufacturerService.findByIdNull(request.getManufacturerId());
        var product = mapper.fromCreationRequestToEntity(request, manufacturer);
        var savedProduct = repository.save(product);
        if (images != null && !images.isEmpty()) {
            product.setImages(imageService.saveImages(images, savedProduct));
        }
        return savedProduct;
    }

    private void existsById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundByIdException(Product.class, id);
        }
    }

    @Transactional
    @Modifying
    public Product update(Long id, ProductCreationRequest request) {
        existsById(id);
        var manufacturer = manufacturerService.findByIdNull(request.getManufacturerId());
        var product = mapper.fromCreationRequestToEntity(request, manufacturer);
        product.setId(id);
        return repository.save(product);
    }

    @Transactional
    @Modifying
    public void updateSales(List<ProductSaleInfo> productSaleInfos) {
        productSaleInfos.forEach(productSaleInfo ->
                repository.updateSale(productSaleInfo.getCount(), productSaleInfo.getId()));
    }

    public Product findProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Product.class, id));
    }


    public Page<Product> findAll(ProductFilter request, Pageable pageable) {
        return repository.findAll(specification.filterByCriteria(request), pageable);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }



}

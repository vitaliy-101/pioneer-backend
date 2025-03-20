package com.example.pioneerbackend.service.docs;

import com.example.pioneerbackend.dto.docs.*;
import com.example.pioneerbackend.dto.image.ImageData;
import com.example.pioneerbackend.entity.docs.Docs;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.product.ProductImage;
import com.example.pioneerbackend.exceptions.NotFoundByIdException;
import com.example.pioneerbackend.mapper.DocsMapper;
import com.example.pioneerbackend.repository.DocsRepository;
import com.example.pioneerbackend.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.pioneerbackend.util.FileUtils.createDocInfoFromMultipartList;
import static com.example.pioneerbackend.util.FileUtils.createImageData;

@Service
@RequiredArgsConstructor
public class DocsService {
    private final DocsRepository repository;
    private final DocsMapper mapper;
    private final ProductService productService;

    public List<DocInfo> createDocs(DocsCreateRequest request,
                                    List<MultipartFile> files) {
        var docsImageData = createDocInfoFromMultipartList(files);
        var product = findProductByType(request.getProductId());
        var type = request.getType();
        var docs = docsImageData.stream()
                .map(docImageInfo -> {
                    var doc = mapper.toEntity(product, type, docImageInfo);
                    return repository.save(doc);
                })
                .toList();
        return mapper.toDocInfoList(docs);
    }

    public DocDownloadResponse findImageById(Long id) {
        var doc = repository.findById(id).orElseThrow(() -> new NotFoundByIdException(Docs.class, id));
        return new DocDownloadResponse(doc.getName(), createImageData(doc.getData()));
    }

    public List<DocInfo> findAll(DocType type) {
        return mapper.toDocInfoList(repository.findByType(type));
    }

    public List<DocProductInfo> findDocsByProductId(Long productId) {
        return mapper.toDocProductInfoList(repository.findByProductId(productId));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Product findProductByType(Long productId) {
        if (productId == null) {
            return null;
        }
        return productService.findProductById(productId);
    }


}

package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.dto.docs.*;
import com.example.pioneerbackend.entity.docs.Docs;
import com.example.pioneerbackend.entity.product.Product;

import static com.example.pioneerbackend.util.FileUtils.convertFileToUrl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

import static com.example.pioneerbackend.constant.Urls.DOCS_FILE_URL;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "docImageInfo.name")
    @Mapping(target = "data", source = "docImageInfo.data")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "type", source = "type")
    Docs toEntity(Product product, DocType type, DocImageInfo docImageInfo);

    @Mapping(target = "docUrl", source = "id", qualifiedByName = "convertImageToUrl")
    DocInfo toDocInfo(Docs doc);

    @Mapping(target = "fileUrl", source = "id", qualifiedByName = "convertImageToUrl")
    DocProductInfo toDocProductInfo(Docs doc);

    List<DocInfo> toDocInfoList(List<Docs> docs);

    List<DocProductInfo> toDocProductInfoList(List<Docs> docs);

    @Named("convertImageToUrl")
    default String convertImageToUrl(Long docId) {
        return convertFileToUrl(DOCS_FILE_URL, docId.toString());
    }
}

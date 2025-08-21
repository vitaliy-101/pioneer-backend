package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.dto.manufacturer.ManufacturerCreateRequest;
import com.example.pioneerbackend.dto.manufacturer.ManufacturerInfo;
import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

import static com.example.pioneerbackend.constant.Urls.MANUFACTURER_IMAGE_URL;
import static com.example.pioneerbackend.util.FileUtils.convertToUrl;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManufacturerMapper {
    @Mapping(target = "image", source = "image")
    Manufacturer toEntity(ManufacturerCreateRequest request, byte[] image);

    @Mapping(target = "imageUrl", source = "id", qualifiedByName = "convertImageToUrl")
    ManufacturerInfo toManufacturerInfo(Manufacturer manufacturer);

    List<ManufacturerInfo> toInfoListFromEntityList(List<Manufacturer> manufacturers);

    @Named("convertImageToUrl")
    default String convertImageToUrl(Long id) {
        return convertToUrl(MANUFACTURER_IMAGE_URL, id.toString());
    }
}

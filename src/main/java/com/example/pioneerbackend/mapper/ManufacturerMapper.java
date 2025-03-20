package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.dto.manufacturer.ManufacturerInfo;
import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

import static com.example.pioneerbackend.constant.Urls.MANUFACTURER_IMAGE_URL;
import static com.example.pioneerbackend.util.FileUtils.convertFileToUrl;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManufacturerMapper {
    @Mapping(target = "image", source = "image")
    @Mapping(target = "name", source = "name")
    Manufacturer toEntity(String name, byte[] image);

    @Mapping(target = "imageUrl", source = "name", qualifiedByName = "convertImageToUrl")
    ManufacturerInfo toManufacturerInfo(Manufacturer manufacturer);

    List<ManufacturerInfo> toInfoListFromEntityList(List<Manufacturer> manufacturers);

    @Named("convertImageToUrl")
    default String convertImageToUrl(String name) {
        return convertFileToUrl(MANUFACTURER_IMAGE_URL, name);
    }
}

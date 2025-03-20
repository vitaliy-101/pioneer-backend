package com.example.pioneerbackend.dto.manufacturer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ManufacturerGetResponse {
    private List<ManufacturerInfo> manufacturerInfos;
}

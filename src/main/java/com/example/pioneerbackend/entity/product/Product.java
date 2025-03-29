package com.example.pioneerbackend.entity.product;

import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.docs.Docs;
import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operating_temperature")
    private String operatingTemperature;

    @Column(name = "degree_protection")
    private String degreeProtection;

    @Column(name = "warning_alarm")
    private String warningAlarm;

    @Column(name = "display")
    private String display;

    @Column(name = "calibration")
    private String calibration;

    @Column(name = "response_time")
    private String responseTime;

    @Column(name = "explosion_protection_rating")
    private String explosionProtectionRating;

    @Column(name = "power_supply")
    private String powerSupply;

    @Column(name = "operating_humidity")
    private String operatingHumidity;

    @Column(name = "sensor_type")
    private String sensorType;

    @Column(name = "gas_sampling")
    private String gasSampling;

    @Column(name = "enclosure")
    private String enclosure;

    @Column(name = "operating_time")
    private String operatingTime;

    @Column(name = "charging_time")
    private String chargingTime;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "channel_type")
    private String channelType;

    @Column(name = "type")
    private String type;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "additional", columnDefinition = "TEXT[]")
    private List<String> additional;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "measuring_ranges", columnDefinition = "TEXT[]")
    private List<String> measuringRanges;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "sales")
    private Integer sales = 0;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Basket> baskets;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Docs> docs;
}
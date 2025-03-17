package com.example.pioneerbackend.entity.product;

import com.example.pioneerbackend.entity.basket.Basket;
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

    @Column(name = "battery_life")
    private Integer batteryLife;

    @Column(name = "warning_alarm")
    private Boolean warningAlarm;

    @Column(name = "display")
    private Boolean display;

    @Column(name = "calibration")
    private String calibration;

    @Column(name = "response_time")
    private Integer responseTime;

    @Column(name = "explosion_protection_rating")
    private String explosionProtectionRating;

    @Column(name = "power_supply")
    private String powerSupply;

    @Column(name = "country")
    private String country;

    @Column(name = "operating_humidity")
    private String operatingHumidity;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "sensor_type")
    private String sensorType;

    @Column(name = "gas_sampling")
    private String gasSampling;

    @Column(name = "enclosure")
    private String enclosure;

    @Column(name = "operating_time")
    private Integer operatingTime;

    @Column(name = "charging_time")
    private Integer chargingTime;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "measured_gases_type")
    private String measuredGasesType;

    @Column(name = "channel_type")
    private String channelType;

    @Column(name = "type")
    private String type;

    @Column(name = "wireless_link")
    private String wirelessLink;

    @Column(name = "integrated_pump")
    private String integratedPump;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "additional", columnDefinition = "TEXT[]")
    private List<String> additional;

    @Column(name = "combustible_gases")
    private String combustibleGases;

    @Column(name = "oxygen")
    private String oxygen;

    @Column(name = "carbon_monoxide")
    private String carbonMonoxide;

    @Column(name = "hydrogen_sulphide")
    private String hydrogenSulphide;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "sales")
    private Integer sales = 0;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Basket> baskets;
}
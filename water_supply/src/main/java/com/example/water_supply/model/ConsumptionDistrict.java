package com.example.water_supply.model;

import jakarta.persistence.*;

@Entity
@Table(name = "consumption_districts")
public class ConsumptionDistrict {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "district_name", nullable = false, unique = true)
    private String districtName;

    @Column(name = "house_count", nullable = false)
    private Integer houseCount;

    @Column(name = "dispatch_address", nullable = false)
    private String dispatchAddress;

    @Column(name = "phone", nullable = false)
    private String phone;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDistrictName() { return districtName; }
    public void setDistrictName(String districtName) {
        if (districtName != null && !districtName.isEmpty()) {
            this.districtName = districtName;
        } else {
            throw new IllegalArgumentException("Название района не может быть пустым!");
        }
    }

    public Integer getHouseCount() { return houseCount; }
    public void setHouseCount(Integer houseCount) {
        if (houseCount != null && houseCount > 0) {
            this.houseCount = houseCount;
        } else {
            throw new IllegalArgumentException("Количество домов должно быть больше 0!");
        }
    }

    public String getDispatchAddress() { return dispatchAddress; }
    public void setDispatchAddress(String dispatchAddress) {
        if (dispatchAddress != null && !dispatchAddress.isEmpty()) {
            this.dispatchAddress = dispatchAddress;
        } else {
            throw new IllegalArgumentException("Адрес диспетчерской не может быть пустым!");
        }
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        if (phone != null && !phone.isEmpty() && phone.length() <= 20) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Телефон не может быть пустым или содержать более 20 символов!");
        }
    }

    @Override
    public String toString() {
        return districtName;
    }
}
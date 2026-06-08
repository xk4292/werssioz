package com.example.water_supply.controller.consumptiondistrict;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import com.example.water_supply.model.ConsumptionDistrict;

public class ConsumptionDistrictTableItem {
    private SimpleStringProperty districtName;
    private SimpleIntegerProperty houseCount;
    private SimpleStringProperty dispatchAddress;
    private SimpleStringProperty phone;
    private ConsumptionDistrict consumptionDistrict;

    public ConsumptionDistrictTableItem(ConsumptionDistrict district) {
        this.districtName = new SimpleStringProperty(district.getDistrictName());
        this.houseCount = new SimpleIntegerProperty(district.getHouseCount());
        this.dispatchAddress = new SimpleStringProperty(district.getDispatchAddress());
        this.phone = new SimpleStringProperty(district.getPhone());
        this.consumptionDistrict = district;
    }

    public String getDistrictName() { return districtName.get(); }
    public SimpleStringProperty districtNameProperty() { return districtName; }

    public int getHouseCount() { return houseCount.get(); }
    public SimpleIntegerProperty houseCountProperty() { return houseCount; }

    public String getDispatchAddress() { return dispatchAddress.get(); }
    public SimpleStringProperty dispatchAddressProperty() { return dispatchAddress; }

    public String getPhone() { return phone.get(); }
    public SimpleStringProperty phoneProperty() { return phone; }

    public ConsumptionDistrict getConsumptionDistrict() { return consumptionDistrict; }

    public void setDistrictName(String districtName) { this.districtName.set(districtName); }
    public void setHouseCount(int houseCount) { this.houseCount.set(houseCount); }
    public void setDispatchAddress(String dispatchAddress) { this.dispatchAddress.set(dispatchAddress); }
    public void setPhone(String phone) { this.phone.set(phone); }
}
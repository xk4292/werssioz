module water_supply {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens com.example.water_supply to javafx.fxml;
    exports com.example.water_supply;

    opens com.example.water_supply.controller.pipelinetype to javafx.fxml;
    exports com.example.water_supply.controller.pipelinetype;

    opens com.example.water_supply.controller.pipelinesection to javafx.fxml;
    exports com.example.water_supply.controller.pipelinesection;

    opens com.example.water_supply.controller.consumptiondistrict to javafx.fxml;
    exports com.example.water_supply.controller.consumptiondistrict;

    opens com.example.water_supply.controller.watermeasurement to javafx.fxml;
    exports com.example.water_supply.controller.watermeasurement;

    opens com.example.water_supply.model to org.hibernate.orm.core, javafx.base;
    exports com.example.water_supply.model;

    opens com.example.water_supply.repository to org.hibernate.orm.core;
    exports com.example.water_supply.repository;

    opens com.example.water_supply.service to javafx.base;
    exports com.example.water_supply.service;

    opens com.example.water_supply.util to org.hibernate.orm.core;
}
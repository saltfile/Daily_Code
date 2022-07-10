package com.saltfish.cloud.contoller;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {
    private DynamicStringProperty propertyOneWithDynamic
            = DynamicPropertyFactory.getInstance()
            .getStringProperty("saltfish.one", "not found!");

    @GetMapping("/property-from-dynamic-management")
    public String getPropertyValue() {
        return propertyOneWithDynamic.getName() + ": " + propertyOneWithDynamic.get();
    }
}

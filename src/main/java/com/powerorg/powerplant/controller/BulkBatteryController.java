package com.powerorg.powerplant.controller;

import com.powerorg.powerplant.exception.APIException;
import com.powerorg.powerplant.model.Battery;
import com.powerorg.powerplant.service.BatteryService;
import com.powerorg.powerplant.util.CustomValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.powerorg.powerplant.util.Constant.INVALID_REQUEST;

@Validated
@RestController
@RequestMapping(path = "${apiPrefix}/bulk/batteries")
public class BulkBatteryController {

    private final BatteryService batteryService;

    BulkBatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping
    List<Battery> addBatteries(@RequestBody @NotEmpty(message = "battery list can't be empty") List<Battery> batteryList) {
        List<String> errorList = CustomValidator.validateCollection(batteryList);

        if (!errorList.isEmpty()) {
            throw new APIException(HttpStatus.BAD_REQUEST, INVALID_REQUEST, errorList);
        }
        return batteryService.addBatteries(batteryList);
    }
}

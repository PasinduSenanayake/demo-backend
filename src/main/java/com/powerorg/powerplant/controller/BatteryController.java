package com.powerorg.powerplant.controller;

import com.powerorg.powerplant.dto.mapping.BatteryResponse;
import com.powerorg.powerplant.exception.APIException;
import com.powerorg.powerplant.model.Battery;
import com.powerorg.powerplant.service.BatteryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import static com.powerorg.powerplant.util.Constant.DEFAULT_PAGE;
import static com.powerorg.powerplant.util.Constant.DEFAULT_PAGE_SIZE;
import static com.powerorg.powerplant.util.Constant.DEFAULT_POSTAL_RANGE;
import static com.powerorg.powerplant.util.Constant.INVALID_REQUEST;


@Validated
@RestController
@RequestMapping("${apiPrefix}/batteries")
public class BatteryController {

    private final BatteryService batteryService;

    public BatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @PostMapping
    Battery addBattery(@RequestBody Battery battery) {
        return batteryService.addBattery(battery);
    }

    @GetMapping
    BatteryResponse getBatteries(
            @RequestParam(name = "post_code_range", defaultValue = DEFAULT_POSTAL_RANGE) @Pattern(regexp = "\\b\\d{4}\\b-\\b\\d{4}\\b", message = "post_code_range must match pattern 0000-9999") String postCodeRange,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) @Min(value = 1, message = "page_size must be a positive integer") int pageSize,
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) @Min(value = 1, message = "page must be a positive integer") int page
    ) {

        String[] postRanges = postCodeRange.split("-");

        int rangeStartFrom = Integer.parseInt(postRanges[0]);

        int rangeEndWith = Integer.parseInt(postRanges[1]);

        if (rangeStartFrom >= rangeEndWith) {
            throw new APIException(HttpStatus.BAD_REQUEST, INVALID_REQUEST, "invalid post_code_range");
        }

        return batteryService.getBatteryResponse(rangeStartFrom, rangeEndWith, page, pageSize);
    }


}

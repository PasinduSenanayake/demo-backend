package com.powerorg.powerplant.service;

import com.powerorg.powerplant.dto.mapping.BatteryResponse;
import com.powerorg.powerplant.model.Battery;

import java.util.List;

/**
 * The interface Battery service.
 */
public interface BatteryService {


    /**
     * Add battery battery.
     *
     * @param battery the battery
     * @return the battery
     */
    Battery addBattery(Battery battery);

    /**
     * Add batteries list.
     *
     * @param batteryList the battery list
     * @return the list
     */
    List<Battery> addBatteries(List<Battery> batteryList);

    /**
     * Gets battery response.
     *
     * @param rangeStartFrom the range start from
     * @param rangeEndWith   the range end with
     * @param page           the page
     * @param pageSize       the page size
     * @return the battery response
     */
    BatteryResponse getBatteryResponse(int rangeStartFrom, int rangeEndWith, int page, int pageSize);
}

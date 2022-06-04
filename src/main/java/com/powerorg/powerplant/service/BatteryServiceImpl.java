package com.powerorg.powerplant.service;

import com.powerorg.powerplant.dao.BatteryRepository;
import com.powerorg.powerplant.dto.mapping.BatteryResponse;
import com.powerorg.powerplant.model.Battery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository batteryRepository;

    BatteryServiceImpl(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    public Battery addBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    public List<Battery> addBatteries(List<Battery> batteryList) {
        return batteryRepository.saveAll(batteryList);
    }

    public BatteryResponse getBatteryResponse(int rangeStartFrom, int rangeEndWith, int page, int pageSize) {

        BatteryRepository.BatteryStatistic batteryStatistic = batteryRepository.getFilteredBatteryStatistics(rangeStartFrom, rangeEndWith);

        List<Battery> batteryList = batteryRepository.getFilteredAndOrderedBatteries(rangeStartFrom, rangeEndWith, PageRequest.of(page - 1, pageSize, Sort.Direction.ASC, "name"));

        return new BatteryResponse(batteryStatistic.getTotalCount(), batteryStatistic.getAverageCapacity(), batteryList);
    }
}

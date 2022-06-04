package com.powerorg.powerplant.dto.mapping;

import com.powerorg.powerplant.model.Battery;

import java.text.DecimalFormat;
import java.util.List;

public class BatteryResponse {

    private final int totalCount;
    private final double averageCapacity;
    private final List<Battery> items;

    public BatteryResponse (int totalCount, double averageCapacity, List<Battery> batteryList ){
        DecimalFormat df2 = new DecimalFormat("###.##");
        this.totalCount = totalCount;
        this.averageCapacity = Double.parseDouble(df2.format(averageCapacity));
        this.items = batteryList;
    }

    public double getAverageCapacity() {
        return averageCapacity;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Battery> getItems() {
        return items;
    }
}

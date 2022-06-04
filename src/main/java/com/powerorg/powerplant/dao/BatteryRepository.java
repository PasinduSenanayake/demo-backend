package com.powerorg.powerplant.dao;

import com.powerorg.powerplant.model.Battery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

    interface BatteryStatistic {

        int getTotalCount();

        double getAverageCapacity();

    }

    @Query(value = "SELECT * FROM BATTERY battery WHERE CAST(POSTCODE as INT) >= :rangeStart and CAST(POSTCODE as INT) <= :rangeEnd", nativeQuery = true)
    List<Battery> getFilteredAndOrderedBatteries(@Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd, Pageable pageable);

    @Query(value = "SELECT count(*) as totalCount, COALESCE(avg(CAPACITY),0) as averageCapacity FROM BATTERY battery WHERE CAST(POSTCODE as INT) >= :rangeStart and CAST(POSTCODE as INT) <= :rangeEnd", nativeQuery = true)
    BatteryStatistic getFilteredBatteryStatistics(@Param("rangeStart") int rangeStart, @Param("rangeEnd") int rangeEnd);

}
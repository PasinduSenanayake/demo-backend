package com.powerorg.powerplant.service;

import com.powerorg.powerplant.dao.BatteryRepository;
import com.powerorg.powerplant.dto.mapping.BatteryResponse;
import com.powerorg.powerplant.model.Battery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class BatteryServiceImplTest {

    private BatteryServiceImpl batteryServiceImpl;

    @Mock
    private BatteryRepository batteryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        batteryServiceImpl = new BatteryServiceImpl(batteryRepository);
    }


    @Test
    public void test_add_battery() {

        Battery battery = new Battery("test","1234",1234);

        Mockito.when(batteryRepository.save(any(Battery.class))).thenReturn(battery);

        Battery createdBattery =  batteryServiceImpl.addBattery(new Battery());

        Assertions.assertEquals("test",createdBattery.getName());

        Mockito.verify(batteryRepository,Mockito.times(1)).save(any(Battery.class));
    }

    @Test
    public void test_add_batteries() {

        Battery battery = new Battery("test","1234",1234);

        Mockito.when(batteryRepository.saveAll(anyCollection())).thenReturn(Collections.singletonList(battery));

        List<Battery> createdBatteryList =  batteryServiceImpl.addBatteries(Collections.singletonList(new Battery()));

        Mockito.verify(batteryRepository,Mockito.times(1)).saveAll(anyCollection());

        Assertions.assertFalse(createdBatteryList.isEmpty());

        Assertions.assertEquals("test",createdBatteryList.get(0).getName());
    }


    @Test
    public void test_get_battery_response() {

        BatteryRepository.BatteryStatistic batteryStatistic = new BatteryRepository.BatteryStatistic() {
            @Override
            public int getTotalCount() {
                return 20;
            }

            @Override
            public double getAverageCapacity() {
                return 20.02;
            }
        };

        Mockito.when(batteryRepository.getFilteredBatteryStatistics(anyInt(),anyInt())).thenReturn(batteryStatistic);

        Battery battery = new Battery("test","1234",1234);

        List<Battery> batteryList = Collections.singletonList(battery);

        Mockito.when(batteryRepository.getFilteredAndOrderedBatteries(anyInt(),anyInt(), any(PageRequest.class))).thenReturn(batteryList);

        BatteryResponse batteryResponse =  batteryServiceImpl.getBatteryResponse(1,2,3,4);

        Mockito.verify(batteryRepository,Mockito.times(1)).getFilteredAndOrderedBatteries(anyInt(),anyInt(), any(PageRequest.class));

        Mockito.verify(batteryRepository,Mockito.times(1)).getFilteredBatteryStatistics(anyInt(),anyInt());

        Assertions.assertEquals(20.02,batteryResponse.getAverageCapacity());

        Assertions.assertEquals(20,batteryResponse.getTotalCount());

        List<Battery> batteries = batteryResponse.getItems();

        Assertions.assertFalse(batteries.isEmpty());

        Assertions.assertEquals("test",batteries.get(0).getName());
    }
}

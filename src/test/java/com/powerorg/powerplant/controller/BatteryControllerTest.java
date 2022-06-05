package com.powerorg.powerplant.controller;

import com.powerorg.powerplant.dto.mapping.BatteryResponse;
import com.powerorg.powerplant.model.Battery;
import com.powerorg.powerplant.service.BatteryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BatteryController.class)
public class BatteryControllerTest {

    @MockBean
    BatteryService batteryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_post_request() throws Exception {
        Battery battery = new Battery("Cannington", "1233",10);

        Mockito.when(batteryService.addBattery(any(Battery.class))).thenReturn(battery);

        mockMvc.perform(post("/v1/batteries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n    \"name\": \"Cannington\",\n    \"postcode\":\"1233\",\n    \"capacity\": 10\n}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Cannington")))
                .andExpect(jsonPath("$.postcode", Matchers.is("1233")))
                .andExpect(jsonPath("$.capacity", Matchers.is(10)));
    }


    @Test
    public void test_get_request() throws Exception {
        Battery battery = new Battery("Cannington", "1233",10);

        BatteryResponse  batteryResponse = new BatteryResponse(10,20.20 , Collections.singletonList(battery));

        Mockito.when(batteryService.getBatteryResponse(anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(batteryResponse);

        mockMvc.perform(get("/v1/batteries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCount", Matchers.is(10)))
                .andExpect(jsonPath("$.averageCapacity", Matchers.is(20.20)))
                .andExpect(jsonPath("$.items[0].name", Matchers.is("Cannington")))
                .andExpect(jsonPath("$.items[0].postcode", Matchers.is("1233")))
                .andExpect(jsonPath("$.items[0].capacity", Matchers.is(10)));
    }


    @Test
    public void test_get_request_with_page_error() throws Exception {

        mockMvc.perform(get("/v1/batteries?page=0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Invalid request")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("page must be a positive integer")));

    }

    @Test
    public void test_get_request_with_page_size_error() throws Exception {

        mockMvc.perform(get("/v1/batteries?page_size=0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Invalid request")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("page_size must be a positive integer")));

    }


    @Test
    public void test_get_request_with_post_code_range_error() throws Exception {

        mockMvc.perform(get("/v1/batteries?post_code_range=3500-1230"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Invalid request")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("invalid post_code_range")));

    }
}

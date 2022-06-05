package com.powerorg.powerplant.controller;

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

import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BulkBatteryController.class)
public class BulkBatteryControllerTest {

    @MockBean
    BatteryService batteryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_post_request() throws Exception {

        Battery battery = new Battery("Cannington", "1233",10);

        Mockito.when(batteryService.addBatteries(anyList())).thenReturn(Collections.singletonList(battery));

        mockMvc.perform(post("/v1/bulk/batteries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[ \n  {\n    \"name\": \"Cannington\",\n    \"postcode\": \"6107\",\n    \"capacity\": \"13500\"\n  }\n]")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is("Cannington")))
                .andExpect(jsonPath("$[0].postcode", Matchers.is("1233")))
                .andExpect(jsonPath("$[0]capacity", Matchers.is(10)));
    }

    @Test
    public void test_post_request_with_empty_array() throws Exception {


        mockMvc.perform(post("/v1/bulk/batteries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[ \n]")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", Matchers.is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", Matchers.is("Invalid request")))
                .andExpect(jsonPath("$.errors[0]", Matchers.is("battery list can't be empty")));
    }

}

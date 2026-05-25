package com.example.rewards.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.rewards.entity.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc

public class RewardControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();

        repository.save(new Transaction(1L, "John", 120.0, LocalDate.of(2026, 1, 10)));
        repository.save(new Transaction(1L, "John", 70.0, LocalDate.of(2026, 1, 15)));
    }

    @Test
    void shouldReturnRewardsForCustomer() throws Exception {

        mockMvc.perform(get("/api/rewards/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.totalRewards").exists())
                .andExpect(jsonPath("$.monthlyRewards").isArray());
    }
    
    @Test
    void shouldReturnBadRequestForInvalidCustomerId() throws Exception {

        mockMvc.perform(get("/api/rewards/0"))
                .andExpect(status().isBadRequest());
    }
}

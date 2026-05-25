package com.example.rewards.controller;

import com.example.rewards.service.RewardService;

import jakarta.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
@Validated
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<com.example.rewards.dto.response.RewardResponse> getRewards(
            @PathVariable @Min(1) Long customerId) {

        return ResponseEntity.ok(rewardService.getRewardsByCustomer(customerId));
    }
}

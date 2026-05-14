package com.example.rewards.controller;

import com.example.rewards.dto.RewardResponse;
import com.example.rewards.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<RewardResponse> getRewards(@PathVariable Long customerId) {

        return ResponseEntity.ok(rewardService.getRewardsByCustomer(customerId));
    }
}

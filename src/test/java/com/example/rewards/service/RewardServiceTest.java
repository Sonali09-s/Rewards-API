package com.example.rewards.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RewardServiceTest {

    private final RewardService rewardService = new RewardService(null);

    @Test
    void shouldCalculateRewardsCorrectly() {

        long points = rewardService.calculateRewardPoints(120.0);

        Assertions.assertEquals(90, points);
    }
}

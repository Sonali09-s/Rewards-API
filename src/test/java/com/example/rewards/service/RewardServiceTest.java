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
    
    @Test
    void shouldCalculateRewardsForBetween50And100() {
        Assertions.assertEquals(20, rewardService.calculateRewardPoints(70.0));
    }

    @Test
    void shouldReturnZeroForBelow50() {
        Assertions.assertEquals(0, rewardService.calculateRewardPoints(40.0));
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> rewardService.calculateRewardPoints(-10.0));  	
}
    
    

}
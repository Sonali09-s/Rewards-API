package com.example.rewards.dto.response;

import java.util.List;

public class RewardResponse {

    private Long customerId;
    private String customerName;
    private List<MonthlyReward> monthlyRewards;
    private Long totalRewards;

    public RewardResponse(Long customerId, String customerName,
                          List<MonthlyReward> monthlyRewards,
                          Long totalRewards) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MonthlyReward> getMonthlyRewards() {
        return monthlyRewards;
    }

    public Long getTotalRewards() {
        return totalRewards;
    }
}

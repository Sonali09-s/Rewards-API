package com.example.rewards.dto;

public class MonthlyReward {

    private String month;
    private Long points;

    public MonthlyReward(String month, Long points) {
        this.month = month;
        this.points = points;
    }

    public String getMonth() {
        return month;
    }

    public Long getPoints() {
        return points;
    }
}

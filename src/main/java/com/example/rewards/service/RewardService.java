package com.example.rewards.service;

import com.example.rewards.dto.MonthlyReward;
import com.example.rewards.dto.RewardResponse;
import com.example.rewards.entity.Transaction;
import com.example.rewards.exception.ResourceNotFoundException;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private final TransactionRepository transactionRepository;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public RewardResponse getRewardsByCustomer(Long customerId) {

        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for customer id: " + customerId);
        }

        Map<Month, Long> monthlyRewardMap = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTransactionDate().getMonth(),
                        Collectors.summingLong(transaction -> calculateRewardPoints(transaction.getAmount()))
                ));

        List<MonthlyReward> monthlyRewards = monthlyRewardMap.entrySet()
                .stream()
                .map(entry -> new MonthlyReward(
                        entry.getKey().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                        entry.getValue()))
                .toList();

        long totalRewards = monthlyRewards.stream()
                .mapToLong(MonthlyReward::getPoints)
                .sum();

        Transaction transaction = transactions.get(0);

        return new RewardResponse(
                transaction.getCustomerId(),
                transaction.getCustomerName(),
                monthlyRewards,
                totalRewards);
    }

    public long calculateRewardPoints(Double amount) {

        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative or null");
        }

        long points = 0;

        if (amount > 100) {
            points += (long) ((amount - 100) * 2);
            points += 50;
        } else if (amount > 50) {
            points += (long) (amount - 50);
        }

        return points;
    }
}

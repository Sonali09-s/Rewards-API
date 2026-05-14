package com.example.rewards.service;

import com.example.rewards.entity.Transaction;
import com.example.rewards.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader {

    private final TransactionRepository repository;

    public DataLoader(TransactionRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadData() {

        repository.save(new Transaction(1L, "John", 120.0, LocalDate.of(2026, 1, 15)));
        repository.save(new Transaction(1L, "John", 75.0, LocalDate.of(2026, 1, 25)));
        repository.save(new Transaction(1L, "John", 200.0, LocalDate.of(2026, 2, 10)));
        repository.save(new Transaction(2L, "Emma", 130.0, LocalDate.of(2026, 2, 5)));
        repository.save(new Transaction(2L, "Emma", 90.0, LocalDate.of(2026, 3, 5)));
    }
}

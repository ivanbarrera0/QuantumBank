package com.example.QuantumBank.entities;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column
    private int balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account() {
    }

    public Account(int accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Account(int accountId, int balance, User user) {
        this.accountId = accountId;
        this.balance = balance;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}

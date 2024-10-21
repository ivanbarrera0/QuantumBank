package com.example.QuantumBank.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column
    private String accountType;

    @Column
    private int balance;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account() {
    }

    public Account(String accountType, int balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account(String accountType, int balance, User user) {
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
    }

    public Account(int accountId, String accountType, int balance, User user) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}

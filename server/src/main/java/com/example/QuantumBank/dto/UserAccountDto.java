package com.example.QuantumBank.dto;

import com.example.QuantumBank.entities.Account;
import com.example.QuantumBank.entities.User;

public class UserAccountDto {

    private User user;

    private Account account;
    public UserAccountDto(User user, Account account) {
        this.user = user;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserAccountDto{" +
                "user=" + user +
                ", account=" + account +
                '}';
    }
}

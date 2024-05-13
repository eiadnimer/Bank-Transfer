package org.example.core;
import lombok.Getter;
import lombok.Setter;
import org.example.exeprions.NumberIsMinus;

import java.util.Objects;

@Getter
public class Customer {
    private final String name;
    private final String mobile;
    private final String emil;
    private int balance;
    private final int accountNumber;
    @Setter
    private StatusTypes status;
    private NotificationType notificationType = NotificationType.EMAIL;

    public Customer(String name, String mobile, String emil, int balance, int accountNumber, StatusTypes status) {
        if (name == null) {
            throw new IllegalArgumentException("Name must be filled");
        }
        this.name = name;
        if (mobile == null) {
            throw new IllegalArgumentException("Mobile must be filled");
        }
        this.mobile = mobile;
        if (emil == null) {
            throw new IllegalArgumentException("Emil must be filled");
        }
        this.emil = emil;
        if (balance < 0) {
            throw new NumberIsMinus("The balance should be a positive number");
        }
        this.balance = balance;
        if (accountNumber <= 0) {
            throw new NumberIsMinus("The account number should be a positive number");
        }
        this.accountNumber = accountNumber;
        if (status == null) {
            throw new IllegalArgumentException("Status must be filled");
        }
        this.status = status;
    }

    protected void credit(int money) {
        this.balance = this.balance + money;
    }

    protected boolean isActive() {
        return this.status == StatusTypes.ACTIVE;
    }

    protected void debit(int money) {
        this.balance = this.balance - money;
    }


    protected void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return accountNumber == customer.accountNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return name;
    }
}

package org.example.customerBuilder;
import org.example.core.Customer;
import org.example.core.NotificationType;
import org.example.core.StatusTypes;

public class CustomerBuilder implements HasMobile, HasEmil, HasBalance, HasAccountNumber ,HasStatus,HasNotification,CanBuild{
    private final String name;
    private String mobile;
    private String emil;
    private int balance;
    private int accountNumber;
    private StatusTypes status;
    private NotificationType notificationType = NotificationType.EMAIL;

    private CustomerBuilder(String name) {
        this.name = name;
    }

    public static HasMobile withName(String name) {
        return new CustomerBuilder(name);
    }


    @Override
    public HasEmil withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public HasBalance withEmil(String emil) {
        this.emil = emil;
        return this;
    }

    @Override
    public HasAccountNumber withBalance(int balance) {
        this.balance = balance;
        return this;
    }

    @Override
    public HasStatus withAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    @Override
    public HasNotification withStatus(StatusTypes status) {
        this.status = status;
        return this;
    }

    @Override
    public CanBuild withNotification(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    @Override
    public Customer build() {
        return null;
    }
}

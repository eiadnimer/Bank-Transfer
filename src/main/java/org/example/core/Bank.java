package org.example.core;

import org.example.core.notification.NotificationFactory;
import org.example.core.notification.NotificationType;
import org.example.exeprions.CustomerNotExist;
import org.example.exeprions.InsufficientFunds;
import org.example.exeprions.UnActiveAccount;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<Integer, Customer> customers = new HashMap<>();
    private final NotificationFactory notificationFactory;

    public Bank(NotificationFactory notificationFactory) {
        this.notificationFactory = notificationFactory;
    }

    public Customer openAccount(String name, String mobile, String emil) {
        int accountNumber = getAccountNumber();
        Customer customer = new Customer(name, mobile, emil, 0,
                accountNumber, StatusTypes.ACTIVE);
        customers.put(accountNumber, customer);
        sendNotification(customer);
        return customer;
    }

    private int getAccountNumber() {
        int accountNumber = 0;
        if (customers.isEmpty()) {
            accountNumber = 1;
        } else {
            accountNumber = accountNumber + customers.size() + 1;
        }
        return accountNumber;
    }

    private void sendNotification(Customer customer) {
        notificationFactory.create(customer).send(customer);
    }

    public void creditMoney(int accountNumber, int money) {
        Customer customer = customers.get(accountNumber);
        if (!customer.isActive()) {
            throw new UnActiveAccount("the account should be active to credit money");
        }
        customer.credit(money);
        sendNotification(customer);
    }

    public void changeStatus(int accountNumber, StatusTypes status) {
        Customer customer = customers.get(accountNumber);
        customer.setStatus(status);
        sendNotification(customer);
    }

    public void changeNotification(int accountNumber, NotificationType notificationType) {
        Customer customer = customers.get(accountNumber);
        customer.setNotificationType(notificationType);
    }

    public void transferMoney(int accountNumberSender, int accountNumberReceiver, int money) {
        if (!customers.containsKey(accountNumberSender) || !customers.containsKey(accountNumberReceiver)) {
            throw new CustomerNotExist("the customer must be register in the bank");
        }
        for (Customer customer : customers.values()) {
            if (customer.getAccountNumber() == accountNumberSender) {
                if (customer.getBalance() < money) {
                    throw new InsufficientFunds();
                }
                if (!customer.isActive()) {
                    throw new UnActiveAccount("the account should be active to transfer money");
                }
                customer.debit(money);
                sendNotification(customer);
            }
            if (customer.getAccountNumber() == accountNumberReceiver) {
                if (!customer.isActive()) {
                    throw new UnActiveAccount("the account should be active to transfer money");
                }
                customer.credit(money);
                sendNotification(customer);
            }
        }
    }
}

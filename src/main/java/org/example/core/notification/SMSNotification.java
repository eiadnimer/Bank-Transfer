package org.example.core.notification;

import org.example.core.Customer;

public class SMSNotification implements Notification {
    @Override
    public void send(Customer customer) {
        System.out.println("Message was sent to " + customer);
    }
}

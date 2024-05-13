package org.example.core;

public class SMSNotification implements Notification {
    @Override
    public void send(Customer customer) {
        System.out.println("Message was sent to " + customer);
    }
}

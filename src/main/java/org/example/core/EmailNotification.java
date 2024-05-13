package org.example.core;

public class EmailNotification implements Notification {
    @Override
    public void send(Customer customer) {
        System.out.println("Emil was sent to " + customer);
    }
}

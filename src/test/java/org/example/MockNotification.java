package org.example;

import org.example.core.Customer;
import org.example.core.notification.Notification;

public class MockNotification implements Notification {
    private boolean isCalled;

    @Override
    public void send(Customer customer) {
        isCalled = true;
    }

    public boolean isCalled() {
        return isCalled;
    }
}

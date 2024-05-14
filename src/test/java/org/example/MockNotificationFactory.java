package org.example;

import lombok.Getter;
import org.example.core.Customer;
import org.example.core.notification.Notification;
import org.example.core.notification.NotificationFactory;

@Getter
public class MockNotificationFactory implements NotificationFactory {

    private final MockNotification notification = new MockNotification();

    @Override
    public Notification create(Customer customer) {
        return notification;
    }

}

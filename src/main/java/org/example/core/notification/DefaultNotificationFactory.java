package org.example.core.notification;

import org.example.core.Customer;

public class DefaultNotificationFactory implements NotificationFactory{
    @Override
    public Notification create(Customer customer) {
        return switch (customer.getNotificationType()){
            case EMAIL -> new EmailNotification();
            case SMS -> new SMSNotification();
        };
    }
}

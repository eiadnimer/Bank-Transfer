package org.example.core;

public class DefaultNotificationFactory implements NotificationFactory{
    @Override
    public Notification create(Customer customer) {
        return switch (customer.getNotificationType()){
            case EMAIL -> new EmailNotification();
            case SMS -> new SMSNotification();
        };
    }
}

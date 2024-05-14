package org.example.core.notification;

import org.example.core.Customer;

public interface NotificationFactory {
    Notification create(Customer customer);
}

package org.example.core.notification;

import org.example.core.Customer;

public interface Notification {
    void send(Customer customer);
}

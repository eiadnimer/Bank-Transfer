package org.example.customerBuilder;


import org.example.core.notification.NotificationType;

public interface HasNotification {
    CanBuild withNotification(NotificationType notificationType);
}

package org.example.customerBuilder;


import org.example.core.NotificationType;

public interface HasNotification {
    CanBuild withNotification(NotificationType notificationType);
}

package org.example.customerBuilder;

import org.example.core.StatusTypes;

public interface HasStatus {

    HasNotification withStatus(StatusTypes status);
}

package org.example.exeprions;

public class CustomerNotExist extends RuntimeException{

    public CustomerNotExist(String message) {
        super(message);
    }
}

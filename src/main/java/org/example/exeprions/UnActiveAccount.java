package org.example.exeprions;

public class UnActiveAccount extends RuntimeException{
    public UnActiveAccount(String message){
        super(message);
    }
}

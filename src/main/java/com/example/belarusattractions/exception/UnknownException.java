package com.example.belarusattractions.exception;

public class UnknownException extends VirtualMachineError {
    public UnknownException(String description){
        super(description);
    }
}

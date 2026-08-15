package com.example.PowerToolStore.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String idKey, Long id) {
        super("Address not found for " + idKey + ": " + id);
    }
}

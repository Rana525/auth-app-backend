package com.substring.auth.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {
    public ResourceNotFoundExceptions(String message) {
        super(message);
    }
    public ResourceNotFoundExceptions() {
        super("Resource not found !!!!");
    }
}

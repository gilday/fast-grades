package com.johnathangilday.autograder.exception;

/**
 * General RuntimeException thrown by application code
 */
public class AppException extends RuntimeException {

    public AppException(String message) { super(message); }
    public AppException(String message, Throwable cause) { super(message, cause); }
}

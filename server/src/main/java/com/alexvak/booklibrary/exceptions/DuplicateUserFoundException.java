package com.alexvak.booklibrary.exceptions;

public class DuplicateUserFoundException extends RuntimeException {

    private static final String DUPLICATE_FOND = "Unable to create new user. A User with name %s already exist.";

    public DuplicateUserFoundException(String userName) {
        super(String.format(DUPLICATE_FOND, userName));
    }
}

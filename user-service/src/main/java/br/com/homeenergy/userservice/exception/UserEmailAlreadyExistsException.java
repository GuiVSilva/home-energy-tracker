package br.com.homeenergy.userservice.exception;

public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String email) {
        super("An account with email '" + email + "' already exists");
    }
}

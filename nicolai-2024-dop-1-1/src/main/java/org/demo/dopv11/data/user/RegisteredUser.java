package org.demo.dopv11.data.user;

public record RegisteredUser(String name, Email email) implements User {

    public RegisteredUser {
        if (email == null || email.address() == null || email.address().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
    }
}

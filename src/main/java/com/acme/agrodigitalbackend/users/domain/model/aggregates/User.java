package com.acme.agrodigitalbackend.users.domain.model.aggregates;

import com.acme.agrodigitalbackend.users.domain.model.valueobjects.EmailAddress;
import com.acme.agrodigitalbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.agrodigitalbackend.users.domain.model.commands.CreateUserCommand;
import com.acme.agrodigitalbackend.users.domain.model.valueobjects.FullName;
import com.acme.agrodigitalbackend.users.domain.model.valueobjects.Password;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends AuditableAbstractAggregateRoot<User> {

    @Embedded
    @AttributeOverride(name = "fullName", column = @Column(name = "full_name"))
    private FullName name;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "email", unique = true))
    private EmailAddress email;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password"))
    private Password password;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String name, String email, String password, String role) {
        this.name = new FullName(name);
        this.email = new EmailAddress(email);
        this.password = new Password(password);
        this.role = role;
    }

    public User(CreateUserCommand command) {
        this.name = new FullName(command.name());
        this.email = new EmailAddress(command.email());
        this.password = new Password(command.password());
        this.role = command.role();
    }

    public void updateFullName(String fullName) {
        this.name = new FullName(fullName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updatePassword(String password) {
        this.password = new Password(password);
    }

    public void updateRole(String role) {
        this.role = role;
    }

    // Getters
    public FullName getName() {
        return name;
    }

    public EmailAddress getEmailAddress() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return name.fullName();
    }

    public String getEmail() {
        return email.address();
    }

    public String getPasswordValue() {
        return password.password();
    }


}
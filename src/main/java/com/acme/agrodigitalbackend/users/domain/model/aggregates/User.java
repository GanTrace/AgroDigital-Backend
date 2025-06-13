package com.acme.agrodigitalbackend.users.domain.model.aggregates;

import com.acme.agrodigitalbackend.profiles.domain.model.valueobjects.EmailAddress;
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



    public User() {
    }

    public User(String fullName, String email, String password) {
        this.name = new FullName(fullName);
        this.email = new EmailAddress(email);
        this.password = new Password(password);
    }

    public User(CreateUserCommand command) {
        this.name = new FullName(command.fullName());
        this.email = new EmailAddress(command.email());
        this.password = new Password(command.password());
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



    public String getFullName() {
        return name.fullName();
    }

    public String getEmail() {
        return email.address();
    }


}
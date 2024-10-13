package main.java.UEFS.system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a user in the system.
 * A user can be an admin or a regular user and has personal information such as login, name, CPF, and email.
 * Users can also have associated tickets.
 */
public class User {
    private final UUID id;
    private final String login;
    private String password;
    private String name;
    private String cpf;
    private String email;
    private final Boolean isAdmin;
    private final List<UUID> tickets = new ArrayList<>();

    /**
     * Constructs a new User with the specified login, password, name, CPF, email, and admin status.
     *
     * @param login   the login name for the user
     * @param password the password for the user
     * @param name    the full name of the user
     * @param cpf     the CPF (Cadastro de Pessoas Físicas) number of the user
     * @param email   the email address of the user
     * @param isAdmin the admin status of the user
     */
    public User(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /**
     * Constructs a new User with the specified login, password, name, CPF, and email.
     * The user is created as a non-admin by default.
     *
     * @param login   the login name for the user
     * @param password the password for the user
     * @param name    the full name of the user
     * @param cpf     the CPF (Cadastro de Pessoas Físicas) number of the user
     * @param email   the email address of the user
     */
    public User(String login, String password, String name, String cpf, String email) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = false;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user's UUID
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the login name of the user.
     *
     * @return the login name
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets the full name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the CPF number of the user.
     *
     * @return the user's CPF
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Checks if the user is an admin.
     *
     * @return true if the user is an admin, false otherwise
     */
    public Boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Gets the password of the user.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name for the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the CPF number for the user.
     *
     * @param cpf the new CPF number for the user
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Authenticates the user by checking if the provided login and password match the user's credentials.
     *
     * @param login    the login name to authenticate
     * @param password the password to authenticate
     * @return true if the credentials match, false otherwise
     */
    public Boolean login(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    /**
     * Adds a ticket to the user's list of tickets.
     *
     * @param ticket the ticket to add
     */
    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket.getId())) {
            this.tickets.add(ticket.getId());
        }
    }

    /**
     * Removes a ticket from the user's list of tickets.
     *
     * @param ticket the ticket to remove
     */
    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket.getId())) {
            this.tickets.remove(ticket.getId());
        }
    }

    /**
     * Gets the list of ticket IDs associated with the user.
     *
     * @return a list of ticket UUIDs
     */
    public List<UUID> getTickets() {
        return tickets;
    }

    /**
     * Checks if this user is equal to another object.
     * Two users are considered equal if their login, CPF, and email are the same.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(cpf, user.cpf) &&
                Objects.equals(email, user.email);
    }

    /**
     * Computes the hash code for the user based on the login, CPF, and email.
     *
     * @return the hash code for the user
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, email);
    }
}

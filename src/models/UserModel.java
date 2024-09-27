package models;

import java.util.Objects;
import java.util.UUID;

public class UserModel {
    private UUID id;
    private String login;
    private String password;
    private String name;
    private String cpf;
    private String email;
    private Boolean isAdmin;

    public UserModel(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public UserModel(String login, String password, String name, String cpf, String email) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = false;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean login(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel user = (UserModel) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(cpf, user.cpf) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, email);
    }
}

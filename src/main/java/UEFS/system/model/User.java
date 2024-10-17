/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package main.java.UEFS.system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String login;
    private String password;
    private String name;
    private String cpf;
    private String email;
    private final Boolean isAdmin;
    private final List<UUID> tickets = new ArrayList<>();

    public User(String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User(String login, String password, String name, String cpf, String email) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = false;
    }


    public UUID getId() {
        return this.id;
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


    public String getPassword() {
        return password;
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


    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket.getId())) {
            this.tickets.add(ticket.getId());
        }
    }


    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket.getId())) {
            this.tickets.remove(ticket.getId());
        }
    }


    public List<UUID> getTickets() {
        return tickets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(cpf, user.cpf) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, email);
    }
}

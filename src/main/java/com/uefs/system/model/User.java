/***************************
 * Autor: Robson Carvalho de Souza
 * Componente Curricular: MI de Programação
 * Concluído em: 16/09/2024
 *
 * Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ******************************/

package com.uefs.system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa um usuário no sistema, que pode ou não ser um administrador.
 * Cada usuário possui um login, senha, nome, CPF, e-mail e uma lista de ingressos comprados.
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
     * Construtor principal que inicializa um usuário, podendo ser um administrador.
     *
     * @param login    SignUp do usuário
     * @param password Senha do usuário
     * @param name     Nome completo do usuário
     * @param cpf      CPF do usuário
     * @param email    E-mail do usuário
     * @param isAdmin  Indica se o usuário é administrador
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
     * Construtor alternativo que inicializa um usuário com id.
     *
     * @param id    ID do usuário
     * @param login    SignUp do usuário
     * @param password Senha do usuário
     * @param name     Nome completo do usuário
     * @param cpf      CPF do usuário
     * @param email    E-mail do usuário
     * @param isAdmin  Indica se o usuário é administrador
     */
    public User(UUID id, String login, String password, String name, String cpf, String email, Boolean isAdmin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /**
     * Construtor alternativo que inicializa um usuário comum (não administrador).
     *
     * @param login    SignUp do usuário
     * @param password Senha do usuário
     * @param name     Nome completo do usuário
     * @param cpf      CPF do usuário
     * @param email    E-mail do usuário
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
     * Retorna o ID único do usuário.
     *
     * @return ID do usuário
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Retorna o login do usuário.
     *
     * @return SignUp do usuário
     */
    public String getLogin() {
        return login;
    }

    /**
     * Retorna o nome completo do usuário.
     *
     * @return Nome do usuário
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o CPF do usuário.
     *
     * @return CPF do usuário
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna o e-mail do usuário.
     *
     * @return E-mail do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Indica se o usuário é administrador.
     *
     * @return true se for administrador, false caso contrário
     */
    public Boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return Senha do usuário
     */
    public String getPassword() {
        return password;
    }

    /**
     * Atualiza o nome do usuário.
     *
     * @param name Novo nome do usuário
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Atualiza o e-mail do usuário.
     *
     * @param email Novo e-mail do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Atualiza a senha do usuário.
     *
     * @param password Nova senha do usuário
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Atualiza o CPF do usuário.
     *
     * @param cpf Novo CPF do usuário
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Verifica se o login e a senha fornecidos são válidos para o usuário.
     *
     * @param login    SignUp fornecido
     * @param password Senha fornecida
     * @return true se as credenciais forem válidas, false caso contrário
     */
    public Boolean login(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    /**
     * Adiciona um ingresso à lista de ingressos do usuário.
     *
     * @param ticket Ingresso a ser adicionado
     */
    public void addTicket(Ticket ticket) {
        if (!tickets.contains(ticket.getId())) {
            this.tickets.add(ticket.getId());
        }
    }

    /**
     * Remove um ingresso da lista de ingressos do usuário.
     *
     * @param ticket Ingresso a ser removido
     */
    public void removeTicket(Ticket ticket) {
        if (tickets.contains(ticket.getId())) {
            this.tickets.remove(ticket.getId());
        }
    }

    /**
     * Retorna a lista de ingressos associados ao usuário.
     *
     * @return Lista de IDs de ingressos
     */
    public List<UUID> getTickets() {
        return tickets;
    }

    /**
     * Sobrescreve o método equals para comparar usuários com base no login, CPF e e-mail.
     *
     * @param o Objeto a ser comparado
     * @return true se os usuários forem iguais, false caso contrário
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
     * Sobrescreve o método hashCode com base no login, CPF e e-mail.
     *
     * @return Código hash do usuário
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, email);
    }
}

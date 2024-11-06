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

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe que representa um cartão de pagamento vinculado a um usuário.
 * Cada instância da classe contém informações como ID do cartão, ID do usuário,
 * nome do usuário, marca do cartão, número da conta, número do cartão, data de validade,
 * código de verificação (CVV) e status do cartão (ativo/inativo).
 */
public class Card {
    private final UUID id;
    private final UUID userID;
    private final String userName;
    private final String brand;
    private final String accountNumber;
    private final String number;
    private final Date expiryDate;
    private final String cvv;
    private Boolean status;

    /**
     * Construtor que inicializa um objeto Card com as informações fornecidas.
     * O ID do cartão é gerado automaticamente.
     *
     * @param userID        ID do usuário vinculado ao cartão
     * @param userName      Nome do usuário
     * @param brand         Marca do cartão (ex: Visa, MasterCard)
     * @param accountNumber Número da conta vinculada ao cartão
     * @param number        Número do cartão
     * @param expiryDate    Data de validade do cartão
     * @param cvv           Código de verificação do cartão
     */
    public Card(UUID userID, String userName, String brand, String accountNumber, String number, Date expiryDate, String cvv) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.userName = userName;
        this.brand = brand;
        this.accountNumber = accountNumber;
        this.number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.status = true;
    }

    /**
     * Retorna o nome do usuário vinculado ao cartão.
     *
     * @return Nome do usuário
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Retorna o ID único do cartão.
     *
     * @return ID do cartão
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retorna o status do cartão (ativo/inativo).
     *
     * @return Status do cartão
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Retorna o ID do usuário vinculado ao cartão.
     *
     * @return ID do usuário
     */
    public UUID getUserId() {
        return userID;
    }

    /**
     * Retorna o número do cartão.
     *
     * @return Número do cartão
     */
    public String getCardNumber() {
        return number;
    }

    /**
     * Desativa o cartão, alterando seu status para inativo.
     */
    public void disable() {
        this.status = false;
    }

    /**
     * Retorna a data de validade do cartão.
     *
     * @return Data de validade do cartão
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Retorna o código de verificação (CVV) do cartão.
     *
     * @return Código CVV do cartão
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Verifica se o cartão está ativo.
     *
     * @return true se o cartão estiver ativo, caso contrário, false
     */
    public Boolean isActive() {
        return status;
    }

    /**
     * Retorna a marca do cartão.
     *
     * @return Marca do cartão (ex: Visa, MasterCard)
     */
    public String getCardBrand() {
        return brand;
    }

    /**
     * Retorna o número da conta vinculada ao cartão.
     *
     * @return Número da conta
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Verifica se dois objetos Card são iguais, comparando o número e a marca do cartão.
     *
     * @param o Objeto a ser comparado
     * @return true se os cartões forem iguais, caso contrário, false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card cc = (Card) o;
        return Objects.equals(number, cc.number) && Objects.equals(brand, cc.brand);
    }

    /**
     * Retorna o hash code para o objeto Card, baseado no número e na marca do cartão.
     *
     * @return Hash code do objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(number, brand);
    }
}

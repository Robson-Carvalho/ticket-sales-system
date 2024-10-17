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

package main.java.UEFS.system.model;

import main.java.UEFS.system.enums.PaymentMethod;

import java.util.Date;
import java.util.UUID;

/**
 * Classe que representa uma transação financeira no sistema.
 * Cada transação envolve um usuário, um ingresso, um método de pagamento e um valor.
 */
public class Transaction {
    private final UUID id;
    private final UUID userID;
    private final UUID ticketID;
    private final UUID creditCardID;
    private final Date date;
    private final Double amount;
    private final PaymentMethod paymentMethod;

    /**
     * Construtor que inicializa uma transação com todos os campos, incluindo o ID do cartão de crédito.
     *
     * @param userID       ID do usuário associado
     * @param ticketID     ID do ingresso associado
     * @param creditCardID ID do cartão de crédito utilizado
     * @param amount       Valor da transação
     * @param method       Método de pagamento utilizado
     */
    public Transaction(UUID userID, UUID ticketID, UUID creditCardID, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = creditCardID;
        this.date = new Date();
        this.amount = amount;
        this.paymentMethod = method;
    }

    /**
     * Construtor alternativo que inicializa uma transação sem o ID do cartão de crédito.
     *
     * @param userID   ID do usuário associado
     * @param ticketID ID do ingresso associado
     * @param amount   Valor da transação
     * @param method   Método de pagamento utilizado
     */
    public Transaction(UUID userID, UUID ticketID, Double amount, PaymentMethod method) {
        this.id = UUID.randomUUID();
        this.userID = userID;
        this.ticketID = ticketID;
        this.creditCardID = null;
        this.date = new Date();
        this.amount = amount;
        this.paymentMethod = method;
    }

    /**
     * Retorna o ID único da transação.
     *
     * @return ID da transação
     */
    public UUID getId() {
        return id;
    }

    /**
     * Retorna o ID do usuário associado à transação.
     *
     * @return ID do usuário
     */
    public UUID getUserID() {
        return userID;
    }

    /**
     * Retorna o ID do ingresso associado à transação.
     *
     * @return ID do ingresso
     */
    public UUID getTicketID() {
        return ticketID;
    }

    /**
     * Retorna o ID do cartão de crédito associado à transação, se aplicável.
     *
     * @return ID do cartão de crédito, ou null se não houver
     */
    public UUID getCreditCardID() {
        return creditCardID;
    }

    /**
     * Retorna a data em que a transação foi realizada.
     *
     * @return Data da transação
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retorna o valor da transação.
     *
     * @return Valor da transação
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Retorna uma string representando o método de pagamento.
     * Caso seja cartão de crédito, retorna "Cartão", caso contrário, retorna "Boleto".
     *
     * @return Descrição do método de pagamento
     */
    public String getPaymentMethod() {
        if (paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            return "Cartão";
        }
        return "Boleto";
    }
}

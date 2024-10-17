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

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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

    public String getUserName() {
        return this.userName;
    }

    public UUID getId() {
        return id;
    }

    public boolean getStatus() {
        return this.status;
    }

    public UUID getUserId() {
        return userID;
    }

    public String getCardNumber() {
        return number;
    }


    public void disable() {
        this.status = false;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public Boolean isActive() {
        return status;
    }


    public String getCardBrand() {
        return brand;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card cc = (Card) o;
        return Objects.equals(number, cc.number) && Objects.equals(brand, cc.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, brand);
    }
}

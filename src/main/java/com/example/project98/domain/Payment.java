package com.example.project98.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    private long id;
    private String cardNumber;

    private String cardName;

    private String cardHolderName;

    private String expirationDate;

    private String cardCvv;

    private BigDecimal cardAmount;


    public Payment(String cardNumber, String cardName, String receiptDate, String cardHolderName, String expirationDate, String cardCvv, BigDecimal cardAmount) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cardCvv = cardCvv;
        this.cardAmount = cardAmount;
    }

    public Payment(){
        super();
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCardName() {return cardName;}
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getCardCvv() {return cardCvv;}
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
    public BigDecimal getCardAmount() {
        return cardAmount;
    }
    public void setCardAmount(BigDecimal cardAmount) {this.cardAmount = cardAmount;}

}

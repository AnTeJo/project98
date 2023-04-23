package com.example.project98.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;
    private String cardNumber;

    private String cardHolderName;

    private String expMonth;
    private String expYear;

    private String cardCvv;

    private BigDecimal sumPayment;


    public Payment(String cardNumber, String cardHolderName, String expMonth, String expYear, String cardCvv, BigDecimal sumPayment) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cardCvv = cardCvv;
        this.sumPayment = sumPayment;
    }

    public Payment(){
        super();
    }
    public long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(long id) {
        this.paymentId = paymentId;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public String getExpMonth() {
        return expMonth;
    }
    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }
    public String getExpYear() {
        return expYear;
    }
    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }
    public String getCardCvv() {return cardCvv;}
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
    public BigDecimal getSumPayment() {
        return sumPayment;
    }
    public void setSumPayment(BigDecimal sumPayment) {this.sumPayment = sumPayment;}

}

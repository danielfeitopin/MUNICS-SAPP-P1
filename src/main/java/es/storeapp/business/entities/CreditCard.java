package es.storeapp.business.entities;

import java.io.Serializable;
import java.text.MessageFormat;
import jakarta.persistence.Embeddable;

@Embeddable
public class CreditCard implements Serializable {

    private String card;
    
    private Integer cvv;
    
    private Integer expirationMonth;
    
    private Integer expirationYear;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    @Override
    public String toString() {
        return MessageFormat.format("CreditCard{card={0}, cvv={1}, expirationMonth={2}, expirationYear={3}}", 
            card, cvv, expirationMonth, expirationYear);
    }
    
    
    
}

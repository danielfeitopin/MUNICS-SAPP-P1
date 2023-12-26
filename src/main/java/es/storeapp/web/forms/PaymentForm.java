package es.storeapp.web.forms;

public class PaymentForm {
    
    private Boolean defaultCreditCard;
    private String creditCard;
    private Integer cvv;
    private Integer expirationMonth;
    private Integer expirationYear;
    private Boolean save;

    public Boolean getDefaultCreditCard() {
        return defaultCreditCard;
    }

    public void setDefaultCreditCard(Boolean defaultCreditCard) {
        this.defaultCreditCard = defaultCreditCard;
    }
    
    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
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

    public Boolean getSave() {
        return save;
    }

    public void setSave(Boolean save) {
        this.save = save;
    }
    
}

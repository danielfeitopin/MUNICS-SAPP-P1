package es.storeapp.business.entities;

import es.storeapp.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity(name = Constants.USER_ENTITY)
@Table(name = Constants.USERS_TABLE)
public class User implements Serializable {

    private static final long serialVersionUID = 570528466125178223L;

    public User() {
    }

    public User(String name, String email, String password, String address, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.image = image;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @NotBlank(message = "Name cannot be null")
    @Column(name = "name", nullable = false, unique = false)
    private String name;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be null")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "address cannot be null")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "resetPasswordToken")
    private String resetPasswordToken;
    
    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name = "card", column = @Column(name = "card")),
        @AttributeOverride(name = "cvv", column = @Column(name = "CVV")),
        @AttributeOverride(name = "expirationMonth", column = @Column(name = "expirationMonth")),
        @AttributeOverride(name = "expirationYear", column = @Column(name = "expirationYear"))
    })
    private CreditCard card;
    
    @Column(name = "image")
    private String image;
    
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return String.format("User{userId=%s, name=%s, email=%s, password=%s, address=%s, resetPasswordToken=%s, card=%s, image=%s}", 
            userId, name, email, password, address, resetPasswordToken, card, image);
    }

}

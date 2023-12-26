package es.storeapp.web.forms;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class UserProfileForm {

    @NotNull
    @Size(min=4)
    private String name;
    
    @NotNull
    private String email;
    
    private String password;
    
    @NotNull
    private String address;
    
    private MultipartFile image;

    public UserProfileForm() {
    }

    public UserProfileForm(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }    
    
}

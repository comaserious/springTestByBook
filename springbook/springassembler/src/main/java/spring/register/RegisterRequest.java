package spring.register;

import lombok.*;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString
public class RegisterRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;


    public boolean isPasswordEqualToConfirmPassword(){
        return this.password.equals(confirmPassword);
    }
}

import lombok.*;

@NoArgsConstructor@AllArgsConstructor@Setter@Getter@ToString
public class RegisterRequest {

    private String email;
    private String password;
    private String confirmPassword;
    private String name;


}

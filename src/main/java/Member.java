import lombok.*;

import java.lang.invoke.WrongMethodTypeException;
import java.time.LocalDateTime;

@NoArgsConstructor@AllArgsConstructor@Setter@Getter@ToString
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;

    public Member(String email,String password,String name,LocalDateTime registerDateTime){
        this.email=email;
        this.password=password;
        this.name = name;
        this.registerDateTime=registerDateTime;
    }
    public void changePassword(String oldPassword, String newPassword) throws WrongIdPasswordException {
        if(!this.password.equals(oldPassword)){
            throw new WrongIdPasswordException();
        }
        this.password=newPassword;
    }
}

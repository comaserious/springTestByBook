package spring.MemberData;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString
public class Member {

    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;


    public Member(String email, String password, String name, LocalDateTime registerDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = registerDateTime;
    }

    public void changePassword(String oldPassword,String newPassword){
        if(!this.password.equals(oldPassword)){
            throw new WrongPasswordException();
        }
        this.password=newPassword;
    }
}

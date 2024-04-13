package spring.memberData;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@ToString
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
            throw new WrongIdPasswordException();
        }
        this.password=newPassword;
    }
}

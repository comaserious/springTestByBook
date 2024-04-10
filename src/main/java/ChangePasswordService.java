import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordService {

    private MemberDao memberDao;
    @Autowired
    public ChangePasswordService(MemberDao memberDao){
        this.memberDao=memberDao;
    }
    public void changePassword(String email,String oldPassword,String newPassword) throws MemberNotFoundException {
        Member member  = memberDao.selectMemberByEmail(email);

        if(member==null){
            throw new MemberNotFoundException();
        }

        member.changePassword(oldPassword,newPassword);

        memberDao.update(member);
    }


}

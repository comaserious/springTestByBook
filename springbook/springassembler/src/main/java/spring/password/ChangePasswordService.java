package spring.password;

import spring.memberData.Member;
import spring.memberData.MemberDAO;
import spring.memberData.WrongIdPasswordException;

public class ChangePasswordService {

    private MemberDAO memberDAO;

    public ChangePasswordService(MemberDAO memberDAO){
        this.memberDAO=memberDAO;
    }

    public void changePassword(String email,String oldPassword,String newPassword) {
        Member member = memberDAO.selectByEmail(email);

        if(member==null){
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPassword,newPassword);

        memberDAO.update(member);


    }
}

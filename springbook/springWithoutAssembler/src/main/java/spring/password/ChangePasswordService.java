package spring.password;

import org.springframework.stereotype.Component;
import spring.MemberData.Member;
import spring.MemberData.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class ChangePasswordService {
    @Autowired
    private MemberDAO memberDAO;
    public ChangePasswordService(){}

    public ChangePasswordService(MemberDAO memberDAO){
        this.memberDAO=memberDAO;
    }

    public void changePassword(String email,String oldPassword,String newPassword){
        Member member = memberDAO.selectByEmail(email);

        if(member==null){
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPassword,newPassword);

        memberDAO.update(member);
    }
}

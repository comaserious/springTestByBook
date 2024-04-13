package spring.register;

import lombok.NoArgsConstructor;
import spring.MemberData.Member;
import spring.MemberData.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@NoArgsConstructor
public class MemberRegisterService {
    @Autowired
    public MemberDAO memberDAO;

    public MemberRegisterService(MemberDAO memberDAO){
        this.memberDAO=memberDAO;
    }

    public Long register(RegisterRequest req){
        if(memberDAO.selectByEmail(req.getEmail())!=null){
            throw new DuplicateMemberException("dup email"+req.getEmail());
        }
        Member newMember = new Member(req.getEmail(),req.getPassword(),req.getName(), LocalDateTime.now());
        memberDAO.insert(newMember);
        return newMember.getId();
    }

}

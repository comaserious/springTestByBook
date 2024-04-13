package spring.register;

import spring.memberData.Member;
import spring.memberData.MemberDAO;

import java.time.LocalDateTime;

public class MemberRegisterService {

    private MemberDAO memberDAO;

    public MemberRegisterService(MemberDAO memberDAO){
        this.memberDAO=memberDAO;
    }

    public Long register(RegisterRequest req) throws DuplicateMemberException {
        if(memberDAO.selectByEmail(req.getEmail())!=null){
            throw new DuplicateMemberException("dup email"+req.getEmail());
        }
        Member newMember = new Member();
        newMember.setEmail(req.getEmail());
        newMember.setName(req.getName());
        newMember.setPassword(req.getPassword());
        newMember.setRegisterDateTime(LocalDateTime.now());
        memberDAO.insert(newMember);

        // 연동이 되는가 ?
        // 된다 얕은 복사로 주소를 복사해가기 때문에
        return newMember.getId();
    }

}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberRegisterService {

    private MemberDao memberDao;

    @Autowired
    public MemberRegisterService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req){
        Member member = memberDao.selectMemberByEmail(req.getEmail());
        if(member!=null){
            throw new DuplicateMemberException("duplicate email : "+ req.getEmail());
        }

        Member newMember = new Member(req.getEmail(),req.getPassword(),req.getName(), LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }

}

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemberDao {

    // static 용도를 알것 같다
    // 생성할때마다 새로 만들어지는 것이 아닌
    // 모든 인스턴스에서 동일하게 사용하기 위해서
    // static 을 적용하니
    // 값이 0으로 초기화가 되는 것이 아닌
    // 지속적으로 값이 증가하게 되는 것이다
    private static long nextId=0;

    private Map<String,Member> map = new HashMap<>();

    public Member selectMemberByEmail(String email){

        return map.get(email);
    }

    public void insert(Member member){
        member.setId(++nextId);
        map.put(member.getEmail(),member);
    }
    public void update(Member member){
        map.put(member.getEmail(),member);
    }
}

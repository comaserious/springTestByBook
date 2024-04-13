package spring.MemberData;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDAO {

    private static long nextId=0;
    private Map<String,Member> map = new HashMap<>();

    public Member selectByEmail(String email){
        return map.get(email);
    }

    public void insert(Member member){
        member.setId(++nextId);
        map.put(member.getEmail(),member);

    }

    public void update(Member member){
        map.put(member.getEmail(),member);
    }

    public List<Member> selectAll(){
        List<Member> members = new ArrayList<>(map.values());
        return members;
    }
}

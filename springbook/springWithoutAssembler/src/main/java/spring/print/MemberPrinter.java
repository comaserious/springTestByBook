package spring.print;

import org.springframework.stereotype.Component;
import spring.MemberData.Member;
@Component
public class MemberPrinter {

    public void print(Member member){
        System.out.println(member);
    }
}

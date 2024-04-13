package spring.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.MemberData.Member;
import spring.MemberData.MemberDAO;

import java.util.List;


public class MemberListPrint {

    private MemberDAO memberDAO;
    private MemberPrinter memberPrinter;

    @Autowired
    public MemberListPrint(MemberDAO memberDAO,MemberPrinter memberPrinter){
        this.memberDAO=memberDAO;
        this.memberPrinter=memberPrinter;
    }

    public void printAll(){
        List<Member> memberList = memberDAO.selectAll();
        for(Member member : memberList){
            memberPrinter.print(member);
        }
    }
}
